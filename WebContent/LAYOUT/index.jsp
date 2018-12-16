<!DOCTYPE html>
<html>
<head>


<link rel="icon" type="image/png" sizes="96x96"
	href="images/fevicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/fevicon/favicon-16x16.png">

<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link rel="stylesheet" type="text/css" href="CSS/newUI/font-awesome/css/guest.css" />
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.css">
<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.min.css">
<link type="text/css" rel="stylesheet"	href="CSS/newUI/bootstrap.min.css">

<link rel="stylesheet" href="CSS/login/font-awesome.min.css">


<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<!-- <script type="text/javascript" src="JS/slider.js"></script> -->

<script>
var publicIp = false;
function getip(ip) {
  publicIp = ip.ip;
  public_ip.textContent = publicIp;
  setTimeout(function(){extract_ipv4("")}, 1000);
}
</script>
 <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<script type="text/javascript" src="JS/login/Admin.js"></script>
<script src="JS/login/SystemDetail.js"></script>
<script>
url=window.location.href.substring(window.location.href.lastIndexOf("//")+1);
url=url.split(":")[0];
$("head").append('<link rel="stylesheet" href="SCHOOLINFO'+url+'/theme.scss" />');
$(document).ready(function() {
	
$('input,textarea').bind('cut copy paste', function (e) {
    e.preventDefault(); //disable cut,copy,paste
});
});
function noBack() { window.history.forward(); }



</script>

<style>
a{
display: inline-block;
}
h2 {
	color:#000;
	
	font-family: Roboto Regular;
	margin: 0;
	width: 100%;
	text-align: center
}

.up3:after {
	background: none repeat scroll 0 0 rgb(255, 255, 255);
	bottom: 0;
	content: "";
	display: block;
	height: 1px;
	margin-top: 4px;
	width: 100%;
	float: left;
	z-index: 9999;
}

.up4:after {
	background: none repeat scroll 0 0 rgb(255, 255, 255);
	bottom: 0;
	content: "";
	display: block;
	height: 1px;
	margin-top: -40px;
	width: 100%;
	z-index: 9999;
}

#main{
position: relative;
}


.successmessagediv {
	position: absolute;
	left: 40%;
	margin: 0 auto;
	color: #fff;
	font-size: 14px;
	font-weight: bold;
	top: 30%;
	z-index: 99999;
	background: #07aab9;
	padding:20px;
	}
	footer{
	margin-top: 0px;
	}
	
	.container{
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom:0;
	margin: auto;
	height: 310px;
	}
	#roller{
	list-style: none;
	}
	#roller li{
	display: inline-block;
	height:120px;
	width:120px;
	padding: 15px;
	}
	#roller li img{
	width: 100%;
	}
	#clientRoller{
	position: absolute;
	bottom: 0px;
	height: 120px;
	}
	.loginlogo{
		text-align: center;
	}
	</style>
		<title>shoolmate</title>
	</head>

<body class="login-out">

		
    <div id="main">
    
    
    
    
    <div id="overlay"></div>
    	
    	<form  action="login.html?method=login" method="post" method="post">
        	<div class="row">
        	 <div class="col-lg-9 col-md-9 col-sm-8">
        	 
        	 <div id="slider-revolution-slider" class="slider slider-revolution-slider revolution-slider">

    <div class="shadowWrapper">



        <!-- START REVOLUTION SLIDER  -->

    
   
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="./images/slider/slide1.jpg" alt="" style="width:100%;">
      </div>

      <div class="item">
        <img src="./images/slider/slide2.jpg" alt="" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="./images/slider/slide1.jpg" alt="" style="width:100%;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>



    <!-- END REVOLUTION SLIDER -->

    </div>

</div>
        	 </div>
        	<div class="col-lg-3 col-md-3 col-sm-4" style="background: #fff;min-height: 310px;padding: 65px;">
        	<h2 >Sign In</h2>
        	<div class="loginlogo">
        	<img src="./images/Logo/logo.png" />
        	</div>
        	<div id="login-form">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input type="text"
									class="form-control" name="username" id="user_name"
									placeholder="User Name">
							</div>
						
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock icon-color"></span></span> <input type="password"
									class="form-control" name="password" id="user_password"
									placeholder="Password">
							</div>
							<br />
							<span id="public_ip" style="display: none;"></span>
							<input type="hidden" id="internal_ip" name="internal_ip" value="" />
						
							
							<br />  <!-- <a class=""
								href="registration.html?method=NewRegistrationEntryPage">Temporary
								Registration</a> -->
							<!-- 	<a class="" href="#">Forgot
								Password ?</a> -->

							<center>
								<span style="padding-left: 10%; color: red;" class="loginTips"></span>
							</center>
							</div>
							
							<button class="buttons primarythemebackgound primarythemecolor login-button" type="submit"
								onclick="return validateFields()"
								style="border-radius: 5px;font-family: Roboto Medium;width: 90%;padding: 8px;border:  none;">
								<i class="fa fa-sign-in "></i> Login
							</button>
        	</div>
        	
				
				
				 
				 </div>
				

			
			</form>
		</div>
		
		
		
	

</body>
</html>
