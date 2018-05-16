package service;

import model.user.Auction;
import model.user.Bid;
import model.user.Report.AuctionReport;

import java.sql.Date;
import java.util.List;

public interface AuctionService extends CrudTemplate<Auction> {

    void create(Auction auction);

    Auction selectById(int id);

    List<Auction> selectAll();

     void delete(int id);

    void update(Auction auction, int id);

    void processCurrentWinningBid();

    AuctionReport calculateWinningBid(List<AuctionReport> auctions);

    List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning);
}
