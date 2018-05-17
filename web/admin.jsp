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
        <script src="public/assets/js/jquery.min.js"></script>
        <script src="public/assets/bootstrap.bundle.min.js"></script>
        <link href="public/assets/css/bootstrap.css" rel="stylesheet">
        <link href="public/assets/css/simple-sidebar.css" rel="stylesheet">
        <script src="public/js/admin.js" rel="stylesheet"></script>
    </head>
</head>
<body>


<jsp:include page="nav.jsp" />


<div class="container">
    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Start Bootstrap
                    </a>
                </li>
                <li>
                    <a href="/list_product">Products</a>
                </li>
                <li>
                    <a href="/list_category">Caterory</a>
                </li>
                <li>
                    <a href="/list_auction">Auction</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
</div>



</body>
</html>