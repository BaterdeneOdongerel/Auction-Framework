package controller;


import model.Auction;
import service.AuctionService;
import service.Services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/calculate_auctions")
public class CalculateAuctionsServlet extends BaseServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuctionService auctionService = Services.AuctionService;
        auctionService.processAuction();
        List<Auction> auctions = auctionService.selectAll();
        request.setAttribute("auctions", auctions);
        RequestDispatcher view = request.getRequestDispatcher("list_auction.jsp");
        view.forward(request, response);
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
