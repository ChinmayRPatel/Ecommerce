package com.ecom.Ecommerce.controller;

import com.ecom.Ecommerce.bean.UserBean;
import com.ecom.Ecommerce.dao.UserDao;
import com.ecom.Ecommerce.services.EmailService;
import com.ecom.Ecommerce.services.OTPStorage;
import com.ecom.Ecommerce.services.OtpServices;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SessionController {

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	OtpServices otpService;

 	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    private EmailService emailService;

	@GetMapping("/signup")
	public String signup() {
		return "Signup";// jsp open
	}
	@GetMapping("/")
	public String home(){
		return "Welcome";
	}

	@PostMapping("/signup")
	public String signupPost(UserBean user) {

		user.setPassword(encoder.encode(user.getPassword()));
		UserBean userBean = userDao.getUserByEmail(user.getEmail());
		if(userBean != null) {
			System.out.println("Email is already registered");
		}else{
			userDao.insertUser(user);
		}
		return "Login";
	}

	@GetMapping("/login")
	public String login() {
		return "Login";
	}


	@PostMapping("/authenticate")
	public String authenticate(@RequestParam("email") String email, @RequestParam("password") String password,
							   Model model, HttpSession session) {
		System.out.println(email);
		System.out.println(password);
		boolean authStatus = false;
		UserBean dbUser = null;
		try {
			// if email is invalid we will got exception
			dbUser = userDao.getUserByEmail(email);
			System.out.println("dbUser Found");

			String encPwd = dbUser.getPassword();

			if (encoder.matches(password, encPwd) == true) {
				authStatus = true;
				session.setAttribute("user", dbUser);
			} else {
				authStatus = false;
			}

		} catch (Exception e) {
			System.out.println("authenticate -- Exception---");
			authStatus = false;
		}

		if (authStatus == false) {
			model.addAttribute("error", "Invalid Credentials");
			return "Login";
		} else {
			// credentials true -->

			if (dbUser.getRole().equals("ADMIN")) {
				return "redirect:/dashboard";
			} else if (dbUser.getRole().equals("CUSTOMER")) {
				return "Home";
			}
			return "ERROR";// true -> Home False -> Login
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}

	@GetMapping("/forgetpassword")
	public String forgetPassword() {
		return "ForgetPassword";
	}

	@PostMapping("/sendotp")
	public String sendOtp(@RequestParam("email") String email, Model model)  throws IOException {
		System.out.println("email => " + email);
		// check db -> present
		// select * from users where email = ?
		UserBean user = null;
		try {
			user = userDao.getUserByEmail(email);
			// if email is invalid -> dao -> throw exception
		} catch (Exception e) {
			System.out.println("email not found....");
		}

		if (user == null) {
			model.addAttribute("error", "Email Not Found");
			return "ForgetPassword";
		} else {
				String otp = otpService.generateOTP();
				System.out.println("OTP => " + otp);
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("devotees33@gmail.com");
				message.setTo(email);
				message.setSubject("Send otp for reset passwords");
				message.setText("Otp is for reset passwords and valid for 10 mins OTP :- " + otp);
				mailSender.send(message);
				return "OTP Sent";
		}
	}

	@PostMapping("updatepassword")
	public String updatePassword(UserBean userBean, Model model) {

		// verify email - otp
		boolean status = userDao.verifyOtp(userBean.getEmail(), userBean.getOtp());
		if (status == true) {
			// yes -> password update -> login

			String password = encoder.encode(userBean.getPassword());
			userDao.updatePassword(userBean.getEmail(), password);
			userDao.updateOtp(userBean.getEmail(), "");

			return "redirect:/login";// url
		} else {
			// no -> error
			model.addAttribute("error", "Data Does not match");
			return "VerifyOtp";
		}
	}
}
