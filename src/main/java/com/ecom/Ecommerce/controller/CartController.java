package com.ecom.Ecommerce.controller;

import com.ecom.Ecommerce.bean.CartBean;
import com.ecom.Ecommerce.bean.EProductBean;
import com.ecom.Ecommerce.bean.UserBean;
import com.ecom.Ecommerce.dao.CartDao;
import com.ecom.Ecommerce.dao.EProductDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartDao cartDao;

    @Autowired
    EProductDao productDao;

    @GetMapping("/addtocart")
    public String addToCart(@RequestParam("productId") Integer productId, HttpSession session) {

        UserBean userBean = (UserBean) session.getAttribute("user");

        Integer userId = userBean.getUserId();

        CartBean cartBean = new CartBean();

        cartBean.setProductId(productId);
        cartBean.setUserId(userId);
        int qty = cartDao.checkForExistingItem(cartBean);

        if (qty == 0) {
            cartDao.addToCart(cartBean);
        }else {
            cartBean.setQty(qty+1);
            cartDao.updateCart(cartBean);
        }

        return "redirect:/listproducts";// url
    }
    @GetMapping("/mycart")
    public String myCart(HttpSession session,Model model) {
        UserBean userBean = (UserBean) session.getAttribute("user");
        Integer userId = userBean.getUserId();
        List<EProductBean> products = cartDao.myCart(userId);
        model.addAttribute("products",products);
        return "MyCart";
    }
    @GetMapping("/removecartitem")
    public String removeCartUtem(@RequestParam("productId") Integer productId, HttpSession session) {

        UserBean userBean = (UserBean) session.getAttribute("user");

        Integer userId = userBean.getUserId();

        CartBean cartBean = new CartBean();

        cartBean.setProductId(productId);
        cartBean.setUserId(userId);
        System.out.println(cartBean);

        cartDao.removeProduct(cartBean);

        return "redirect:/mycart";// url
    }
}
