package controller;

import props.MessagesProp;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService userModel = Services.UserService;
        boolean success = userModel.login(email, password);

        if (success) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userModel.getCurrentUser() );
            response.sendRedirect("/list_product");
        } else {
            request.setAttribute("error", MessagesProp.INSTANCE.getProp("errorLogin"));
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error","m");
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);

    }

}