package model.user;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


@Data
public class Auction {

    private long id;
    private Date startDate;
    private Date endDate;

    //optional
    public Product current_product;
    public User bidOwnerUser;
    //optional
    private long bidOwner;
    private boolean isRunning;

    private long product;

    private long currentWinner;
    private long currentWinningBid;
    private long winner;
    private Double minimumPrice;
    private String name;
    private String imageLink;
}

