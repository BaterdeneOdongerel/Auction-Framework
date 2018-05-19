package controller;


import Framework.FactoryPattern.ConcreteTraceFactory;
import Framework.FactoryPattern.TraceFactory;
import service.*;
import service.communication.CanCommunicate;
import service.communication.CommunicationServiceImpl;

/**
 * Created by admin on 5/15/18.
 */
public class Services {

    public static UserService UserService;
    public static AuctionService AuctionService;
    public static ProductService ProductService;

    public static EventServiceImpl EventService;
    public static CanCommunicate communicator;

    public static TraceFactory traceFactory;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
        EventService = new EventServiceImpl();
        //communicator = new CommunicationServiceImpl();
        traceFactory = ConcreteTraceFactory.getFactory();
    }

}
