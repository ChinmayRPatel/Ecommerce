package com.ecom.Ecommerce.controller;

import com.ecom.Ecommerce.bean.UserBean;
import com.ecom.Ecommerce.dao.UserDao;
import com.ecom.Ecommerce.services.OtpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	OtpServices otpService;

	@GetMapping("/signup")
	public String signup() {
		return "Signup";// jsp open
	}

	@PostMapping("/signup")
	public String signupPost(UserBean user) {
//		String pwd = user.getPassword(); 
//		String encPwd = encoder.encode(pwd); 
//		user.setPassword(encPwd);

		user.setPassword(encoder.encode(user.getPassword()));
		userDao.insertUser(user);
		return "Login";
	}

	@GetMapping("/login")
	public String login() {
		return "Login";
	}


	@PostMapping("/authenticate")
	public String authenticate(@RequestParam("email") String email, @RequestParam("password") String password,
							   Model model) {
		System.out.println(email);
		System.out.println(password);
		// dev@gmail.com
		// dev123
		// read existing info from database using email
		boolean authStatus = false;
		UserBean dbUser = null;
		try {
			// if email is invalid we will got exception
			dbUser = userDao.getUserByEmail(email);
			System.out.println("dbUser Found");

			String encPwd = dbUser.getPassword();

			if (encoder.matches(password, encPwd) == true) {
				authStatus = true;
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
	public String logout() {
		return "redirect:/login";
	}

	@GetMapping("/forgetpassword")
	public String forgetPassword() {
		return "ForgetPassword";
	}

	@PostMapping("/sendotp")
	public String sendOtp(@RequestParam("email") String email, Model model) {
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
			// otp generate
			String otp = otpService.generateOtp();
			System.out.println("OTP => " + otp);
			// user:email:otp
			// mail otp

			return "VerifyOtp";
		}
	}

}
