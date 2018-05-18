package service;

import model.Auction;

import model.Bid;
import model.Report.AuctionReport;

import java.sql.Date;
import java.util.List;

public interface AuctionService extends CrudTemplate<Auction> {


    void processCurrentWinningBid(List<Bid> currentWinningBids);

    List<Auction> calculateWinningBid(List<Bid> auctions);

    List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning);

    List<Auction> selectRunning();
}
