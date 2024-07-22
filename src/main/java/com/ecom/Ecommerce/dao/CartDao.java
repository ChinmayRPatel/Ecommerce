package com.ecom.Ecommerce.dao;

import com.ecom.Ecommerce.bean.CartBean;
import com.ecom.Ecommerce.bean.EProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class CartDao {
    @Autowired
    JdbcTemplate stmt;

    public void addToCart(CartBean cartBean) {
        stmt.update("insert into cart (productId,userId, qty) values (?,?, ?)", cartBean.getProductId(), cartBean.getUserId(), 1);
    }

    public List<EProductBean> myCart(Integer userId) {
        // select * from products join cart using (productId) where userId= 1;

        List<EProductBean> products = stmt.query(" select * from products join cart using (productId) where userId= ?",
                new BeanPropertyRowMapper<>(EProductBean.class), new Object[] { userId });
        return products;
    }
    public int checkForExistingItem(CartBean cartBean) {
        // select * from cart where productId = ? and userId = ?
        try {
            CartBean cart = stmt.queryForObject("select * from cart where productId = ? and userId = ?",
                    new BeanPropertyRowMapper<>(CartBean.class),
                    new Object[] { cartBean.getProductId(), cartBean.getUserId() });
            return cart.getQty();
        } catch (Exception e) {
            return 0;
        }

    }


    public void updateCart(CartBean cartBean) {
        stmt.update("update cart set qty = ? where productId = ? and userId = ? ", cartBean.getQty(),
                cartBean.getProductId(), cartBean.getUserId());
    }

    public void removeProduct(CartBean cart){
        stmt.update("delete from cart where productId = ? and userId = ?", cart.getProductId(), cart.getUserId());
    }
    // productId userId
}


