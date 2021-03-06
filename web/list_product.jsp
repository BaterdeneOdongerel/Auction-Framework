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

            <a href="edit_product?catid=${catid}">
                <button type="button" class="btn btn-primary btn-lg">Add Product</button>
            </a>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>image</th>
                    <th>Status</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${products}" var="p">
                    <tr>

                        <td>${p.name}</td>
                        <td>${p.desc}</td>
                        <td><img src="${p.imagePath}" width="50" height="50"/></td>
                        <td>status</td>

                        <td><a href="/edit_product?id=${p.id}&catid=${catid}"> edit </a></td>
                        <td><a href="/edit_product?delete_id=${p.id}&catid=${catid}"> delete </a></td>

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