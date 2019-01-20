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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/headerIcon.css" rel="stylesheet" /> 
<script src="JS/backOffice/menuHighlight.js"></script>
<script src="JS/login/Admin.js"></script>
<script src="JS/login/bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/ChangeBackgroundPage.js"></script>
<link href="CSS/Theme/custome.css" rel="stylesheet">
  <!-- dynamic pie/bar chart loadings(below) -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>   <!-- //pie charts -->
<script  type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
 <script type = "text/javascript">
         google.charts.load('current', {packages: ['corechart']});     
      </script>
  <script type="text/javascript" src="JS/spectrum.js"></script>
 <link href="CSS/spectrum.css" rel="stylesheet" />

		
<!-- TRANSFORT  -->
<script type="text/javascript">
        $(document).ready(function() {
                $("#dropdown").click(function() {
                    $("#settingbox").slideUp("fast",function(){
                    	 $("#hbox").slideToggle("slow");
                   	
                   });
                });
                $("#dropdownsettings").click(function() {
                    $("#hbox").slideUp("fast",function(){
                    	 $("#settingbox").slideToggle("slow");
                    	
                    });
            });

        });
        function appendAccYear() {
                var currentYear = $("#globalAcademicYear").val();
                $("#globalAcademicYear option[value=" + currentYear + "]").attr(
                                'selected', 'true');
        }

        function setAcademicYear() {
                $(window.location).attr(
                                'href',
                                'login.html?method=login&accYear='
                                                + $("#globalAcademicYear").val() + '&school='
                                                + $("#school").val());
        }
        function setSchool() {
                $(window.location).attr(
                                'href',
                                'login.html?method=login&accYear='
                                                + $("#globalAcademicYear").val() + '&school='
                                                + $("#school").val());
        }

</script>




<!-- tooltip -->


<!-- for top icon -->

<script type="text/javascript">

        function clickHandler() {
        	$(".logo").toggleClass("logoBrand");
        	$(".headerNavBarS").fadeToggle("fast");
        		$('.lftnav').toggleClass("smallwidth");
        		$(".bodycontent").toggleClass("widewidth");
              
        }

        $(document).ready(function() {

                $('#studenttxt').on('click', clickHandler);
                
                $(".headerLogoutBlock").click(function(){
                	$(".profiledropdown").fadeToggle();
                });
                $('.leftmenu').css('min-height', (window.innerHeight-57)+'px');
                $('.leftmenu').css('height', $("body").css("height"));
               
        });
</script>

<script>
        $(document).ready(function() {

                //Check to see if the window is top if not then display button
                $(window).scroll(function() {
                        if ($(this).scrollTop() > 10) {
                                $('.scrollToTop').fadeIn();
                        } else {
                                $('.scrollToTop').fadeOut();
                        }
                });

                //Click event to scroll to top
                $('.scrollToTop').click(function() {
                        $('html, body').animate({
                                scrollTop : 0
                        }, 800);
                        return false;
                });

        });
</script>

<script>

function startTime() 
{
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
	    h = checkHour(h);
	    h = checkTime(h);
	    m = checkTime(m);
	    s = checkTime(s);
	    if(h=="00"){
	    	h = "12";
	    }
       document.getElementById('txt').innerHTML =
          h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function checkHour(h){
	h = h % 12 ; 
	return h ;
}

</script>


<style>
.cpheader{
	height: 46px;
    width: 365px;
    margin-left: -12px;
    margin-top: 3px;
    margin-bottom: 12px;
}
.scrollToTop {
        width: 100px;
        height: 130px;
        padding: 10px;
        text-align: center;
        font-weight: bold;
        color: #444;
        text-decoration: none;
        position: fixed;
        top: 590px;
        right: -24px;
        display: none;
        z-index: 999;
        /*background: url('arrow_up.png') no-repeat 0px 20px;*/
}
.hoverColor{
margin: 0px;
}
.scrollToTop:hover {
        text-decoration: none;
}

.hoverColor:HOVER {
        text-decoration: underline;
     
        cursor: pointer;
}
@media (min-width: 768px){
.modal-sm {
    width: 300px !important;
}
}

.actionsBtns .btn {
padding: 0 12px !important;
}
.fa-question-circle{
color: #0000ff;
}
.tooltiptext{
display: none;
}
#settingbox{
display: none;

}

