package controller;

import model.user.Auction;
import model.user.Product;
import service.AuctionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends BaseServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = Services.ProductService.selectAll();
        request.setAttribute("products" , products);

        AuctionServiceImpl auctionServiceImpl = new AuctionServiceImpl();
        List<Auction> auctions = auctionServiceImpl.selectRunning();
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
