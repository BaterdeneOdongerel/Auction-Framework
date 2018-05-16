package model.user;


import lombok.Data;

@Data
public class BidAuction {

    private long bidId;
    private long auctionId;
    private long currentWinner;
    private long currentWinningBid;
    private long winner;
}
