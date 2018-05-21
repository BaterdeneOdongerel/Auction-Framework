package controller;

import model.Auction;
import service.Services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Auction> auctions = Services.ProductService.selectWithAuctions();
        request.setAttribute("auctions" , auctions);
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}
