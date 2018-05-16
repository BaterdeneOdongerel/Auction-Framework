<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/20/2018
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cycla</title>
    <jsp:include page="header.jsp" />
    <link rel="stylesheet" type="text/css" href="/rsc/css/main.css">
</head>
<body>

<jsp:include page="nav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="back_container">
    <div class="container text-center">
        <div class="body_content text-left col-sm-7">
            <h3>Edit Product</h3>

            <form class="form-horizontal" action="/edit_product" method="post">

                <input type="hidden" value="${product.id}" name="id" class="form-control" id="id"/>

                <div class="form-group">

                    <label class="control-label col-sm-2" for="pname">Product name:</label>

                    <div class="col-sm-9">
                        <input type="text" value="${product.name}" name="pname" class="form-control" id="pname" placeholder="Product name"/>
                    </div>

                </div>


                <div class="form-group">
                    <label class="control-label col-sm-2" for="desc">Product description:</label>
                    <div class="col-sm-9">
                        <textarea cols="3" rows="5"  class="form-control" id="desc" name="desc" >${product.desc}</textarea>
                    </div>
                </div>

                <input type="hidden" value="${product.catid}" name="catid" class="form-control" id="catid"/>
                <input type="hidden" value="${product.user}" name="user" class="form-control" id="user"/>
                <input type="hidden" value="${product.bid_price}" name="bid_price" class="form-control" id="bid_price"/>


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="edit_button" class="btn btn-default btn-lg">submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
