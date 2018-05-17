package model.user;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String desc;
    private int catid;
    private String imagePath;

    }
