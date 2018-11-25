$(document).ready(function(){	 
	
	
$("#excelDownload").click(function(){
		
		
		window.location.href = 'menuslist.html?method=UsageInventoryXLS';
			
	});

$("#pdfDownload").click(function(){
	
	
	window.location.href = 'menuslist.html?method=UsageInventoryPDF';
		
});

});
