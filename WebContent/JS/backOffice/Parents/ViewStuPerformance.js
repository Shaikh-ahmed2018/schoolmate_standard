$(document).ready(function() {
	
	getPerformanceDetails();
	
	var barColor = $(".primarythemebackgound").css("background-color");
	
});

function getPerformanceDetails(){
	
	data = {
			"stuId" : $('#stuId').val(),
			"examid" : $('#examid').val(),
			"examprefx" : $('#exampfx').val(),
			"accyear" : $('#accyear').val()
	}
	
	$.ajax({
		type:'POST',
		url:'parentMenu.html?method=getPerformanceDetails',
		data:data,
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			
			displayBarChart(result.scoredmarks,result.subnames);
		}
	});
}

function displayBarChart(scoredmarks,subnames){
	var barColor = $(".primarythemebackgound").css("background-color");
	var subnames = subnames;
	var marks = scoredmarks;

	new Chart(document.getElementById("bar-chart"), {
	    type: 'bar',
	    data: {
	      labels: subnames,
	      datasets: [
	        {
	          label: "Marks",
	          backgroundColor: [barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor,barColor],
	          data: marks
	        }
	      ]
	    },
	    options: {
	      legend: { display: false },
	      title: {
	        display: true,
	        text: 'Student Rank'
	      },
	      scales: {
	    	  xAxes: [{
	    	      barPercentage: 0.3,
	    	      
	    	    }]
	      }
	    }
	});
}