package controller;


import model.user.Category;
import model.user.Product;
import service.CategoryServiceImp;
import service.ProductServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list_product")
public class ListProductServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductServiceImp productServiceImp = new ProductServiceImp();
        List<Product> products = null;

        String catid = request.getParameter("catid");

        if ( catid == null ) {
            products = productServiceImp.selectAll();
        } else {
            products = productServiceImp.selectByCatid(Integer.parseInt(catid));
            request.setAttribute("catid" , catid);
        }
        
        request.setAttribute("products" , products);
        RequestDispatcher view = request.getRequestDispatcher("list_product.jsp");
        view.forward(request, response);
    }

}
