<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>
<script src="js/jquery.js"/>
<script src="js/bootstrap.min.js"/>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />


<link href="CSS/IdCard/Event IdCard.css" rel="stylesheet" type="text/css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/spectrum.js"></script>
<script type="text/javascript" src="JS/backspectrum.js"></script>
<script type="text/javascript" src="JS/qrcode.min.js"></script>
<link href='CSS/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.css' rel="stylesheet" type="text/css" />

<link href="CSS/spectrum.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Events/EventIdCreation.js"></script>

<style>
.modal-body {
	text-align: center;
}
span.idCardDesignListDropDownSpan{
position: relative;
top:4px;
color:#000000;
font-family:inherit;
font-size: 14px;
padding: 2px
}
label.idCardDesignListDropDownlabel{
position: relative;
top:-10px;;
color:#000000;
font-family:inherit;
font-size: 14px;
padding: 2px
}
</style>

</head>

<body>

<input type="hidden" id="layoutDetails" value='<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>' />
	<div class="col-md-10" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
	<p>
		<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Design Event ID Card</span>
	</p>

			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"><h3 class="panel-title" id="beforeparent" style="color: #000000;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Event Details
							</h3></a>
						
				<div class="navbar-right">
					
					<span style="margin-left:5px" id="back" class="buttons">Back</span>
					</div>
						
					</div>
					<!-- pop up -->

					<!-- Filters -->
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Event Name</label>
									<div class="col-xs-7">
									<input  type="text" id="eventNameTemplate" name="tempale"  class="form-control" readonly="readonly" 
									value='<logic:present name="eventname"  scope="request" ><bean:write name="eventname" /></logic:present>' />
									</div>
								</div>
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Program Name</label>
									<div class="col-xs-7">
								<input  type="text" id="programName" name="tempale"  class="form-control" readonly="readonly" 
								value='<logic:present name="programname"  scope="request" ><bean:write name="programname" /></logic:present>' />
										</div>
								</div>
								
							</div>
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Event Category</label>
									<div class="col-xs-7">
								<input  type="text" id="eventCategory" name="tempale" class="form-control" readonly="readonly"
								 value='<logic:present name="categoryname"  scope="request" ><bean:write name="categoryname" /></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Template Name</label>
									<div class="col-xs-7">
								<input  type="text" id="templateid" name="tempale" class="form-control" readonly="readonly" value="Event ID Card">
									</div>
								</div>
								
							</div>
						
 </div>
 </div>
 </div>
 </div>

		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<h3 class="panel-title id-head" style="color: #000000;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Design Layout
					</h3>
			<div class="navbar-right">
			<label class="idCardDesignListDropDownlabel" >Select Previous Design</label><span class="idCardDesignListDropDownSpan"><select id="idCardDesignListDropDown"><option value="">-------Select-----</option></select></span> <span class="buttons" id="saveChanges" style="top:4px;">Save</span>
				</div>
				
			</div>	
		<section class="col-md-4 section">
			<div class="main-div" id="main-div" >
			 <img src='images/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><logic:present name="locationNmae"  scope="request" ><bean:write name="locationNmae" /></logic:present></span></div>
				<div class="branch" id="branch"><span id="branchName">PATTOM,THIRUVANANTHAPURAM</span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.1234567</span></div>
			</header>
				
				<div class="namediv" id="namediv">
					<span class="name label" id="name">Name : </span><span id="nametext">Kamran Ansari</span>
				</div>

				<div class="photo" id="photo">
					<img src="./images/profile_photo.png" alt="image" width="100%" height="100%" />
				</div>

				<div class="studentdetails" id="studentdetails">
				<div class="admission" id="admission">
						<span class="label" id="admissionlebel">AdmNo<span class="column">:</span></span><span id="admissiontext">Pre-KG/A</span>
				</div>
				<div class="classDivision" id="classDivision">
						<span class="label" id="classlebel">Class<span class="column">:</span></span><span id="classtext">Pre-KG/A</span>
				</div>
				<div class="rollno" id="rollno">
					<span class="label" id="rolNo">Roll No<span
						class="column">:</span></span><span id="rolNotext">25</span>
				</div>
				
				<div class="eventdetails" id="eventdetails">
						<span class="label" id="eventlabel">Event<span class="column">:</span></span><span id="phonetext">hockey</span>
				</div>
				 <!--<div class="enamediv" id="enamediv">
					<span class="label" id="ename">Event</span><span
						id="enametext">hockey</span>
				</div>
				<div class="cnamediv" id="cnamediv">
					<span class="label" id="cname"></span><span
						id="cnametext">hockey</span>
				</div>
				<div class="pnamediv" id="pnamediv">
					<span class="label" id="pname">Program</span><span
						id="pnametext">hockey</span>
				</div> -->
				
			
				
				<div class="valid" id="valid">
						<span class="label" id="validUpto">Category<span class="column">:</span></span><span id="validUptotext">2017</span>
				</div>
						
				<div class="house" id="house">
						<span class="label" id="houNo">Program<span class="column">:</span></span><span id="houNotext">Red</span>
				</div>
			</div>

				<!-- <div class="phonedetails" id="phonedetails">
					<span class="phones" id="phonelebel">Ph: </span><span id="phonetext">9999999999</span>
				</div>
 -->				<div class="qrdetails" id="qrdetails" ></div>
				<!-- <div class="parents" id="parents">
					<span class="label" id="patentlabel">Parents:</span><div class="parentDetail" id="parentDetail">
					<span class="label" id="emergency">Emergency No:</span><span id="emergencyNo">9999999999</span>
				</div>
				
			</div> -->
	<div  class="cntr">
    <ul id='items'>
	  <li class="headerfontSize">Header Font Size</li>
	  <li class="labelfontSize">Label Font Size</li>
      <li class="changeColor">Font Color</li>
      <li class="labelBackgroundColor">Label Background Color</li>
      <li class="changeBackgroundColor">Box Background Color</li>
      <li class="changeBorderColor">Box Border Color</li>
      <li id="boxCorner0" >Box Corner Curve-0</li>
      <li id="boxCorner2" >Box Corner Curve-2</li>
      <li id="boxCorner4" >Box Corner Curve-4</li>
      <li id="boxCorner6" >Box Corner Curve-6</li>
      <li id="boxCorner8" >Box Corner Curve-8</li>
      <li id="changeBackgroundImage" >Layout Background Image</li>
      <li id="restoreSize" >Restore Id Card Size</li>
    </ul>
  </div>
  <input id="backgroundfile" type="file" style="display: none;" />
  <select id="hfontval" style="display:none;"></select>
  <select id="lfontval" style="display:none;"></select>
		</section>

		</div>
 </div>
 
	<span>&nbsp;</span>

</body>

</html>
