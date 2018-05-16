package model.user;
import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String desc;
    private int catid;
    private int user ;
    private int bid_price;



}
