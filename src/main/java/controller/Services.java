package controller;


import service.*;

/**
 * Created by admin on 5/15/18.
 */
public class Services {

    public static UserService UserService;
    public static service.AuctionService AuctionService;
    public static service.ProductService ProductService;
    public static service.EventServiceImpl EventService;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
        EventService = new EventServiceImpl();
    }

}
