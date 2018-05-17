package controller;


import service.*;
import service.communication.CanCommunicate;
import service.communication.CommunicationServiceImpl;

/**
 * Created by admin on 5/15/18.
 */
public class Services {

    public static UserService UserService;
    public static service.AuctionService AuctionService;
    public static service.ProductService ProductService;
    public static service.EventServiceImpl EventService;
    public static CanCommunicate communicator;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
        EventService = new EventServiceImpl();
        //communicator = new CommunicationServiceImpl();
    }

}
