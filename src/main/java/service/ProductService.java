package service;

import model.Auction;
import model.Product;

import java.util.List;

public interface ProductService  extends CrudTemplate<Product>{
    List<Product> selectByCatid(int catid);

    List<Auction> selectWithAuctions();
}
