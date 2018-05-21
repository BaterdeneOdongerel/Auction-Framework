package service;


import Framework.FactoryPattern.ConcreteTraceFactory;
import Framework.FactoryPattern.TraceFactory;

import model.Auction;
import model.User;
import service.communication.*;
import service.task.TaskQueue;
import service.task.TimerTask;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 5/15/18.
 */
public class Services {

    public static UserService UserService;
    public static AuctionService AuctionService;
    public static ProductService ProductService;
    public static EventServiceImpl EventService;
    public static CanCommunicate Communicator;
    public static BidService BidService;

    public static TraceFactory TraceFactory;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
        EventService = new EventServiceImpl();
        Communicator = new CommunicationServiceImpl();
        TraceFactory = ConcreteTraceFactory.getFactory();
        BidService = new BidServiceImpl();
        loadCronJobs();
    }

    private static void loadCronJobs() {
        TaskQueue queue = TaskQueue.getInstance();
        queue.start();
        int[] executeWindow = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        for (Integer time : executeWindow) {
            TimerTask calculateCurrentWinnerTask = new TimerTask(true) {
                @Override
                public void _run() {
                    LoggingService.createLog("Calculate Current Winner", "Calculate Current Winner of all auctions", LogType.Event);
                    AuctionService.processAuction();
                    List<Auction> auctions = AuctionService.selectAll();
                    for (Auction auction : auctions) {
                        Date now = new Date();
                        if (auction.getStartDate().compareTo(now) == 0) {
                            AuctionService.startAuction(auction);
                        }
                    }
                }
            }.setExecuteTime(LocalTime.of(time, 0));
            queue.addTask(calculateCurrentWinnerTask);
        }
    }
}
