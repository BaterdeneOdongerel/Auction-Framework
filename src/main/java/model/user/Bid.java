package model.user;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Bid {
    private int id;
    private BigDecimal amount;
    private Date bidDate;
    private Auction auction;
    private User user;

}
