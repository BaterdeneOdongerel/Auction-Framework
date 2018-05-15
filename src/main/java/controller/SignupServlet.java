package controller;



import model.user.User;
import props.MessagesProp;
import service.UserServiceImpl;

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

        String username = extractValue(request,"username" ,"");
        String fname = extractValue(request,"firstname" ,"");
        String lname = extractValue(request,"lastname" ,"");
        String pass = extractValue(request,"password" ,"");
        String email = extractValue(request,"email" ,"");

        System.out.println();
        UserServiceImpl imp = new UserServiceImpl();
        User user = imp.selectByEmail(email);
        boolean error = false;

        if ( user != null ) {
            error = true;
            request.setAttribute("error", MessagesProp.INSTANCE.getProp("errorEmail"));
        }
        if ( !error ) {
            user = new User();
            user.setUsername(username);
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setPassword(pass);
            user.setEmail(email);
            imp.create(user);
            request.getSession().setAttribute("user", user);
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
