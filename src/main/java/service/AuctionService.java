package service;

import model.user.Auction;

import java.util.List;

public interface AuctionService {

    void create(Auction auction);

    Auction selectById(int id);

    List<Auction> selectAll();

    void delete(int id);

    void update(Auction auction, int id);
}
