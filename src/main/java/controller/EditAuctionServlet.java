package controller;


import model.user.Auction;
import service.AuctionService;
import service.ProductService;
import service.UserService;
import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_auction")
public class EditAuctionServlet extends BaseServlet {
    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuctionService auctionServiceImp = Services.AuctionService;
        UserService userServiceImp = Services.UserService;
        ProductService productServiceImp = Services.ProductService;


        String delete_id = request.getParameter("delete_id");
        if (delete_id != null) { // delete
            auctionServiceImp.delete(Integer.parseInt(delete_id));
            response.sendRedirect("/list_auction");
        } else {

            String sid = request.getParameter("id");

            int id = -1;
            Auction auction = new Auction();

            if (sid != null) {
                id = Integer.parseInt(sid);
                auction = auctionServiceImp.selectById(id);

            }

            auction.setId(id);
            request.setAttribute("auction", auction);
            request.setAttribute("users", userServiceImp.getUserListForDropDown());
            request.setAttribute("products", productServiceImp.getProductListForDropDown());

            RequestDispatcher view = request.getRequestDispatcher("edit_auction.jsp");
            view.forward(request, response);
        }


    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionService auctionServiceImp = Services.AuctionService;

        String delete_id = request.getParameter("delete_id");
        if (delete_id != null) { // delete
            auctionServiceImp.delete(Integer.parseInt(delete_id));
        } else { // update or create
            String sid = request.getParameter("id");
            String endDate = request.getParameter("endDate");
            String startDate = request.getParameter("startDate");
            String product = request.getParameter("product");
            String bidOwner = request.getParameter("bidOwner");
            String minimumPrice = request.getParameter("minimumPrice");

            Auction auction = new Auction();

            auction.setEndDate(Utils.convertToSqlDate(endDate));
            auction.setStartDate(Utils.convertToSqlDate(startDate));
            auction.setMinimumPrice(Double.valueOf(minimumPrice));
            auction.setProduct(Integer.parseInt(product));
            auction.setBidOwner(Integer.parseInt(bidOwner));


            if (sid == null || "-1".equals(sid)) { // create
                auctionServiceImp.create(auction);
            } else { // update
                auctionServiceImp.update(auction, Integer.parseInt(sid));
            }

            request.setAttribute("auction", auction);
        }
        response.sendRedirect("/list_auction");
    }
}
