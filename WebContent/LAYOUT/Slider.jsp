<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<title></title>

<style type="text/css">
.tp-caption{
position: absolute;
display: none;
}
.lft{
right: 50px;

}
.hand,.service{
right:0;

}
.lfb,.lfr{
left: 50px;
}
#mainImage{
width: 100%;
}
.service{
	top:9%;
	-webkit-animation: servicemove 8s 1; /* Safari 4.0 - 8.0 */
    animation: servicemove 8s 1;
}
.hand{
	bottom: 9%;
	-webkit-animation: mymove 8s 1; /* Safari 4.0 - 8.0 */
    animation: mymove 8s 1;
}
.lfr{
	top:34%;
	-webkit-animation: lfrmove 8s 1; /* Safari 4.0 - 8.0 */
    animation: lfrmove 8s 1;
    font-size: 32px;
}
.lfb{
	bottom:34%;
	-webkit-animation: lfbmove 8s 1; /* Safari 4.0 - 8.0 */
    animation: lfbmove 8s 1;
    font-size: 32px;
}
@-webkit-keyframes mymove {
    from {bottom: -30px;}
    to {bottom: 9%;}
}

@keyframes mymove {
    from {bottom: -30px;}
    to {bottom: 9%;}
}

@-webkit-keyframes servicemove {
    from {top: -30px;}
    to {top: 9%;}
}

@keyframes servicemove {
    from {top: -30px;}
    to {top: 9%;}
}

@-webkit-keyframes lfbmove {
    from {bottom: -30px;}
    to {bottom: 34%;}
}

@keyframes lfbmove {
    from {bottom: -30px;}
    to {bottom: 34%;}
}

@-webkit-keyframes lfrmove {
    from {top: -30px;}
    to {top: 34%;}
}

@keyframes lfrmove {
    from {top: -30px;}
    to {top: 34%;}
}

</style>
</head>

<body>
	<ul style="
    list-style:  none;
    padding: 0;
    margin: 0;
">
		

		<li data-transition="random" data-slotamount="7"
			data-masterspeed="300"><img id ="mainImage" src="HomeImages/slider/2-bg.jpg"
			alt="2-bg">
			<div class="tp-caption main_title lfr" data-x="0" data-y="60"
				data-speed="1000" data-start="300" data-easing="easeOutQuint">
			Services <span
					style="font-family: 'Dancing Script'; font-weight: 100;"></span> <br />
			At its best
			</div>

			<div class="tp-caption paragraph lfb" data-x="0" data-y="194"
				data-speed="1000" data-start="700" data-easing="easeOutQuint">
				An all round performance <br /> <b>solution for each and every
					sector</b>
			</div>

			<div class="tp-caption paragraph lf" data-x="0" data-y="300"
				data-speed="1200" data-start="3300" data-easing="easeOutQuint">

				<!-- <a href="http://www.themeforest.net/item/pink-rio-responsive-multipurpose-theme/3091259"  class="btn btn-xlarge  btn-mfast-3 ">Purchase the theme</a> -->

			</div>

			<div class="tp-caption lf" data-x="598" data-y="166"
				data-speed="900" data-start="2000" data-easing="easeOutQuint">
				<img src="HomeImages/logos/arrow.png" alt="Image 5">
			</div>
			<div class="tp-caption lft service" data-x="700" data-y="-3"
				data-speed="1000" data-start="1600" data-easing="easeOutQuint">
				<img src="HomeImages/logos/slide22.png" alt="Image 4">
			</div>

			<div class="tp-caption lfl hand" data-x="750" data-y="285"
				data-speed="900" data-start=0 "
                         data-easing="easeOutQuint">
				<img src="HomeImages/logos/s1.png" alt="Image 6">
			</div></li>

		

	</ul>
</body>
</html>