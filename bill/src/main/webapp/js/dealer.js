$(document).ready(function(){
	$('#image').on('click',function(){
		var dropdown="";
		dropdown += "<ul>";
		dropdown += "<li>Product<\/li>";
		dropdown += "<li>Dealer<\/li>";
		dropdown += "<li>Customer<\/li>";
		dropdown += "<li>Purchase<\/li>";
		dropdown += "<li>Sales<\/li>";
		dropdown += "<li>Expenses<\/li>";
		dropdown += "<\/ul>";
		$('.menu')[0].innerHTML=dropdown;
		
		$('.menu').css("display","block");
		
	},function(){
		$(".menu").css("display","none");
	});
	
});