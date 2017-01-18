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
    list += "<ul class=\"uldealer\" >";
    list += "<li><a href=\"#divdea\">dealerDetails<\/a><\/li>";
    list += "<li>dealerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#productli', function() {
    var list = "";
    list += "<ul class=\"ulproduct\">";
    list += "<li>productDetails<\/li>";
    list += "<li>productBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#customerli', function() {
    var list = "";
    list += "<ul class=\"ulcustomer\">";
    list += "<li><a href=\"customer.html\">customerDetails<\/a><\/li>";
    list += "<li>customerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#purchaseli', function() {
    var list = "";
    list += "<ul class=\"ulpurchase\">";
    list += "<li><a href=\"purchase.html\">purchasemetadata<\/a><\/li>";
    list += "<li>purchaselineitem<\/li>";
    list += "<li>purchasepayment<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#salesli', function() {
    var list = "";
    list += "<ul class=\"ulsales\">";
    list += "<li><a href=\"Sale.html\">salesmetadata<\/a><\/li>";
    list += "<li>saleslineitem<\/li>";
    list += "<li>salespayment<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#expenseli', function() {
    var list = "";
    list += "<ul class=\"ulexpense\">";
    list += "<li><a href=\"Expense.html\">Expensescategory<\/a><\/li>";
    list += "<li>Expenses<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

});