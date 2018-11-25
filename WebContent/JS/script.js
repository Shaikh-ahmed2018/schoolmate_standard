$(document).ready(function(){
	
	var barColor = $(".primarythemebackgound").css("background-color");
	
	var studentData = getStudentDataYearWise();
	
	var year=studentData[0];
	var school=studentData[1];
	var student=studentData[2];
	
	
	var attendanceDetails = getStudentAttendance();
	
	var classWiseDetails = getClassWiseStudent();
	
	
	

	
	
	
	var ctx3 = document.getElementById("myChart3");
	var myChart = new Chart(ctx3, {
		  
		  
		  
		  type: 'bar',
		    data: {
			      labels: year,
			      datasets: [
			        {
			          label: school[0],
			          backgroundColor: [
			        	                barColor,barColor,barColor,
			        	                barColor,barColor,barColor,
			        	                barColor,barColor,barColor,
						        	    barColor,barColor,barColor,
						        	    barColor,barColor,barColor,
						        	    barColor
						        	    ],
			          data: student
			        }
			      ]
			    },
			    options: {
			      legend: { display: false },
			      title: {
			        display: false,
			        text: 'Student Strength Progress (Academic Year wise)',
			        fontColor: 'black',
			        fontFamily: 'Roboto Medium',
			        fontSize: 14
			        	
			      },
			      scales: {
			    	  xAxes: [{
			    	      barPercentage: 0.3,
			    	      
			    	    }]
			      }
			    }
		  
		  
		  
		});




	var ctx = document.getElementById("myChart");
	
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	      labels: classWiseDetails[1],
	      datasets: [
	        {
	          label: "Student",
	          data: classWiseDetails[0],
	          backgroundColor: [barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor],
	          

	        }
	      ]
	    },
	    options: {
	      legend: { display: false },
	      title: {
	        display: false,
	        text: 'Class Strength',
	        fontColor: 'black',
	        fontFamily: 'Roboto Medium',
	        	 fontSize: 14
	      },
	      scales: {
	    	  xAxes: [{
	    	      barPercentage: 0.5,
	    	      
	    	    }]
	      }
	    	  
	     
	    }
	});


	var ctx2 = document.getElementById("myChart2");
	var myChart = new Chart(ctx2, {
	    type: 'bar',
	    data: {
	      labels: ["Total Student", "Present", "Absent", "Leave"],
	      datasets: [
	        {
	          label: "",
	          backgroundColor: [barColor,barColor,barColor,barColor],
	          data: attendanceDetails
	        }
	      ]
	    },
	    options: {
	      legend: { display: false },
	      title: {
	        display: false,
	        text: 'Today\'s Attendance (Student)',
	        fontColor: 'black',
	        fontFamily: 'Roboto Medium',
	        fontSize: 14
	      },
	      scales: {
	    	  xAxes: [{
	    	      barPercentage: 0.5,
	    	      
	    	    }]
	      }
	    }
	});



	
	
	
});


function getStudentDataYearWise(){
	
	var dataBulk = [];
	
	var year=[];
	var school=[];
	var student=[];
	
	$.ajax({
			type : "POST",
			url :"dashboardData.html?method=StudentDetailsYearWise",
			async:false,
			success : function(data)
			{
			  var result = $.parseJSON(data);
				
				for ( var j = 0; j < result.data.length; j++) {
					year.push(result.data[j].accYear);
					school.push(result.data[j].location);
					student.push(result.data[j].tot_count);
				}
				dataBulk.push(year);
				dataBulk.push(school);
				dataBulk.push(student);
			}    
	  });
	
	return dataBulk;
	
}

function getStudentAttendance(){
	
	var dataBulk = [];
	
	var total;
	var absent;
	var present;
	var leave;
	
	$.ajax({
			type : "POST",
			url :"dashboardData.html?method=todayStudentAttendance",
			async:false,
			success : function(data)
			{
			  var result = $.parseJSON(data);
				
				for ( var j = 0; j < result.data.length; j++) {
					total=(parseInt(result.data[j].tot_count));
					absent=(parseInt(result.data[j].absent_count));
					present=(parseInt(result.data[j].present_count));
					leave=(parseInt(result.data[j].leave_count));
				}
				dataBulk.push(total);
				dataBulk.push(present);
				dataBulk.push(absent);
				
				dataBulk.push(leave);
				
			}    
	  });
	
	return dataBulk;
	
}



function getClassWiseStudent(){
	
	var dataBulk = [];
	
	var total=[];
	var name=[];
	
	
	$.ajax({
			type : "POST",
			url :"dashboardData.html?method=classWiseStudent",
			async:false,
			success : function(data)
			{
			  var result = $.parseJSON(data);
				
				for ( var j = 0; j < result.data.length; j++) {
					total.push((parseInt(result.data[j].tot_count)));
					name.push((result.data[j].classname));
					
				}
				dataBulk.push(total);
				dataBulk.push(name);
			
				
			}    
	  });
	
	return dataBulk;
	
}





















