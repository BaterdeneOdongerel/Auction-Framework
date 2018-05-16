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

@WebServlet("/edit_product")
public class EditProductServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServiceImp productServiceImp = new ProductServiceImp();

         // update or create
        String sid = request.getParameter("id");
        String name = request.getParameter("pname");
        String desc = request.getParameter("desc");
        String catid = request.getParameter("catid");
        String user = request.getParameter("user");
        String bid_price = request.getParameter("bid_price");


        Product product = new Product();
        product.setName(name);
        product.setDesc(desc);
        product.setCatid(Integer.parseInt(catid));
        product.setUser(Integer.parseInt(user));
        product.setBid_price(Integer.parseInt(bid_price));


        if ( sid == null || "-1".equals(sid) ) { // create
            productServiceImp.create(product);
        } else { // update
            productServiceImp.update(product, Integer.parseInt(sid));
        }
        response.sendRedirect("/list_product?catid=" + catid);
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductServiceImp productServiceImp = new ProductServiceImp();
        String delete_id = request.getParameter("delete_id");
        String catid = request.getParameter("catid");
        if ( delete_id != null ) { // delete
            Product p = productServiceImp.selectById(Integer.parseInt(delete_id));
            productServiceImp.delete( Integer.parseInt(delete_id) );
            if ( catid == null )
                catid = "" + p.getCatid();
            response.sendRedirect("/list_product?catid=" + catid);

        } else {

            String sid = request.getParameter("id");

            int id = -1;
            Product product = new Product();

            if ( sid != null ) {
                id = Integer.parseInt(sid);
                product = productServiceImp.selectById(id);
            } else {
                product.setCatid(Integer.parseInt(catid));
            }

            product.setId(id);
            request.setAttribute("product", product);

            RequestDispatcher view = request.getRequestDispatcher("edit_product.jsp");
            view.forward(request, response);
        }

    }

}

