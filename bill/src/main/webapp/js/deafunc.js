$(document).ready(function(){
$(document).on('click','#image',function(){
	$('#image').css("width","50px");
	$('#image').css("height","50px");
},function(){
	$('#image').css("width","100px");
	$('#image').css("height","100px");
});


$(document).on('click','#productli', function() {
    var list = "";
    list += "<ul class=\"ulproduct\" id=\"listall\">";
    list += "<li >productDetails<\/li>";
    list += "<li>productBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#dealerli', function() {
    var list = "";
    list += "<ul class=\"uldealer\" id=\"listall\">";
    list += "<li id=\"adddealer\">dealerDetails<\/li>";
    list += "<li id=\"all\">dealerAll<\/li>";
    list += "<li id=\"dealerbill\">dealerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#customerli', function() {
    var list = "";
    list += "<ul class=\"ulcustomer\" id=\"listall\">";
    list += "<li onclick=\"customer()\">customerDetails<\/li>";
    list += "<li id=\"cusAll\">customerDetails<\/li>";
    list += "<li onclick=\"customerBill()\">customerBillDetails<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#purchaseli', function() {
    var list = "";
    list += "<ul class=\"ulpurchase\" id=\"listall\">";
    list += "<li onclick=\"purchaseproduct()\">purchasemetadata<\/li>";
    list += "<li onclick=\" purchaseLineItem()\">purchaselineitem<\/li>";
    list += "<li onclick=\"purchasePayment()\">purchasepayment<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#salesli', function() {
    var list = "";
    list += "<ul class=\"ulsales\" id=\"listall\">";
    list += "<li onclick=\"salemeta()\">salesmetadata<\/li>";
    list += "<li onclick=\"saleline()\">saleslineitem<\/li>";
    list += "<li onclick=\"salepay()\">salespayment<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});

$(document).on('click','#expenseli', function() {
    var list = "";
    list += "<ul class=\"ulexpense\" id=\"listall\">";   
    list += "<li onclick=\"expensecat()\">Expensescategory<\/li>";
    list += "<li onclick=\"expense()\">Expenses<\/li>";
    list += "<li id=\"expgetall\">Expensesall<\/a><\/li>";
    list += "<li id=\"getallcat\">Expensescategoryall<\/li>";
    list += "<\/ul>";
    $(".menutwo")[0].innerHTML = list;
    $(".menutwo").toggle();
});
 
$(document).on('click','#ima',function(){
	var nextURL = "images/nextButton.png";
    var div = document.createElement("div");
    div.className = 'lineProduct';
    div.innerHTML = "<table>"+
    "<tr><td>DealerName:<\/td><td><input type=\"text\" placeholder=\"DealerName..!\"><\/td><\/tr>" +
      "<tr><td>DealerName:<\/td><td><input type=\"text\" placeholder=\"DealerName..!\"><\/td><\/tr>" +
        "<tr><td>Address:<\/td><td><input type=\"text\" placeholder=\"Address..!\"><\/td><\/tr>" +
        "<tr><td>PhoneNumber:<\/td><td><input type=\"text\" placeholder=\"Phonenumber..!\"><\/td><\/tr>" +
        "<tr><td>TINNumber:<\/td><td><input type=\"text\" placeholder=\"TINNumber\"><\/td><td><img class=\"nextLine\" id=\"ima\" alt=\"next\"  src='" + nextURL + "'><\/td><\/tr>"+
        "<\/table>";
    $("#lists")[0].appendChild(div);
});
});