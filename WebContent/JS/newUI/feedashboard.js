

$(document).ready(function(){
	//amountPaidByTerm
	
	
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			academicYear= result.accyearFace;	
		}
	});
	
	

	
	//TRANSPORT
	
	var myConfig = {
		    "type":"bar",
		    "scale-x":{
		    	 "values":feeTermNames()  //start : lenght : increment
		    	  
		      },
		    "plot":{
		        "exact":1,
		        "shadow":1,
		        "alpha":1,
		        "shadow-distance":1,
		        "stacked":0,
		        "bar-width":40,
		        "line-width":2,
		        "slice-start":15,
		        "background-color": $(".primarythemebackgound").css("background-color"),
		        "value-box":{
		            "visible":0
		        },
		        "animation":{
		          "effect":"7",
		          "speed":"500",
		          "method":"6",
		          "sequence":"3"
		        },
		    },
		    "shapes":[
		        {
		            "type":"rect",
		            "id":"animation1",
		            "width":"100",
		            "height":"30",
		            "background-color":"#fff #fff",
		            "border-radius":"10px",
		            "x":"50px",
		            "y":"15px",
		            "label":{
		                "text":"Termwise Fee Collection:  "+academicYear+"",
		                "font-color":"black",
		                "font-size":"13px",
		                "font-weight":"700",
		               /* "position":"relative",
		                "top":"20px",*/
		            }
		        }
		    ],
		    "series":[
		        {
		        	 "values":getTransportValues()
		        },
		        
		        
		    ],
		  
		};

		zingchart.render({ 
			id : 'FeeCollection', 
			data : myConfig, 
			height : "100%", 
			width: "100%" 
		});

		zingchart.shape_click = function(p) {
		  if (p.shapeid == 'animation1') {
		    zingchart.exec('FeeCollection', 'reload');
		  }
		}
	
		
});//JQUERY





function getTransportValues(){
	var feeCollectionAmt=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		data : {"flag":"fee"},
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].dashboardFeeCollection.length;i++){
				feeCollectionAmt.push(result.list[0].dashboardFeeCollection[i].feeTermwiseCollection);	
			}
			
		}
	});
	return feeCollectionAmt;
}


function feeTermNames(){
	var termName=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues", //  1) Transport dashboard  : transport termname
		data : {"flag":"fee"},
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].dashboardFeeCollection.length;i++){
				termName.push(result.list[0].dashboardFeeCollection[i].feeTermName);	
			}
			
		}
	});
	
	return termName;
}














//----------- transport catedgory

/*function getTransportVechileName(){
	var vehicleName=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		data : {"flag":"fee"},
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].vechileNameList.length;i++){
				 vehicleName.push(result.list[0].vechileNameList[i].trasVechicleName);
			}
			
			
		}
	});
	return vehicleName;
}

function getTransportnoOfStudents(){
	var noOfStudents=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].vechileNameList.length;i++){
				 noOfStudents.push(result.list[0].vechileNameList[i].noOfStudents);
			}
			
			
			
		}
	});
	return noOfStudents;
}

*/
















/*

	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			academicYear= result.accyearFace;	
		}
	});
	
	

	window.onload = function () {
		CanvasJS.addColorSet("greenShades",
	                [//colorSet Array
	                	$(".primarythemebackgound").css("background-color")
	                               
	                ]);
		
	var chart = new CanvasJS.Chart("chartContainer", {
		
		animationEnabled: true,
		colorSet: "greenShades",
		zoomEnabled: true,
		dataPointWidth: 90,  //width of bars
		//theme: "light2", // "light1", "light2", "dark1", "dark2"
		title:{
			text: "Termwise Fee Collection :"+academicYear+"",
			fontSize:25,
			padding:0,
			maxWidth:600,
			fontWeight:"bold",
			
			margin:20,
			fontFamily:"verdana",

		},
		axisY: {

			title: "Amount",
			labelFontSize: 25,

		},
		axisX:{
			   labelFontSize: 30,
			 },
		
			
		data: [{        
			type: "column",  
			showInLegend: true, 
			legendMarkerColor: "grey",
			legendText: "MMbbl = one million barrels",
			dataPoints: 
				getTransportValues()
				
				}]
	});
	chart.render();

	}

	
	
	
	
	
	
	
	


});	//jquery



function getTransportValues(){
	var transportVal=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].termNameList.length;i++){
				transportVal.push({y:result.list[0].termNameList[i].amountPaidByTerm,label:result.list[0].termNameList[i].termNames});
		//transportVal.push('{ y: '+result.list[0].termNameList[i].amountPaidByTerm+', label: "'+ result.list[0].termNameList[i].termNames+'"}');	
			//	transportVal.push(result.list[0].termNameList[i].amountPaidByTerm);
			}
			
		}
	

	});
	
	
	return transportVal;
}


function getTransportTermName(){
	var transportName=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			//transportVal = result.list;
			for(var i=0;i<result.list[0].termNameList.length;i++){
				transportName.push(result.list[0].termNameList[i].termNames);	
			}
			
		}
	});
	
	return transportName;
}
*/