#dropdownsettings{
font-size: 24px;
}
</style>
</head>
<body>
        <nav class="navbar navbar-inverse navbar-fixed-top globalNavBar"  role="navigation">

                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header primarythemebackgound">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span> <span
                                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                                        class="icon-bar"></span>
                        </button>
                       


                </div>

                <div class="row" id="main-header">
                <div class="lftnav col-sm-2 smallwidth">
                  <div class="logo-wrrapper">
                  <a class="logo logoBrand"   href="menuslist.html?method=Home"><img src="images/Logo/logo.png" alt="logo" style="width: 32%;" /></a>
                  </div>
                 
                </div>
                <div class="col-md-1 col-sm-1">
                <span class="glyphicon glyphicon-menu-hamburger" id="studenttxt"
				style="font-size: 20px; line-height: 2.5; color: #fff;"></span>
                </div>
                    <div class="container col-md-7 col-sm-7" style="text-align: center;font-size: 24px;">


									 <span id="schoolText" class="primarythemecolor"></span>
									<div class="acadamicYaerleft">
									
                                                        <select style="display: none;" name="academicYear" onkeypress="HideError()"
                                                                id="globalAcademicYear" class="form-control"
                                                                style="min-width: 100px; max-height: 30px; padding: 0px 10px; -webkit-appearance: none; -moz-appearance: none; border: none; background: transparent;"
                                                                onchange="setAcademicYear()">
                                                                <logic:present name="academicYear" scope="session">
                                                                        <logic:iterate id="academicYear" name="academicYear">
                                                                                <option value='<bean:write name="academicYear" property='accId'/>'><bean:write name="academicYear" property='accName'/></option>
                                                                        </logic:iterate>
                                                                </logic:present>
                                                                
                                                                <option></option>
                                                                
                                                        </select>
                                                </div>

                                

                        </div>

                  <div class="col-md-2 col-sm-2 headerLogout">
                           <div> 
                               <div class="profilediv"> 
                           <label class="headerLogoutBlock primarythemecolor"><span class="headerLogoutBlockSpan primarythemecolor" ><logic:present
                                          name="TEACHERNAMEDETAILS" scope="session">
                                          <bean:write name="TEACHERNAMEDETAILS" scope="session" />
                                  </logic:present></span> | <span id="acadamicyeartext"  style="color: #fff;"></span>
                                  
                            </label>
                            <i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
                            <div class="profiledropdown">
                            <div class="row">
                              	<div class="col-xs-6 txtright">
                              		<div id="dropdownsettings"  class="fa fa-gear headerSettingIcon primarythemecolor"></div>
                              	</div>
                              	<div class="col-xs-6 txtleft">
                              	<div id="dropdown"  class="glyphicon glyphicon-off headerLogoutIcon primarythemecolor"></div>
                              	</div>
                              	
                              	
                               </div>
                            </div>
                                                
                              </div> 
                               
                      
                             </div>    
                  </div>
                </div>
               <div class="row cntrhdr">

                                        <input type="hidden" id="hschoolLocation"
                                                value="<logic:present name="current_schoolLocation" scope="session"><bean:write name="current_schoolLocation"  scope="session"/></logic:present>" />
                                        <input type="hidden" id="hacademicyaer"
                                                value="<logic:present name="current_academicYear" scope="session"><bean:write name="current_academicYear"  scope="session"/></logic:present>" />
                                        <input type="hidden" id="huserType"
                                                value="<logic:present name="user" scope="session"><bean:write name="user"  scope="session"/></logic:present>" />
                                        <input type="hidden" id="hPriveliges"
                                                value="<logic:present name="Priveliges" scope="session"><bean:write name="Priveliges" scope="session" /></logic:present>" />

                                        

                               
                                <logic:present name="highlightName" scope="request">
                                        <input id="highlightName" type="hidden"
                                                value='<bean:write name="highlightName" scope="request"/>' />
                                </logic:present>
                                <logic:present name="subModuleHighlightName" scope="request">
                                        <input id="subModuleHighlightName" type="hidden"
                                                value='<bean:write name="subModuleHighlightName" scope="request"/>' />
                                </logic:present>


                                        <input type="hidden" id="hacademicyaer"
                                                value="<logic:present name="current_academicYear" scope="session"><bean:write name="current_academicYear"  scope="session"/></logic:present>" />
                                        <input type="hidden" id="hPriveliges"
                                                value="<logic:present name="Priveliges" scope="session"><bean:write name="Priveliges" scope="session" /></logic:present>" />

                                        
                                                        <input type="hidden" name="school"  id="school"/>
                                                       
                                            

                               
                        <div id="hbox" class="col-md-2">
                                <!-- <div class="gb_Wa"></div>
                                <div class="gb_Va"></div> -->
                                
                                  <p style="font-size: 13px;text-align:center;padding:5px;" class="hoverColor" data-toggle="modal" data-target="#change_password">Change Password</p>
                                  <p style="font-size: 13px;text-align:center;padding:5px;" data-toggle="modal" data-target="#logoutModal" class="hoverColor">Logout</p>
                               
                        </div>

 				<div id="settingbox" class="col-md-2">
                                <!-- <div class="gb_Wa"></div>
                                <div class="gb_Va"></div> -->
                                
                                <p id="changeBranching" style="font-size: 13px;text-align:center;padding:5px;" class="hoverColor" data-toggle="modal" data-target="#selectBranch">Change Branch</p>
                                <p style="font-size: 13px;text-align:center;padding:5px;" class="hoverColor" data-toggle="modal" data-target="#changeThemeColor">Change Theme</p>
                               	
                        </div>


                </div>

        </nav>
             
             <div class="successmessagediv1" style="display: none;">
                        <div class="message-item">
                                <a href="#" class="msg-success bg-msg-succes"><span id="sucessmessage"> </span></a>
                        </div>
                </div>  
             
        <div class="modal fade" id="change_password">
        
                <div class="modal-dialog">
                        <!-- Modal Content -->
                        <div class="modal-content col-md-12" style=" z-index: 1041; border: solid 1px #5d5454;height: 240px; width:72%;margin-left: 15%;">
                                <!-- Close Button -->
                                <!-- <button type="button" class="close" data-dismiss="modal">
                                        <span style="margin-left:-24px;">&times;</span>
                                </button> -->
                                <!-- Heading -->
                                
                                <div class="buttons cpheader">
                                <h3 style="text-align:center;font-size: 16pt !important;font-family: sans-serif !important;    margin-top: 2px;" class="buttons">Change Password</h3>
                                </div>
                                <!-- Form -->
                                <form>
                                        
                                        <!-- Password -->
                                        <div class="form-group clearfix">
                                                <!-- <span class="col-md-4"><label>Old Password</label></span> --><!-- <lable class="form-lable"> --> </lable><input type="password" style="border-radius: 4px !important;"
                                                        id="oldpassword" class="form-control col-md-5" placeholder="Old Password">
                                        </div>
                                        <!-- Password -->
                                        <div class="form-group clearfix">
                             	 <!-- <span class="col-md-4"><label>New Password</label></span> --><input type="password" style="border-radius: 4px !important;" id="newPassword" class="form-control col-md-5" placeholder="New Password">
                                        </div>
                                        <!-- Confirm Password -->
                                        <div class="form-group clearfix">
                                                        <!-- <span class="col-md-4"><label>Confirm Password</label></span> --> <input type="password"
                                                        id="confirmPassword" class="form-control col-md-5" style="border-radius: 4px !important;font-weight: normal;"
                                                        placeholder="Confirm Password">
                                        </div>
                                        <!-- Submit Button -->
										 <div style="text-align: center;color:red;position: relative;display: none;" class="form-group col-md-12 clearfix" id="errormsgdiv">
                                         <span class="errormsg"></span>
                                        </div>


                                        <div style="text-align: center;position: absolute;bottom:15px;" class="form-group col-md-11 clearfix">
                                                 <span class="buttons" style="font-weight: normal;" onclick="changePassword()">Submit</span>
                                               <span class="buttons" data-dismiss="modal" style="font-weight: normal;" id="cancelcp">Cancel</span>
                                        </div>
                                        <div class="modal" tabindex="-1" role="dialog">
  										<div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title">Change Password</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
									        <p>Modal body text goes here.</p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-primary">Save changes</button>
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
									      </div>
									    </div>
  										</div>
										</div>
                                        
                                        
                                </form>
                        </div>
                </div>
        </div>
  
  <div class="modal" id="changeThemeColor" tabindex="-1" role="dialog" aria-hidden="false">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4>Change Theme background </h4>
      </div>
      <div class="modal-body">
    	
    	
        <div class="actionsBtns">
                <div class="header-color theme">
							<span class="fa fa-eyedropper"></span><span>Change Theme Color</span>
						</div>
						
                <input type="button" id="headerbackgroundsave" class="btn btn-default  primarythemebackgound primarythemecolor"  data-dismiss="modal" value="Save" />
	            <button id="reset" class="btn btn-default" data-dismiss="modal">No</button>
           
        </div>
      </div>
    </div>
  </div>
