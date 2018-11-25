$(document).ready(function(){
	
	var barColor = $(".primarythemebackgound").css("background-color");
	var sms = getsmsDetails();
	
	
	
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
		   ['Element', '', { role: 'style' } ],
		     ['Total SMS',parseInt(sms.data[0]), barColor], 
		      ['Sent SMS',parseInt(sms.data[1]),barColor],
		    ['Remaining SMS',parseInt(sms.data[2]), barColor], ]);
		 var view = new google.visualization.DataView(data);
	     view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);
		var options = {title: '  ', bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};

		
		var chart = new google.visualization.ColumnChart(document.getElementById('SMSdetails'));
		chart.draw(view, options);}  
	
});



function getsmsDetails(){
	var result ;
	$.ajax({
			type : "POST",
			url :"dashboardData.html?method=smsStatus",
			async:false,
			success : function(data)
			{
			  result = $.parseJSON(data);
			}    
	  });
	
	return result;
	
}
