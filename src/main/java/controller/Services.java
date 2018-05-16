package controller;


import service.*;

/**
 * Created by admin on 4/23/18.
 */
public class Services {

    public static UserService UserService;
    public static service.AuctionService AuctionService;
    public static service.ProductService ProductService;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
    }

}
