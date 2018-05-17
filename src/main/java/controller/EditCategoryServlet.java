package controller;

import model.Category;
import service.CategoryServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_category")
public class EditCategoryServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryServiceImp categoryServiceImp = new CategoryServiceImp();

        String delete_id = request.getParameter("delete_id");
        if ( delete_id != null ) { // delete
            categoryServiceImp.delete(Integer.parseInt(delete_id));
        }
        else { // update or create
            String sid = request.getParameter("id");
            String name = request.getParameter("catname");
            String desc = request.getParameter("desc");

            Category cat = new Category();
            cat.setName(name);
            cat.setDesc(desc);

            if (sid == null || "-1".equals(sid)) { // create
                categoryServiceImp.create(cat);
            } else { // update
                categoryServiceImp.update(cat, Integer.parseInt(sid));
            }

            request.setAttribute("category", cat);
        }
        response.sendRedirect("/list_category");
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryServiceImp categoryServiceImp = new CategoryServiceImp();
        String delete_id = request.getParameter("delete_id");
        if ( delete_id != null ) { // delete
            categoryServiceImp.delete(Integer.parseInt(delete_id));
            response.sendRedirect("/list_category");
        } else {

            String sid = request.getParameter("id");

            int id = -1;
            Category cat = new Category();

            if (sid != null) {
                id = Integer.parseInt(sid);
                cat = categoryServiceImp.selectById(id);

            }

            cat.setId(id);
            request.setAttribute("category", cat);

            RequestDispatcher view = request.getRequestDispatcher("edit_category.jsp");
            view.forward(request, response);
        }

    }

}
