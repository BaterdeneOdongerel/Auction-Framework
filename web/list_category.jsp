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



                <c:forEach items="${categories}" var="cat">
                    <tr>

                        <td>${cat.name}</td>
                        <td>${cat.desc}</td>

                        <td><a href="/edit_category?id=${cat.id}"> edit </a> </td>
                        <td><a href="/edit_category?delete_id=${cat.id}"> delete </a> </td>
                        <td><a href="/list_product?catid=${cat.id}"> products  </a> </td>
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