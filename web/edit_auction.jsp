<%--
  Created by IntelliJ IDEA.
  User: Lukman Arogundade
  Date: 5/17/2018
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Auction Management: Create/Edit Auction</title>
    <jsp:include page="header.jsp"/>
    <script src="public/assets/js/jquery.min.js"></script>
    <script src="public/assets/bootstrap.bundle.min.js"></script>
    <link href="public/assets/css/bootstrap.css" rel="stylesheet">
    <link href="public/assets/css/simple-sidebar.css" rel="stylesheet">
    <script src="public/js/admin.js" rel="stylesheet"></script>
    <link rel="stylesheet" type="text/css" href="/rsc/css/main.css">
</head>
<body>
<jsp:include page="nav.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="sidebar.jsp"/>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <div class="back_container">
            <div class="container text-center">
                <div class="body_content text-left col-sm-7">
                    <h3>Edit Auction</h3>

                    <form class="form-horizontal" action="/edit_auction" method="post">

                        <input type="hidden" value="${auction.id}" name="id" class="form-control" id="id"/>

                        <div class="form-group">

                            <label class="control-label col-sm-2" for="product">Product</label>

                            <div class="col-sm-9">

                                <select class="form-control" id="product" name="product">
                                    <options items="${products}" itemValue="value" itemLabel="label"/>
                                </select>


                            </div>

                        </div>
                        <div class="form-group">

                            <label class="control-label col-sm-2" for="product">Bid Owner</label>

                            <div class="col-sm-9">

                                <select class="form-control" id="bidOwner" name="bidOwner">
                                    <options items="${users}" itemValue="value" itemLabel="label"/>
                                </select>


                            </div>

                        </div>


                        <div class="form-group">

                            <label class="control-label col-sm-2" for="startDate">Start Date:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.startDate}" name="startDate" class="form-control"
                                       id="startDate" placeholder="Start Date"/>
                            </div>

                        </div>
                        <div class="form-group">

                            <label class="control-label col-sm-2" for="endDate">Start Date:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.endDate}" name="endDate" class="form-control"
                                       id="endDate" placeholder="End Date"/>
                            </div>

                        </div>


                        <div class="form-group">

                            <label class="control-label col-sm-2" for="minimumPrice">Start Date:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.minimumPrice}" name="minimumPrice"
                                       class="form-control"
                                       id="minimumPrice" placeholder="Minimum Price"/>
                            </div>

                        </div>


                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="edit_button" class="btn btn-default btn-lg">submit</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
