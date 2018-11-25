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


<link rel="apple-touch-icon" sizes="57x57"
	href="images/fevicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="images/fevicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="images/fevicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="images/fevicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="images/fevicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="images/fevicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="images/fevicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="images/fevicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="images/fevicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="images/fevicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/fevicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="images/fevicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/fevicon/favicon-16x16.png">
<link rel="manifest" href="images/fevicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<title>Campus Pro</title>





<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script src="JQUERY/js/jquery-1.8.3.js"></script>
<script src="JS/backOffice/menuHighlight.js"></script>

<script src="JS/login/Admin.js"></script>



<script src="JS/login/bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/newUI/jquery-1.9.1.min.js"></script>

<script type="text/javascript"
	src="JS/frontOffice/PrintApplicationForm.js"></script>


<style>
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
	/* 	background: url('arrow_up.png') no-repeat 0px 20px; */
}

.scrollToTop:hover {
	text-decoration: none;
}
</style>



<style type="text/css">
.hoverColor:HOVER {
	text-decoration: underline;
	color: blue;
	cursor: pointer;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span id='label'
				style="text-align: center; color: white; font-size: 26px"></span> </a>


		</div>

		<div class="container col-md-8">

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar">

				</ul>
			</div>

		</div>

	</nav>

	<br>
	<br>
	<br>
	<br>
	<p style="font-size: 22px; text-align: center; color: green;">Thank
		you for Submitting Your Application</p>
	<p style="font-size: 22px; text-align: center; color: green;">
		<span class="buttons" id='print'>Print Application</span>
	</p>
	<div class="container" id='container' style="display: none;">
		<div class='row'>
			<div class='col-md-12'>
				<div class='tabledetails'>
					<div class='row'>
						<div class='col-md-12'>
							<div class='firstpage'>
								<div class="row">
									<div class='col-md-12'>
										<div class='headings'>
										<h2 style="text-align: center; margin: 0; margin-top: 5px; font-family: Algerian;"><logic:present name='result'><bean:write name='result' property="school_name"/></logic:present></h2>
											<!-- <p class='schoolheading'>KINDERGARTEN</p> -->
											<!-- <p class='schoolheading'>ARYA CENTRAL SCHOOL</p> -->
											<p style="text-align: center; font-weight: 600"><logic:present name='result'><bean:write name='result' property="address"/></logic:present></p>
										</div>
										<div class='subheadings'>
											<p>APPLICATION FOR ADMISSION</p>
										</div>
									</div>
								</div>
								<div class='row'>
									<div class='col-md-12'>
										<div class='tablecontent'>
											<div class='row'>
												<div class="col-md-12">
													<div style="margin-top: 5px;">
														<div class='col-md-3'>
															<table class='toptables'>
																<tr>
																	<th style="width: 150px; text-align: center;">Admission
																		No</th>
																</tr>
																<tr>
																	<td style="text-align: center;width:150px;height: 25px;"></td>
																</tr>
															</table>
														</div>
														<div class='col-md-6'>
															<div>
																<table class='toptables'>
																	<tr style="height: 45px;">
																		<th style="width: 165px; text-align: center;">Class
																			to which Admission is sought</th>
																		<td style="width: 75px;left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="className"/></logic:present></td>
																	</tr>
																</table>
															</div>
														</div>
														<div class='col-md-3'>
															<div class='photostyle'>
															<img height="90px" width="90px" src='<logic:present name='studentinformation'><bean:write name='studentinformation' property="imageString"/></logic:present>'>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class='row'>
												<div class='col-md-12'>
													<div class='studetails'>
														<div class='col-md-12'>
															<table class="toptables" width="100%">
																<tr style="height: 25px">
																	<th style="text-align: center; width: 30px;font-size:13px;">1.</th>
																	<th style="text-align: center; width: 170px;font-size:13px;">Name
																		of Pupil</th>
																	<td style="width: 375px; left;padding:5px;font-size:13px;"><span class='stuName'><logic:present name='studentinformation' scope='request'><bean:write name='studentinformation' property="studentfirstName"/></logic:present></span>&nbsp;&nbsp;<span class='stuName'><logic:present name='studentinformation'><bean:write name='studentinformation' property="studentLastName"/></logic:present></span></td>
																</tr>
															</table>
														</div>
														<div class='col-md-12' style="margin-top: 5px;">
															<table class="toptables" width="100%">
																<tr style="height: 25px">
																	<th rowspan="2"
																		style="text-align: center; width: 34px;font-size:13px;">2.</th>
																	<th rowspan="2"
																		style="text-align: center; width: 120px;font-size:13px;">Date of
																		Birth</th>
																	<th style="width: 100px; text-align: center;font-size:13px;">In
																		figure</th>
																	<th style="width: 280px; text-align: center;font-size:13px;">In
																		Words</th>
																</tr>
																<tr style="height: 25px">
																	<td style="width: 120px; left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="dateofBirth"/></logic:present></td>
																	<td style="width: 280px; left;padding:5px;font-size:13px;"><logic:present name='dateofbirthinwords'><bean:write name='dateofbirthinwords'/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class='col-md-12' style="margin-top: 5px;">
															<table width="100%">
																<tr>
																	<th rowspan="2"
																		style="width: 39px; text-align: center;font-size:13px;">3.</th>
																	<th rowspan="2" style="width: 70px;text-align: center;font-size:13px;">Sex</th>
																	<td rowspan="2"
																		style="width: 126px; left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="gender"/></logic:present></td>
																	<th rowspan="2"
																		style="width: 40px;font-size:13px; text-align: center;font-size:13px;">4.</th>
																	<th rowspan="2"
																		style="width: 315px; text-align: center;font-size:13px;">Age as
																		on 1<sup>st</sup> June of Academic Session
																	</th>
																
																</tr>
																<tr>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="stuage"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class='col-md-12' style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 39px;font-size:13px;">5.</th>
																	<th style="text-align: center;font-size:13px;">Religion</th>
																	<td style="width: 158px; left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="relname"/></logic:present></td>
																	<th style="text-align: center; width: 40px;font-size:13px;">6.</th>
																	<th style="text-align: center;font-size:13px;">Caste</th>
																	<td style="width: 75px; left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="castename"/></logic:present></td>
																	<th style="text-align: center; width: 40px;font-size:13px;">7.</th>
																	<th style="text-align: center;font-size:13px;">Nationality</th>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="nationality"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 39px;font-size:13px;">8.</th>
																	<th style="text-align: center; width: 250px;font-size:13px;">Whether
																		Belonging to SC/ST/OBC</th>
																	<td style="left;padding:5px;font-size:13px;width: 75px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="castecatname"/></logic:present></td>
																	<th style="text-align: center; width: 40px;font-size:13px;">9.</th>
																	<th style="text-align: center; width: 110px;font-size:13px;">Mother
																		Tongue</th>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothertongue"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class='col-md-12'>
															<div class="parentsdetails">
																<p>DETAILS OF PARENTS</p>
															</div>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="width: 250px;"></th>
																	<th style="text-align: center; width: 242px;font-size:13px;">Father</th>
																	<th style="text-align: center;font-size:13px;">Mother</th>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">10.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Name</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatherName"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothername"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 40px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">11.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Occupation
																		& Designation</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><span><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatherOccupation"/></logic:present></span><br /><span><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatherDesignation"/></logic:present></span></td>
																	<td style="left;padding:5px;font-size:13px;"><span><logic:present name='studentinformation'><bean:write name='studentinformation' property="motherOccupation"/></logic:present></span><br /><span><logic:present name='studentinformation'><bean:write name='studentinformation' property="motherDesignation"/></logic:present></span></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 80px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">12.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Official
																		Address</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fathersOfficialAddress"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothersOfficialAddress"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">13.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatOffiAddLandLiNo"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothOffiAddLandLiNo"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">14.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Monthly
																		Income</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatherMonthlyIncome"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="motherMonthlyIncome"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 100px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">15.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;"><span>Permanant
																			Residental</span><br />Address</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="address"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="address"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">16.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatPerAddLandLiNo"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothPerAddLandLiNo"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 40px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">17.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Mobile
																		No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatherMobileNo"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="motherMobileNo"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">18.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">SMS
																		No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="preferedphno"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="preferedphno"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">19.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Emergency
																		Mobile No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="emergencyNo"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="emergencyNo"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12" style="margin-top: 5px;">
															<table width="100%">
																<tr style="height: 100px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">20.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Present
																		Address<br /> [At the time of Admission]
																	</th>
																	<td style="padding-left:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="addressofcommunication"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="addressofcommunication"/></logic:present></td>
																</tr>
															</table>
														</div>
														<div class="col-md-12"
															style="margin-top: 5px; margin-bottom: 10px;">
															<table width="100%">
																<tr style="height: 25px;">
																	<th style="text-align: center; width: 40px;font-size:13px;">21.</th>
																	<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																	<td style="left;padding:5px;font-size:13px; width: 242px;margin-bottom:20px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="fatPreAddLandLiNo"/></logic:present></td>
																	<td style="left;padding:5px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="mothPreAddLandLiNo"/></logic:present></td>
																</tr>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class='row'>
						<div class='col-md-12'>
							<div class="declaration">
								<p>I have read the entire date typed above(typed by me) and
									verified it.I here by declare that the details furnished
									above are true to the best of my knowledge.</p>
								<p style="text-align: right; padding-top: 20px;">Signature
									of Parent or Guardian</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
								<div class="col-md-12" style="margin-top: 8px;">
									<table width="100%">
										<tr style="height: 25px;">
											<th style="text-align: left;padding:5px;font-size:13px; width: 40px;font-size:13px;">22.</th>
											<th style="text-align: center; width: 210px;">Name of
												the Play School/<br />Kindergarten last studied
											</th>
											<td style="left;padding:5px;font-size:13px; width: 242px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="lastKindergartenName"/></logic:present></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
<div class="row">
						<div class="col-md-12">
							<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
								<div class="col-md-12" style="margin-top: 8px;">
									<table width="100%">
										<tr style="height: 25px;">
											<th style="text-align: center; width: 40px;font-size:13px;">23.</th>
											<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Adhar No.
											</th>
											<td class="aadharNo" style="padding-left: 5px; width: 242px;font-size:13px;"><logic:present name='studentinformation'><bean:write name='studentinformation' property="aadharno"/></logic:present></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class='row'>
						<div class='col-md-12'>
							<div class="parentdeclaration"
								style="width: 760px; margin: auto; margin-top: 5px;">
								<div class='declarationheading'>
									<p
										style="text-align: center; font-weight: 600; font-size: 16px; margin: 3px;">Declaration
										of the parent/guardian</p>
								</div>
								<div>
									<p
										style="left;padding:5px;font-size:13px; margin-top: 5px; padding-right: 2px;">1)&nbsp;I
										hereby declare that the 'Date of Birth' in respect of my
										Son/Daughter : <span style="width: 375px; left;padding:5px;font-size:13px;text-decoration:underline;"><logic:present name='studentinformation' scope='request'><bean:write name='studentinformation' property="studentfirstName"/></logic:present></span>&nbsp;&nbsp;<span><logic:present name='studentinformation'><bean:write name='studentinformation' property="studentLastName"/></logic:present></span>furnished by me in
										item No. 2 is correct and that I would not demand any change
										in it at any date. I accept that any statement made in the
										application, if found incorrect on scrutiny, will render the
										application of my son/daughter/ward liable for rejection and
										the admission, if granted on the basis of such incorrect
										information will stand cancelled.</p>
									<p style="left;padding:5px;font-size:13px;">2)&nbsp;I shall be
										responsible for his/her conduct,good behaviour and compliance
										with the rules in force from time to time during the entire
										period of his/her study.</p>
									<p style="left;padding:5px;font-size:13px; padding-right: 2px;">3)&nbspI
										promise to abide by any decision taken by the Principal for
										any misconduct or misbehaviour or breach of rules by my
										son/daughter/ward during the entire period of his/her course.
										I shall also hold myself responsible and compensate for any
										damages caused by my son/daughter/ward in the school.</p>
									<p style="left;padding:5px;font-size:13px; padding-right: 2px;">PS: The
										school provides transport facilities but offers no guarantee
										that a seat in the school bus will be available when the buses
										are full to capacity/do not ply in the area of your residence.
										It will be the responsibility of the parents/guardian to
										drop/collect the child from the school or from the specified bus stop as applicable.</p>
									<p
										style="left;padding:5px;font-size:13px; padding-right: 2px; font-family: Times New Roman; font-size: 15px; font-weight: 600;">I
										have read the entire data typed and verified
										it. I hereby declare that the details furnished above are
										true.</p>
									<p
										style="left;padding:5px;font-size:13px; font-weight: 600; font-size: 15px;">
										Date : ...................</p>
									<p
										style="left;padding:5px;font-size:13px; font-weight: 600; font-size: 15px;">
										<span>Place :</span> <span
											style="float: right; padding-right: 5px;">Signature of
											Parent/Guardian</span>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class='col-md-12'>
							<div class="office"
								style="width: 760px; margin: auto; margin-top: 5px;">
								<div class='officeuse'>
									<p
										style="text-align: center; font-weight: 600; font-size: 16px; margin: 3px;">FOR
										OFFICE USE</p>
								</div>
								<div class='officewraP' style="width: 746px; margin-left: 5px;">
									<div
										style="border: 1px solid black; height: 75px; margin-top: 10px; border-radius: 5px;">
										<div class="col-md-6" style="margin-top: 15px;">
											<p>Verified the data furnished</p>
										</div>
										<div class="col-md-6"
											style="margin-top: 10px; text-align: left;">
											<p style="margin-left: 15px;">Name :</p>
											<p style="margin-left: 15px;">Signature :</p>
										</div>
									</div>
									<div
										style="border: 1px solid black; height: 100px; margin-top: 10px; border-radius: 5px;">
										<div class="col-md-6" style="margin-top: 10px;">
											<p>Admit to class</p>
										</div>
										<div class="col-md-6" style="margin-top: 25px;">
											<p style="text-align: right;">Principal</p>
											<p style="text-align: right; padding-top: 10px;">Date :
												........................</p>
										</div>
									</div>
									<div
										style="border: 1px solid black; height: 125px; margin-top: 10px; border-radius: 5px;">
										<p style="margin-top: 20px; padding-left: 15px;">Admitted
											to class : ...................... Sec ....................
											Fee Receipt No .................... Date ...................
											issued.</p>
										<p style="margin-top: 10px; padding-left: 15px;">Name has
											been entered in the Admission Register.</p>
										<p style="margin-top: 20px; padding-left: 15px;">
											<span>Date : ...................</span> <span
												style="float: right; padding-right: 10px;">Office
												Clerk.</span>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<textarea id="printing-css" style="display: none;">
	.col-md-6{
	width:49%;
	float:left;
	}
	.col-md-3{
	width:24%;
	float:left;
	}
	.row{margin-left:-15px;margin-right:-15px}
	.row::after{
	content:"";
	clear:both;
	display:block;
	overflow:hidden;
	}
	body{-webkit-print-color-adjust: exact;margin:0;padding:0;}
	</textarea>
</body>
</html>