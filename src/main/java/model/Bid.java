package model;

import lombok.Data;


import java.sql.Date;

@Data
public class Bid {
    private long id;
    private double amount;
    private Date bidDate;
    private long auction;
    private long user;
    private Date endDate;

}
