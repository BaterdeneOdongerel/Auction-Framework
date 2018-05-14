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

            </div>

            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">

                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="live">Live Cycling Events</a></li>
                        <li><a href="users">Users</a></li>
                        <li><a href="create">Create event</a></li>


                </ul>
                <ul class="nav navbar-nav navbar-right">


                            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                            <li><a href="/signup"><span class="glyphicon glyphicon-log-in"></span> Sign up </a></li>
                            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>


                </ul>
            </div>
        </div>
    </nav>

</div>

