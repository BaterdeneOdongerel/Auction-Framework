package Framework.TemplateMethodPattern;

import model.Auction;
import model.Bid;

import java.util.List;

public abstract class AbstractAuctionTemplate {

    public final List<Auction> processAuction(List<Bid> bids) {

        processCurrentWinningBid(bids);
        List<Auction> auctions = calculateWinningBid(bids);

        return auctions;
    }

    public abstract void processCurrentWinningBid(List<Bid> currentWinningBids);

    public abstract List<Auction> calculateWinningBid(List<Bid> bids);
}