</div>
  
  <div class="modal" id="selectBranch" tabindex="-1" role="dialog" aria-hidden="false">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4>Change Branch </h4>
      </div>
      <div class="modal-body">
    	
    	
        <div class="actionsBtns">
                <select id="branch">
    			</select>
                <input type="button" class="btn btn-default  primarythemebackgound primarythemecolor" onclick="changeBranch()" data-dismiss="modal" value="Yes" />
	            <button class="btn btn-default" data-dismiss="modal">No</button>
           
        </div>
      </div>
    </div>
  </div>
</div>
  
  <div class="modal" id="logoutModal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4>Log Out <i class="fa fa-lock"></i></h4>
      </div>
      <div class="modal-body">
        <p><i class="fa fa-question-circle"></i> Are you sure you want to log-out? <br /></p>
        <div class="actionsBtns">
                
                <input type="button" class="btn btn-default  primarythemebackgound primarythemecolor" onclick="gotoLogout()" data-dismiss="modal" value="Yes" />
	            <button class="btn btn-default" data-dismiss="modal">No</button>
           
        </div>
      </div>
    </div>
  </div>
</div>
        
        <input type="hidden" id="LocalIp"
                value="<logic:present name="IP" scope="session"><bean:write name="IP"  scope="session"/></logic:present>" />
        
        <!-- <div id="hbox" class="col-md-2" style="" >
                
                <div class="gb_Wa"></div>
                <div class="gb_Va"></div>
                <p>&nbsp;</p>
                    <img src="img/11.jpg" style="width:100%"/> 
                   <p style="margin:0;">Name:</p>
                   <p style="margin:0;">Log In time:</p>
                   <p style="margin:0;">Date;</p><br/>
                   
        </div> -->

        <!-- Page Content -->


        <div class="pull-right" id="back-top ">
                <a href="#firstrow" class="scrollToTop"><img src="images/top.png"></a>
        </div>


</body>
</html>