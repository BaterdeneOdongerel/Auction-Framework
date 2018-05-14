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
    <head>

        <title>Cycla</title>
        <jsp:include page="header.jsp" />

    </head>
</head>
<body>


<jsp:include page="nav.jsp" />


<div class="container">
    <h2>Template to use</h2>
    <p>The .table-condensed class makes a table more compact by cutting cell padding in half:</p>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>john@example.com</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>mary@example.com</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>july@example.com</td>
        </tr>
        </tbody>
    </table>
</div>
<h2>Thumbnail</h2>
<p>The .img-thumbnail class creates a thumbnail of the image:</p>
<img src="./public/images/img1.jpg" class="img-thumbnail" alt="Cinque Terre" width="304" height="236">

<div class="page-header">
    <h1>Example Page Header</h1>

</div>

<h2>Well Size</h2>
<div class="well well-sm">Small Well</div>
<div class="well">Normal Well</div>
<div class="well well-lg">Large Well</div>

<h2>Animated Alerts</h2>
<p>The .fade and .in classes adds a fading effect when closing the alert message.</p>
<div class="alert alert-success alert-dismissible fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Success!</strong> This alert box could indicate a successful or positive action.
</div>
<div class="alert alert-info alert-dismissible fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Info!</strong> This alert box could indicate a neutral informative change or action.
</div>

<div class="alert alert-warning alert-dismissible fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Warning!</strong> This alert box could indicate a warning that might need attention.
</div>

<div class="alert alert-danger alert-dismissible fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Danger!</strong> This alert box could indicate a dangerous or potentially negative action.
</div>

<h1> Botton styles</h1>
<button type="button" class="btn">Basic</button>
<button type="button" class="btn btn-default btn-md">Default</button>
<button type="button" class="btn btn-primary btn-lg">Primary</button>
<button type="button" class="btn btn-success btn-sm">Success</button>
<button type="button" class="btn btn-info">Info</button>
<button type="button" class="btn btn-warning">Warning</button>
<button type="button" class="btn btn-danger">Danger</button>
<button type="button" class="btn btn-link">Link</button>

<h2>Glyphicon Examples</h2>

<p>Envelope icon as a link:
    <a href="#"><span class="glyphicon glyphicon-envelope"></span></a>
</p>
<p>Search icon: <span class="glyphicon glyphicon-search"></span></p>
<p>Search icon on a button:
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-search"></span> Search
    </button>
</p>
<p>Search icon on a styled button:
    <button type="button" class="btn btn-info">
        <span class="glyphicon glyphicon-search"></span> Search
    </button>
</p>
<h2>Progress bar</h2>
<div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar"
         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        40%
    </div>
</div>
<h2> Panel </h2>
<div class="panel panel-default">
    <div class="panel-body">Panel Content</div>
    <div class="panel-footer">Panel Footer</div>
</div>

<h2> Menu bar</h2>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">Menu 1</a></li>
    <li><a href="#">Menu 2</a></li>
    <li><a href="#">Menu 3</a></li>
</ul>

<h2>
    Form example
</h2>
<form class="form-horizontal" action="/action_page.php">
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="email" placeholder="Enter email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Password:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="pwd" placeholder="Enter password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>
        </div>

        <div class="col-xs-2">
            <label for="ex1">col-xs-2</label>
            <input class="form-control" id="ex1" type="text">
        </div>
        <div class="col-xs-3">
            <label for="ex2">col-xs-3</label>
            <input class="form-control" id="ex2" type="text">
        </div>
        <div class="col-xs-4">
            <label for="ex3">col-xs-4</label>
            <input class="form-control" id="ex3" type="text">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Submit</button>
        </div>

    </div>
    <span class="label label-default">Default Label</span>
    <span class="label label-primary">Primary Label</span>
    <span class="label label-success">Success Label</span>
    <span class="label label-info">Info Label</span>
    <span class="label label-warning">Warning Label</span>
    <span class="label label-danger">Danger Label</span>

</form>
<h2>Tool tip </h2>

<a href="#" data-toggle="tooltip" data-placement="top" title="Hooray!">Hover</a>
<a href="#" data-toggle="tooltip" data-placement="bottom" title="Hooray!">Hover</a>
<a href="#" data-toggle="tooltip" data-placement="left" title="Hooray!">Hover</a>
<a href="#" data-toggle="tooltip" data-placement="right" title="Hooray!">Hover</a>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>





</body>
</html>
