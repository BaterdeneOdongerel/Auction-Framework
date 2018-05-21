package controller;


import service.Services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_user")
public class DeleteUserServlet extends BaseServlet {
    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Services.UserService.delete(Integer.parseInt(id));
        response.sendRedirect("/list_user");
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
    }
}
