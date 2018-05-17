package model.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class AuctionReport {

    private long bidId;
    private long auctionId;

    private Date startDate;
    private Date endDate;
    private BigDecimal minimumPrice;

    private String bidOwner;
    private String isRunning;

    private String product;

    private String currentWinner;
    private String currentWinningBid;
    private String winner;

    private double bidAmount;
    private Date bidDate;

    private String bidUser;
}
