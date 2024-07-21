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
        stmt.update("insert into cart (productId,userId) values (?,?)", cartBean.getProductId(), cartBean.getUserId());
    }

    public List<EProductBean> myCart(Integer userId) {
        // select * from products join cart using (productId) where userId= 1;

        List<EProductBean> products = stmt.query(" select * from products join cart using (productId) where userId= ?",
                new BeanPropertyRowMapper<>(EProductBean.class), new Object[] { userId });
        return products;
    }

    // productId userId
}


