$(document).ready(function(){

	var size=parseInt($("#hidensize").val());
	if(size==0){
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan=4>No Records Found</td></tr>");
	}
	

	var size1=parseInt($("#hidensize1").val());
	if(size1==0){
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan=5>No Records Found</td></tr>");
	}
	
	$("#back1").click(function(){
		   window.location.href="LibraryMenu.html?method=GetBookSearch&requestby="+$("#requestby").val()+
		   "&orderby="+$("#orderby").val()+"&startedby="+$("#startedby").val()+"&searchValue="+$("#searchValue").val()
		   +"&historygoto="+$("#historygoto").val();
		});
	
});

