package controller;


import service.UserService;
import service.UserServiceImpl;

/**
 * Created by admin on 4/23/18.
 */
public class Services {

    public static UserService UserService;

    static {
        UserService = new UserServiceImpl();
    }
}
