package service;

import model.Auction;

import model.Report.AuctionReport;

import java.sql.Date;
import java.util.List;

public interface AuctionService extends CrudTemplate<Auction> {

    void processCurrentWinningBid();

    AuctionReport calculateWinningBid(List<AuctionReport> auctions);

    List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning);

    List<Auction> selectRunning();
}
