<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">

            <div class="navbar-header">

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/" style="">

                </a>
            </div>

            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <c:if test="${not empty user}">
                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="live">Live Cycling Events</a></li>
                        <li><a href="users">Users</a></li>
                        <li><a href="create">Create event</a></li>
                    </c:if>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                        </c:when>

                        <c:otherwise>
                            <li><a href="/signup"><span class="glyphicon glyphicon-log-in"></span> Sign up </a></li>
                            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </div>
        </div>
    </nav>

</div>

