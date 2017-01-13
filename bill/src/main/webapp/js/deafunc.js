$(document).ready(function(){
$(document).on('click','#image',function(){
	$('#image').css("width","50px");
	$('#image').css("height","50px");
},function(){
	$('#image').css("width","100px");
	$('#image').css("height","100px");
});

$(document).on('click','#dealerli', function() {
    var list = "";
    list += "<ul class=\"list1\" >";
    list += "<li>dealerDetails<\/li>";
    list += "<li>dealerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#productli', function() {
    var list = "";
    list += "<ul class=\"list2\">";
    list += "<li>productDetails<\/li>";
    list += "<li>productBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#customerli', function() {
    var list = "";
    list += "<ul class=\"list3\">";
    list += "<li>customerDetails<\/li>";
    list += "<li>customerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#purchaseli', function() {
    var list = "";
    list += "<ul class=\"list3\">";
    list += "<li>purchasedeatails<\/li>";
    list += "<li>BillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});



});