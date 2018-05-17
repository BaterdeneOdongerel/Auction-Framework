package service;

import model.user.Auction;
import model.user.LabelValue;
import model.user.Product;

import java.util.List;

public interface ProductService  extends CrudTemplate<Product>{
    List<Product> selectByCatid(int catid);

    List<Auction> selectWithAuctions();

    List<LabelValue> getProductListForDropDown();
}
