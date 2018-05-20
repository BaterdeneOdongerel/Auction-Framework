package service;

import model.Auction;

import model.Bid;
import model.Report.AuctionReport;

import java.sql.Date;
import java.util.List;

public interface AuctionService extends CrudTemplate<Auction> {

    List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning);

    List<Auction> selectRunning();

    List<Auction> processAuction();

}
