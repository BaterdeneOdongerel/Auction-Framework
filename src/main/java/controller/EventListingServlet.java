package controller;


import model.Category;
import model.Event;
import service.CategoryServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list_event")
public class EventListingServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryServiceImp categoryServiceImp = new CategoryServiceImp();
        List<Event> events = Services.EventService.selectAll();
        request.setAttribute("events", events);
        RequestDispatcher view = request.getRequestDispatcher("list_event.jsp");
        view.forward(request, response);
    }

}
