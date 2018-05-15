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

    <title>Cycla - Create Event</title>
    <jsp:include page="header.jsp" />

</head>
<body>

<jsp:include page="nav.jsp" />

<div class="back_container">
    <div class="container text-center">
        <div class="body_content text-left">
            <div class="alert alert-danger alert-dismissible fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Server encounter an error. Please click on the button below to go back to the home page!</strong>
                <a href="/" class="btn btn-primary">Home Page</a>
            </div>


        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
