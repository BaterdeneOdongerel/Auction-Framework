package Framework.TemplateMethodPattern;

import model.Auction;
import model.Bid;
import model.User;
import service.Services;
import service.UserService;
import service.communication.CommunicationType;
import service.communication.Option;

import java.lang.reflect.AccessibleObject;
import java.util.Date;
import java.util.List;

public abstract class AbstractAuctionTemplate {

    public final List<Auction> processAuction() {
        List<Bid> bids = currentWinningBid();
        processCurrentWinningBid(bids);
        List<Auction> auctions = calculateWinningBid(bids);
        Date now = new Date();
        for (Auction auction : auctions) {
            if (auction.getEndDate().before(now)) {
                User user = Services.UserService.selectById(new Long(auction.getWinner()).intValue());
                Option emailOption = new Option.Builder()
                        .withTo(user.getEmail())
                        .withContent("Winner you are")
                        .withSubject("You're the winner")
                        .withName(user.getFirstName())
                        .build();
                Option smsOption = new Option.Builder()
                        .withTo("+16414511523")
                        .withContent("Winner you are")
                        .withSubject("You're the winner")
                        .withName(user.getFirstName())
                        .build();
                Services.Communicator.send(emailOption, CommunicationType.EMAIL);
                Services.Communicator.send(smsOption, CommunicationType.SMS);
            }
        }
        return auctions;
    }

    public abstract List<Bid> currentWinningBid();

    public abstract void processCurrentWinningBid(List<Bid> currentWinningBids);

    public abstract List<Auction> calculateWinningBid(List<Bid> bids);

    public void calculateWinningBid() {
        processCurrentWinningBid(currentWinningBid());
    }


}
