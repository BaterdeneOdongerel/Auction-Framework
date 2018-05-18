package Framework.TemplateMethodPattern;

import model.Auction;
import model.Bid;

import java.util.List;

public abstract class AbstractAuctionTemplate {

    public final void processAuction(List<Bid> bids) {
        processCurrentWinningBid(bids);
        calculateWinningBid(bids);
    }

    public abstract void processCurrentWinningBid(List<Bid> currentWinningBids);

    public abstract List<Auction> calculateWinningBid(List<Bid> bids);
}
