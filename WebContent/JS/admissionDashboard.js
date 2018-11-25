$(document).ready(function(){
	
	var barColor = $(".primarythemebackgound").css("background-color");
	
	var studentData = getStudentDetails();
	
	
	
	var ctx= document.getElementById("student");
	
	var myChart =new Chart(ctx, {
	    type: 'pie',
	    legend: {
			fontSize: 30,
		},
	    data: {
	      labels: ["Applied","Approved","Cancelled","Pending","Processed","Rejected"],
	      datasets: [{
	        label: "Student",
	        backgroundColor: ["#3366cc","#109618","#ff9900","#990066","#1EABB4","#dc3912"],
	        data:studentData
	      }]
	    },
	    options: {
	      title: {
	        display:false,
	        text: 'Academic Admission Details',
	        	fontColor: 'black',
		        fontFamily: 'Roboto sans-sarif',
		        fontSize: 28
	      }
	    },
	    options: {
	    	  tooltips: {
	    	    titleFontSize: 25,
	    	    bodyFontSize: 25
	    	  }
	    	},
	});
	
});




function getStudentDetails(){
	var result ;
	
	
	$.ajax({
			type : "POST",
			url :"dashboardData.html?method=admissionDetails",
			async:false,
			success : function(data)
			{
			  var datas = $.parseJSON(data);
			  result= datas.data;
			}    
	  });
     
	return result;
	
}

