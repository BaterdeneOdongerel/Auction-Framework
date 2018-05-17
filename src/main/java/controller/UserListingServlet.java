package controller;


import model.User;
import service.CategoryServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list_user")
public class UserListingServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryServiceImp categoryServiceImp = new CategoryServiceImp();
        List<User> users = Services.UserService.selectAll();
        request.setAttribute("users", users);
        RequestDispatcher view = request.getRequestDispatcher("list_user.jsp");
        view.forward(request, response);
    }

}
