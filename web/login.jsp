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
 </head>
<body>

<jsp:include page="nav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="back_container">
    <div class="container text-center">
        <div class="body_content text-left col-sm-7">
            <h3>Login</h3>

            <form class="form-horizontal" action="/login" method="post">

                <c:if test="${error!='m'}">
                    <div class="alert alert-warning alert-dismissible fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Warning!</strong> ${error}
                    </div>
                </c:if>


                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Email:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="email" id="email" placeholder="Enter email">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">Password:</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password" name="password" placeholder="password">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default btn-lg">Login</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
