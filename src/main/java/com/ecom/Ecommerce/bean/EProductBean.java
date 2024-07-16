package com.ecom.Ecommerce.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EProductBean {

    private Integer productId;
    private String productName;
    private String category;
    private Integer qty;// int qty
    private Float price;
    private MultipartFile productImage;
    private String productImagePath;

}
