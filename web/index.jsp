<%@ page import="model.user.Auction" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/13/2018
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <head>

      <title>Cycla</title>
      <jsp:include page="header.jsp" />

    </head>
  </head>
  <body>


  <jsp:include page="nav.jsp" />


  <div class="container">

    <h3>Auction going on</h3>

    <% List<Auction> auctions = (List<Auction>)request.getAttribute("auctions");%>

    <div class="row">
      <% for (int i = 0; i < auctions.size(); i ++ ) {%>
        <% if ( i % 3 == 0 && i > 0 ) {%>
          </div>
          <div class="row">
        <% } %>
            <div class="column" id="1">
              <h5><%= auctions.get(i).current_product.getName() %>
                <span event="joined" class="label label-success"><%= i%></span>
                <span event="joined" class="label label-warning"><%= auctions.get(i).getMinimumPrice()%>$</span>
              </h5>

              <div class="desc">
               <%= auctions.get(i).current_product.getDesc()%>
              </div>
              <p> <%= auctions.get(i).getStartDate()%> <span event="joined" class="label label-danger">150$</span></p>
              <p> <%= auctions.get(i).getEndDate()%> </p>

              <span class="label label-info">Going on</span>
              <a><button type="button" event="edit" class="btn btn-primary btn-md" data-event="id_event" data-user="user_id" data-toggle="modal" data-target="#myModal<%= i%>" >Bid</button></a>
            </div>

        <% }%>
    </div>
    <% for (int i = 0; i < auctions.size(); i ++ ) {%>
      <div class="modal fade" id="myModal<%= i%>" role="dialog">
        <div class="modal-dialog modal-sm">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title"><%= auctions.get(i).current_product.getName() %></h4>
            </div>
            <div class="modal-body">

                <label for="ex3">Price</label>
                <input class="form-control " id="ex3" type="number">

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-success" data-dismiss="modal">submit</button>

            </div>
          </div>
        </div>
      </div>
    <% }%>

  </div>



  </body>
</html>
