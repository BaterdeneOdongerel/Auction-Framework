<%--
  Created by IntelliJ IDEA.
  User: Lukman Arogundade
  Date: 5/17/2018
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Auction Management</title>
    <jsp:include page="header.jsp"/>
    <script src="public/assets/js/jquery.min.js"></script>
    <script src="public/assets/bootstrap.bundle.min.js"></script>
    <link href="public/assets/css/bootstrap.css" rel="stylesheet">
    <link href="public/assets/css/simple-sidebar.css" rel="stylesheet">
    <script src="public/js/admin.js" rel="stylesheet"></script>
</head>
<body>

<jsp:include page="nav.jsp"/>

<div id="wrapper">

<!-- Sidebar -->
<jsp:include page="sidebar.jsp"/>
<!-- /#sidebar-wrapper -->

<!-- Page Content -->
<div id="page-content-wrapper">
    <div class="container">

        <a href="edit_auction">
            <button type="button" class="btn btn-primary btn-lg">Create Auction</button>
        </a>
        <a href="calculate_auctions">
            <button type="button" class="btn btn-primary btn-lg">Calculate Auctions</button>
        </a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Product</th>
                <th>Bid Owner</th>
                <th>Minimum Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <!--td>Image</td -->
                <td>Status</td>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${auctions}" var="a">
                <tr>

                    <td>${a.current_product.name}</td>
                    <td>${a.bidOwnerUser.firstName} ${a.bidOwnerUser.lastName}</td>
                    <td>${a.minimumPrice}</td>
                    <td>${a.startDate}</td>
                    <td>${a.endDate}</td>
                    <td>${a.status}</td>

                    <td><a href="/edit_auction?id=${a.id}"> Edit </a></td>
                    <td><a href="/edit_auction?delete_id=${a.id}"> Delete </a></td>

                </tr>
            </c:forEach>

            </tbody>
        </table>

    </div>
</div>
<!-- /#page-content-wrapper -->

</div>


</body>
</html>
