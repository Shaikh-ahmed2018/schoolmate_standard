<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="manifest" href="images/fevicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="theme-color" content="#ffffff">

<title>Schoolmate</title>

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="JS/login/bootstrap-hover-dropdown.min.js"></script>


<link href="CSS/Theme/custome.css" rel="stylesheet">
<link rel="stylesheet" href="SCHOOLINFO/<logic:present name="token_information" scope="session"><bean:write name="token_information" property="domain" /></logic:present>/theme.scss" />
<style type="text/css">
.modal{
display: block;
}
.btn-default{
vertical-align: -1px;
border-radius:5px; 
    padding: 3px 12px;
}
section.content{
margin: 0px;
}
header,footer{
    padding: 20px 10px;
    margin: 0px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	getBranch();
	
	
	var windowheight=($(window).height()-127)+'px';
	$("#main,#mainImage,.carousel-inner").css({
		'height':windowheight
		
	});
	$("#logout").click(function(){
		$(window.location).attr('href', 'login.html?method=logout');
	});
	
});


function getBranch(){
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=getBranch",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			for(var i=0;i<result.jsonResponse.length;i++){
				$("#branch").append("<option value='"+result.jsonResponse[i].locationId+"'>"+result.jsonResponse[i].locationName+"</option>");
			}
			
		}
	});
}
function changeBranch(){
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=changeBranch",
		data:{"branch":$("#branch").val()},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status){
				$("#hschoolLocation").val($("#branch").val());
				window.location.href="menuslist.html?method=Home";
			}
			
				
		}
	});
}
</script>

</head>
<body>
        
    <header class="primarythemebackgound">
    	<div class="primarythemebackgound clearfix">
        	<div class="col-lg-2 col-md-2">
            	<div id="main-logo">
                	<a href="/schoolmate">
                    	<img src="images/Logo/logo.png" alt="logo" width="40%" />
                    </a>
                </div>
            </div>
            
            
        </div>
	</header>       
  
  <div class="modal" id="selectBranch" tabindex="-1" role="dialog" aria-hidden="false">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="primarythemecolor">Select Branch</h4>
      </div>
      <div class="modal-body">
    	
    	
        <div class="actionsBtns">
                <select id="branch">
    			</select>
                <input type="button" class="btn btn-default  primarythemebackgound primarythemecolor" onclick="changeBranch()" data-dismiss="modal" value="Yes" />
	            <button class="btn btn-default primarythemebackgound primarythemecolor" id="logout">Log out</button>
           
        </div>
      </div>
    </div>
  </div>
</div>
  <div id="main">
  <div id="slider-revolution-slider" class="slider slider-revolution-slider revolution-slider">

    <div class="shadowWrapper">



        <!-- START REVOLUTION SLIDER  -->

        <<div id="myCarousel" class="carousel slide" data-ride="carousel">
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
  <footer class="primarythemebackgound">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">

					<div class="copyright">Copyright © 2018 Thought Wings - All Rights Reserved</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="social-icon">
						<div class="row">
							<div class="col-md-6"></div>
							<div class="col-md-6">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</footer>


</body>
</html>