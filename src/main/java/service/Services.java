package service;


import Framework.FactoryPattern.ConcreteTraceFactory;
import Framework.FactoryPattern.TraceFactory;

import model.Auction;
import model.User;
import service.communication.*;
import service.task.TaskQueue;
import service.task.TimerTask;

import java.time.LocalTime;
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

    public static TraceFactory TraceFactory;

    static {
        UserService = new UserServiceImpl();
        AuctionService = new AuctionServiceImpl();
        ProductService = new ProductServiceImp();
        EventService = new EventServiceImpl();
        Communicator = new CommunicationServiceImpl();
        TraceFactory = ConcreteTraceFactory.getFactory();
        loadCronJobs();
    }

    private static void loadCronJobs() {
        TaskQueue queue = TaskQueue.getInstance();
        queue.start();
        /*TimerTask calculateCurrentWinnerTask = new TimerTask(true) {
            @Override
            public void _run() {
                LoggingService.createLog("Calculate Current Winner", "Calculate Current Winner of all auctions", LogType.Event);
                AuctionService.processAuction();
            }
        }.setExecuteTime(executeTime);*/

        LocalTime executeTime = LocalTime.of(9, 01, 10);
        TimerTask calculateUltimateWinner = new TimerTask(true) {
            @Override
            public void _run() {
                LoggingService.createLog("Calculate Ultimate Winner", "Calculate Ultimate Winner of all auctions", LogType.Event);
                List<Auction> results = AuctionService.processAuction();
                for (Auction auction : results) {
                    User user = UserService.selectById(new Long(auction.getWinner()).intValue());
                    Option emailOption = new Option.Builder()
                            .withTo(user.getEmail())
                            .withContent("Winner you are")
                            .withSubject("You're the winner")
                            .withName(user.getFirstName())
                            .build();
                    Option smsOption = new Option.Builder()
                            .withTo("+16414511523")
                            .withContent("Winner you are")
                            .withSubject("You're the winner")
                            .withName(user.getFirstName())
                            .build();
                    Communicator.send(emailOption, CommunicationType.EMAIL);
                    Communicator.send(smsOption, CommunicationType.SMS);
                }
            }
        }.setExecuteTime(executeTime);

        queue.addTask(calculateUltimateWinner);
    }

}
