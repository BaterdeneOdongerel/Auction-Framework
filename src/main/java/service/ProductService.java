package service;

import model.user.Product;

import java.util.List;

public interface ProductService  extends CrudTemplate<Product>{
    List<Product> selectByCatid(int catid);
}
