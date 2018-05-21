package controller;


import model.User;
import props.MessagesProp;
import service.LogType;
import service.LoggingService;
import service.Services;
import service.UserServiceImpl;
import service.communication.CommunicationType;
import service.communication.Option;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Utils.*;

@WebServlet("/signup")
public class SignupServlet extends BaseServlet {
    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = extractValue(request, "username", "");
        String fname = extractValue(request, "firstname", "");
        String lname = extractValue(request, "lastname", "");
        String pass = extractValue(request, "password", "");
        String email = extractValue(request, "email", "");

        System.out.println();
        UserServiceImpl imp = new UserServiceImpl();
        User user = imp.selectByEmail(email);
        boolean error = false;

        if (user != null) {
            error = true;
            request.setAttribute("error", MessagesProp.INSTANCE.getProp("errorEmail"));
        }
        if (!error) {
            user = new User();
            user.setUsername(username);
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setPassword(pass);
            user.setEmail(email);
            imp.create(user);
            request.getSession().setAttribute("user", user);
            Option welcomeEmail = new Option.Builder()
                    .withTo(email)
                    .withName(fname)
                    .withContent("Greeting from Auction X! And congrats on your good choice")
                    .withSubject("Welcome to Auction X System")
                    .build();
            Services.Communicator.send(welcomeEmail, CommunicationType.EMAIL);
            LoggingService.createLog("User Created", email + " is created", LogType.Event);
            response.sendRedirect("/");
        } else {

            RequestDispatcher view = request.getRequestDispatcher("signup.jsp");
            view.forward(request, response);
        }
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("error", "m");
        RequestDispatcher view = request.getRequestDispatcher("signup.jsp");
        view.forward(request, response);

    }
}
