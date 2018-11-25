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
		    	 "values":getTransportTermName()  //start : lenght : increment
		      },
		      "noData":{
		     	  "text":"Empty Series",
		     	  "backgroundColor":"#20b2db"
		     	},
		    "plot":{
		        "exact":1,
		        "shadow":1,
		        "alpha":1,
		        "shadow-distance":1,
		        "bar-width":40,
		        "stacked":0,
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
		               // "text":"Termwise    Transport Fee:  "+academicYear+"",
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
			id : 'myChart', 
			data : myConfig, 
			height : "100%", 
			width: "100%" 
		});

		zingchart.shape_click = function(p) {
		  if (p.shapeid == 'animation1') {
		    zingchart.exec('myChart', 'reload');
		  }
		}
	
		
		 google.charts.setOnLoadCallback(drawChart); // for TransportCategory :definition	
	
		
});//JQUERY

function drawChart() {  // for TransportCategory : implementataion
	
    // Define the chart to be drawn.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Browser');
    data.addColumn('number', 'Percentage');
  
    for(var k=0;k<getTransportVechileName().length;k++){
    	 data.addRows([
    	    	[getTransportVechileName()[k],getTransportnoOfStudents()[k]]
    	    	
    	    	]);
    }
    // Set chart options
    var options = {
       'title':'Transport Category',
       'width':600,
       'height':280,
      // colors: ['#109896','#3366CC', '#DC3912', '#109618','#ff8c00',"#66ccff",'#66B366',],
       //is3D: true
    };

    // Instantiate and draw the chart.
    var chart = new google.visualization.PieChart(document.getElementById('TransportCateg'));
    chart.draw(data, options);
 }




function getTransportValues(){
	var transportVal=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		data : {"flag":"transport"},
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			for(var i=0;i<result.list[0].transportDashboard.length;i++){
				transportVal.push(result.list[0].transportDashboard[i].transportTermwiseFeeCollection);	
			}
			
		}
	});
	return transportVal;
}


function getTransportTermName(){
	var dashboardtransportTermName=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues", //  1) Transport dashboard  : transport termname
		data : {"flag":"transport"},
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			for(var i=0;i<result.list[0].transportDashboard.length;i++){
				dashboardtransportTermName.push(result.list[0].transportDashboard[i].transportTermname);	
			}
		}
	});
	
	return dashboardtransportTermName;
}












//----------- transport catedgory

function getTransportVechileName(){
	var vehicleName=[];
	$.ajax({
		type:'POST',
		url:"Menus.html?method=getTransportValues",
		data : {"flag":"transport"},
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
		data : {"flag":"transport"},
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

