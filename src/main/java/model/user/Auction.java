package model.user;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class Auction {
    private int id;
    private Date startDate;
    private Date endDate;
    private BigDecimal minimumPrice;
    private User bidOwner;
    private boolean isRunning;
    private User currentUser;
    private Bid currentWinningBid;
    private User winner;
    private List<Bid> bids;
}

