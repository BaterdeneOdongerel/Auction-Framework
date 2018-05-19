package controller;


import model.Bid;
import service.BidService;
import service.Services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create_bid")
public class CreateBidServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BidService bidService = Services.BidService;
        Bid t = new Bid();
        String auctionId = request.getParameter("auctionId");
        String amount = request.getParameter("amount");
        t.setAuction(Long.parseLong(auctionId));
        t.setAmount(Double.parseDouble(amount));
        bidService.create(t);
        Services.AuctionService.processAuction();
        response.sendRedirect("/");
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
