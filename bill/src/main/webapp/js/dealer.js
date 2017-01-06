$(document).ready(function(){
	$(document).on('click','#image',function(){
		var dropdown="";
		dropdown += "<ul>";
		dropdown += "<li>Product<\/li>";
		dropdown += "<li id=\"dealerli\">Dealer<\/li>";
		dropdown += "<li>Customer<\/li>";
		dropdown += "<li>Purchase<\/li>";
		dropdown += "<li>Sales<\/li>";
		dropdown += "<li>Expenses<\/li>";
		dropdown += "<\/ul>";
		$('.menu')[0].innerHTML=dropdown;
		 $('.menu').toggle(); 
		 $('.menutwo').css("display","none");
});
	
	$('#dealerli').on('load',function(){
		var list="";
		list += "<ul>";
		list += "<li>dealerDetails<\/li>";
		list += "<li>dealerBillDetails<\/li>";
		list += "<\/ul>";
		$(".menutwo")[0].innerHTML=list;
		 $(".menutwo").toggle();

		 
	});		 
		
		
	$(document).on('click','#adddealer',function(){
		var add="";
		add += "<input type=\"text\" id=\"dealerid\" placeholder=\"DealerId\"\/><\/br><\/br>";
		add += "<input type=\"text\" id=\"name\" placeholder=\"Name\"\/><\/br><\/br>";
		add += "<input type=\"text\" id=\"address\" placeholder=\"Address\"\/><\/br><\/br>";
		add += "<input type=\"text\" id=\"phone\" placeholder=\"PhoneNumber\"\/><\/br><\/br>";
		add += "<input type=\"text\" id=\"tin\" placeholder=\"TINNumber\"\/><\/br><\/br>";
		add += "<button id=\"submit\">ADDNOW!<\/button>";
		add += "<button id=\"update\">UPDATE<\/button>";
	
      $('.adddiv')[0].innerHTML=add;
	});
	
	$(document).on('click','#dealerbill',function(){
		var billadd = "";
		billadd += "<input type=\"text\" id=\"did\" placeholder=\"dealerId\"\/><\/br>";
		billadd += "<input type=\"text\" id=\"pid\" placeholder=\"purchaseId\"\/><br>";
		billadd += "<button id=\"billadd\">billadd<\/button>";
		
		$('.adddiv')[0].innerHTML=billadd;
	});
	
	
	
	});
	
	