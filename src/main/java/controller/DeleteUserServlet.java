package controller;


import model.Auction;
import service.AuctionService;
import service.ProductService;
import service.Services;
import service.UserService;
import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_user")
public class DeleteUserServlet extends BaseServlet {
    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Services.UserService.delete(Integer.parseInt(id));
        RequestDispatcher view = request.getRequestDispatcher("list_user.jsp");
        view.forward(request, response);
    }
}
