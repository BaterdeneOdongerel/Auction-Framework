<%--
  Created by IntelliJ IDEA.
  User: Lukman Arogundade
  Date: 5/17/2018
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Auction Management: Create/Edit Auction</title>
    <jsp:include page="header.jsp"/>
    <script src="public/assets/js/jquery.min.js"></script>

    <script src="public/assets/js/jquery-1.12.4.min.js"></script>
    <script src="public/assets/js/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="public/assets/css/jquery-ui.css">

    <script src="public/assets/bootstrap.bundle.min.js"></script>
    <link href="public/assets/css/bootstrap.css" rel="stylesheet">
    <link href="public/assets/css/simple-sidebar.css" rel="stylesheet">
    <script src="public/js/admin.js" rel="stylesheet"></script>
    <link rel="stylesheet" type="text/css" href="/rsc/css/main.css">

    <script>
        $(function () {
            $("#endDate").datepicker();
            $("#startDate").datepicker();
            //$( "#startDate" ).datepicker( "option", "dateFormat", 'yy-mm-dd' );
        });
    </script>
</head>
<body>
<jsp:include page="nav.jsp"/>

<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="sidebar.jsp"/>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <div class="back_container">
            <div class="container text-center">
                <div class="body_content text-left col-sm-7">
                    <h3>

                        <c:if test="${empty auction.id}">
                            Create Auction
                        </c:if>
                        <c:if test="${not empty auction.id}">
                            Edit Auction
                        </c:if>
                    </h3>

                    <form class="form-horizontal" action="/edit_auction" method="post">

                        <input type="hidden" value="${auction.id}" name="id" class="form-control" id="id"/>

                        <div class="form-group">

                            <label class="control-label col-sm-2" for="product">Product</label>

                            <div class="col-sm-9">

                                <select class="form-control" id="product" name="product">

                                    <c:forEach var="item" items="${products}">
                                        <option value="${item.value}">${item.label}</option>
                                    </c:forEach>
                                </select>


                            </div>

                        </div>
                        <div class="form-group">

                            <label class="control-label col-sm-2" for="product">Bid Owner</label>

                            <div class="col-sm-9">

                                <select class="form-control" id="bidOwner" name="bidOwner">

                                    <c:forEach var="item" items="${users}">
                                        <option value="${item.value}">${item.label}</option>
                                    </c:forEach>
                                </select>


                            </div>

                        </div>


                        <div class="form-group">

                            <label class="control-label col-sm-2" for="startDate">Start Date:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.startDate}" name="startDate" class="form-control"
                                       id="startDate" placeholder="MM/dd/yyyy"/>
                            </div>

                        </div>
                        <div class="form-group">

                            <label class="control-label col-sm-2" for="endDate">End Date:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.endDate}" name="endDate" class="form-control"
                                       id="endDate" placeholder="MM/dd/yyyy"/>


                            </div>

                        </div>

                        <!--div class="form-group">

                            <label class="control-label col-sm-2" for="datepicker">Test Date:</label>

                            <div class="col-sm-9">

                                <input type="text" id="datepicker" name ="datepicker"  placeholder="Test Date"
                                       class="form-control"/>
                            </div>

                        </div -->


                        <div class="form-group">

                            <label class="control-label col-sm-2" for="minimumPrice">Minimum Price:</label>

                            <div class="col-sm-9">
                                <input type="text" value="${auction.minimumPrice}" name="minimumPrice"
                                       class="form-control"
                                       id="minimumPrice" placeholder="Minimum Price"/>
                            </div>

                        </div>

                        <div class="form-group">

                            <label class="control-label col-sm-2" for="isRunning">Is Running?:</label>

                            <div class="col-sm-9">

                                <input type="checkbox" value="" name="isRunning"
                                       class="form-control"
                                       id="isRunning"/>

                                <!--input type="checkbox" name="names" value="Yashwant"/>Yashwant
                                <input type="checkbox" name="names" value="Vishal"/>Vishal
                                <input type="checkbox" name="names" value="Suhas"/ Suhas-->
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
