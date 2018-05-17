<%@ page import="model.user.Auction" %>
<%@ page import="java.util.List" %><%--
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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ShapedTheme">

  <!-- favicon icon -->
  <link rel="shortcut icon" href="assets/images/">

  <title></title>

  <!-- common css -->
  <link rel="stylesheet" href="public/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="public/assets/css/font-awesome.min.css">
  <link rel="stylesheet" href="public/assets/css/animate.min.css">
  <link rel="stylesheet" href="public/assets/css/owl.carousel.css">
  <link rel="stylesheet" href="public/assets/css/owl.theme.css">
  <link rel="stylesheet" href="public/css/style.css">
  <link rel="stylesheet" href="public/assets/css/responsive.css">

  <!-- HTML5 shim and Respond.js IE9 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="public/assets/js/html5shiv.js"></script>
  <script src="public/assets/js/respond.js"></script>
  <![endif]-->
</head>

<body>

<!-- header area-->
<header>
  <div class="welcome-text-area">
    <div class="container">
      <div class="pull-left welcome-text">
        <p>Welcome to Auction X <a href="/login">Sign in</a> or <a href="signup">Register</a></p>
      </div>


    </div>
  </div>


</header>

<!-- home area-->
<section id="home">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-1 col-md-7">
        <div class="home-content">
          <h2 class="text-uppercase">Auction <span>X</span></h2>
          <h3 class="text">Latest trendy clothes with a price of your next coffee cup</h3>
        </div>

      </div>
    </div>
  </div>
</section>

<section class="arrival-area">
  <div class="container">

    <div class="arrival-menu">
      <div class="pull-left arrival-text">
        <h3 class="text-uppercase">Products</h3>
      </div>
      <%--<div class="nav-center text-uppercase">
        <ul class="nav  nav-pills text-center">

          <li class="active"><a data-toggle="tab" href="#men"> MEN</a></li>
          <li><a data-toggle="tab" href="#women">WOMEN</a></li>
          <li><a data-toggle="tab" href="#accesories">ACCESSORIES</a></li>
          <li><a data-toggle="tab" href="#kid"> KID</a></li>
        </ul>
      </div>--%>
    </div>
  </div>
</section>
<!-- arrival tab area-->
<div class="container">
  <div class="row">
    <div class="tab-content">
      <div id="men" class="tab-pane fade in active">
        <div class="arrival-item">
          <div>

            <c:forEach items="${products}" var="p">
              <div class="single-product-area">
                <div class="single-shop">
                  <a href="single-shop.html">
                    <img src="public/assets/images/product-1.jpg" alt="">
                  </a>
                  <div class="shop-icon">
                    <ul>
                      <li><a href="single-shop.html"> <i class="fa fa-cart-plus"></i> </a></li>
                      <li><a href=""> <i class="fa fa-heart-o"></i></a></li>
                      <li><a href="single-shop.html"> <i class="fa fa-chain"></i></a></li>
                    </ul>

                  </div>

                </div>
                <div class="single_featured_label text-center">
                  <h3>{p.name}</h3>
                  <h4>
                    <del>{p.bid_price}</del>

                  </h4>
                  <a href="single-shop.html" class="add-cart text-uppercase"> Bid</a>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>

      </div>
    </div>

    <h3></h3>

      <% List<Auction> auctions = (List<Auction>) request.getAttribute("auctions");%>

    <div class="row">
        <% for (int i = 0; i < auctions.size(); i++) {%>
        <% if (i % 3 == 0 && i > 0) {%>
    </div>
      <div class="row">
          <% } %>
          <div class="column" id="1">
              <h5><%= auctions.get(i).current_product.getName() %>
                  <span event="joined" class="label label-success"><%= i%></span>
                  <span event="joined" class="label label-warning"><%= auctions.get(i).getMinimumPrice()%>$</span>
              </h5>

              <div class="desc">
                  <%= auctions.get(i).current_product.getDesc()%>
              </div>
              <p><%= auctions.get(i).getStartDate()%> <span event="joined" class="label label-danger">150$</span></p>
              <p><%= auctions.get(i).getEndDate()%>
              </p>

              <span class="label label-info">Going on</span>
              <a>
                  <button type="button" event="edit" class="btn btn-primary btn-md" data-event="id_event"
                          data-user="user_id" data-toggle="modal" data-target="#myModal<%= i%>">Bid
                  </button>
              </a>
          </div>

          <% }%>
      </div>
      <% for (int i = 0; i < auctions.size(); i++) {%>
      <div class="modal fade" id="myModal<%= i%>" role="dialog">
          <div class="modal-dialog modal-sm">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title"><%= auctions.get(i).current_product.getName() %>
                      </h4>
                  </div>
                  <div class="modal-body">

                      <label for="ex3">Price</label>
                      <input class="form-control " id="ex3" type="number">

                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-success" data-dismiss="modal">submit</button>

                  </div>
              </div>
        </div>
      </div>
      <% }%>

  </div>
</div>
<footer class="copy text-center">
  <p>&copy; 2018 Advanced Software Development</p>
</footer>
<!-- js files -->
<script type="text/javascript" src="public/assets/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="public/assets/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="public/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="public/assets/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="public/assets/js/jquery.countdown.min.js"></script>
<script type="text/javascript" src="public/assets/js/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src="public/assets/js/scripts.js"></script>

</body>
</html>
