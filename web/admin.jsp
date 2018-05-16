<%--
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

        <title>Auctions</title>
        <jsp:include page="header.jsp" />

    </head>
</head>
<body>


<jsp:include page="nav.jsp" />


<div class="container">
    <a href="edit_category">
        <button type="button" class="btn btn-primary btn-lg">Add Category</button>
    </a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>products</th>
        </tr>
        </thead>
        <tbody>



        <c:forEach items="${auctions}" var="auction">
            <tr>

                <td>${auction.name}</td>
                <td>${auction.desc}</td>

                <td><a href="/edit_category?id=${cat.id}"> edit </a> </td>
                <td><a href="/edit_category?delete_id=${cat.id}"> delete </a> </td>
                <td><a href="/list_product?catid=${cat.id}"> status  </a> </td>
                <td><a href="/list_product?catid=${cat.id}"> calculate  </a> </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>



</body>
</html>