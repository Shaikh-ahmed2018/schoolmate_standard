$(document).ready(function() {
	
	$("#print").click(function(){
	printApplication();
	//printPriview();
	});
	
	
});

function printPriview(){
	
	
	 var printContents = document.getElementById('container').innerHTML;
	 w=window.open();
	 w.document.write(printContents);
	 w.print();
	 w.close()
	 
	 var mywindow = window.open('', 'new div', 'height=400,width=600');
     mywindow.document.write('<html><head><title>my div</title>');
     /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
   
     mywindow.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
  
   
     mywindow.document.write('<link href="CSS/newUI/PrintAppForm.css" rel="stylesheet">');
    
     mywindow.document.write('</head><body >');
     mywindow.document.write(printContents);
     mywindow.document.write('</body></html>');
     mywindow.print();
     mywindow.close();
     return true;
	 
	 
	 
}


function printApplication(){

	
	
	 var a=$("#printing-css").val();
		var b = document.getElementById("container").innerHTML;

	  
	
	    var abd='<style>' + a +'</style>' + b;
	    
 var frame1 = $('<iframe />');
 frame1[0].name = "frame1";
 frame1.css({ "position": "absolute", "top": "-1000000px" });
 $("body").append(frame1);
 var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
 frameDoc.document.open();
 //Create a new HTML document.
 frameDoc.document.write('<html><head><title>DIV Contents</title>');
 //Append the external CSS file.
 
 frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
 frameDoc.document.write('<link href="CSS/Parent/PrintAppForm.css" rel="stylesheet">');
  frameDoc.document.write('</head><body>');

 
 //Append the DIV contents.
 frameDoc.document.write(abd);
 frameDoc.document.write('</body></html>');
 frameDoc.document.close();
 setTimeout(function () {
     window.frames["frame1"].focus();
     window.frames["frame1"].print();
     frame1.remove();
 }, 100);
	
}



