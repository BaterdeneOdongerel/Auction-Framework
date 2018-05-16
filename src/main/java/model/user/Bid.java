package model.user;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Bid {
    private long id;
    private double amount;
    private Date bidDate;
    private long auction;
    private long user;

}
