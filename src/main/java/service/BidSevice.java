package service;


import model.user.Bid;

import java.util.List;

public interface BidSevice {

    void create(Bid bid);

    Bid selectById(int id);

    List<Bid> selectAll();

    void delete(int id);

    void update(Bid bid, int id);
}
