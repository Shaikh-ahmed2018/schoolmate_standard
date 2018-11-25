$(document).ready(function() {
	
    var s1=$("#hiddenid").val();
   
	if($("#hiddenid").val()!="")
	{
		$("#parentchild option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
	}
 
	$("#allstudent tbody tr").find('.Declared').append("<span class='result' id='viewResult' title='click here' style='margin-right: 19px;'>Declared</span>");
	
	
	$("#parentchild").change(function(){
		var studentid = $('#parentchild').val();
		var hiddenid = $('#parenthidden').val();
		window.location.href = "parentMenu.html?method=getexamlist&hiddenid="+hiddenid+"&studentid="+studentid;
	});
	
	$("#viewResult").click(function(){
		examid = $(this).closest('tr').find('.Declared').attr('id');
		examprfx = $(this).closest('tr').find('.Declared').attr('class').split(' ')[1];
		window.location.href = "parentMenu.html?method=viewResultDetails&locid="+$("#hiddenloc").val()+"&examid="+examid+
		"&classid="+$("#hiddenclassid").val()+"&secid="+$("#hiddensectionid").val()+"&examprfx="+examprfx;
	});
	
});