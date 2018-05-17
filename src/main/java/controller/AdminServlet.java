package controller;

import model.user.Auction;
import model.user.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends BaseServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
        List<Auction> auctions = Services.AuctionService.selectAll();
        request.setAttribute("auctions" , auctions);
        view.forward(request, response);
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
        view.forward(request, response);
    }
}
