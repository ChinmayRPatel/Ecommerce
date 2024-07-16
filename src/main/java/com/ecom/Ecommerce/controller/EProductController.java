package com.ecom.Ecommerce.controller;

import com.ecom.Ecommerce.bean.EProductBean;
import com.ecom.Ecommerce.dao.EProductDao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUtils.*;

@Controller
public class EProductController {

    @Autowired
    EProductDao productDao;

    @GetMapping("/newproduct") // url->browser
    public String newProduct() {// method name
        return "NewProduct";// jsp name
    }

    // int a = 10
    @PostMapping("/saveproduct")
    public String saveProduct(EProductBean productBean) {

        if (productBean.getProductImage() != null) {
//            String productImagePath = "C:\\sts\\ecom-app\\src\\main\\webapp\\images\\products\\";
              String productImagePath = "C:\\Users\\patel\\OneDrive\\Desktop\\Spring_Roayl_Tech\\Ecommerce\\src\\main\\webapp\\images\\products\\";
            try {
                File file = new File(productImagePath, productBean.getProductImage().getOriginalFilename());
                byte b[] = productBean.getProductImage().getBytes();// copy
                FileUtils.writeByteArrayToFile(file, b);

                productBean
                        .setProductImagePath("images/products/" + productBean.getProductImage().getOriginalFilename());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        productDao.addProduct(productBean);

        // productDao.addProduct(productBean);// argument
        return "redirect:/products";// url call


    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<EProductBean> products = productDao.getAllProducts();// return type

        model.addAttribute("products", products);

        return "EcomListProducts";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productId") Integer productId) {

        System.out.println("deleteProduct() => " + productId);

        productDao.deleteProduct(productId);
        return "redirect:/products";// do not open jsp
        // open an url

    }

    // form ->data -> read -> bean

    // hyperlink -> ? -> query string -> @RequestParam("q") datatyep variablename
    // @RequestParam("productId") Integer productId

    @GetMapping("/viewproduct")
    public String viewProduct(@RequestParam("productId") Integer productId, Model model) {

        // id->details->table : products
        // select * from products where productId = ?
        EProductBean productBean = productDao.getProductById(productId);
        model.addAttribute("product", productBean);

        return "ViewProduct";
    }
}
