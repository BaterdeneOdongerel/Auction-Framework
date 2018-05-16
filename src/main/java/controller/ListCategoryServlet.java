package controller;


import model.user.Category;
import service.CategoryServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list_category")
public class ListCategoryServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryServiceImp categoryServiceImp = new CategoryServiceImp();
        List<Category> categories = categoryServiceImp.selectAll();
        request.setAttribute("categories" , categories);



        RequestDispatcher view = request.getRequestDispatcher("list_category.jsp");
        view.forward(request, response);
    }

}
