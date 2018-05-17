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
    <title>Auction</title>
    <jsp:include page="header.jsp" /><title>Auction</title>
    <script src="public/assets/js/jquery.min.js"></script>
    <script src="public/assets/bootstrap.bundle.min.js"></script>
    <link href="public/assets/css/bootstrap.css" rel="stylesheet">
    <link href="public/assets/css/simple-sidebar.css" rel="stylesheet">
    <script src="public/js/admin.js" rel="stylesheet"></script>
</head>
<body>


<jsp:include page="nav.jsp" />


<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="sidebar.jsp"/>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Content</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>



                <c:forEach items="${events}" var="event">
                    <tr>

                        <td>${event.name}</td>
                        <td>${event.content}</td>
                        <td>${event.created}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>