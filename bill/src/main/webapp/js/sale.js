function salemeta() {
    var salemeta = "";
    salemeta += "<div class=\"salemeta\">";
    salemeta += "<h2> Add SaleMetaData <\/h2>";
    salemeta += "<table>";
    salemeta += "<tr>";
    salemeta += "<td>Sale Id*:<\/td>";
    salemeta += "<td><input type=\"text\" id=\"saleId\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>Bill Date*:<\/td>";
    salemeta += "<td><input type=\"date\"id=\"billdate\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>ValueAddedTax*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"VAT\" id=\"vat\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>Discount*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"discount\" id=\"discount\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>Bill Total*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"billTotal\" id=\"billtotal\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<\/table>";
    salemeta += "<\/div>";
    salemeta += "<div class=\"getSaleMetaData\"><\/div>";
    $('#lists')[0].innerHTML = salemeta;
}

function salepay() {
    var salepay = "";
    salepay += "<div class=\"salepay\">";
    salepay += "<h2> Add SalePayment <\/h2>";
    salepay += "<table>";
    salepay += "<tr>";
    salepay += "<td>Pay Date*:<\/td>";
    salepay += "<td><input type=\"date\" placeholder=\"PayDate\" id=\"payDate\"><\/td>";
    salepay += "<\/tr>";
    salepay += "<tr>";
    salepay += "<td>PaidAmount*:<\/td>";
    salepay += "<td><input type=\"text\" placeholder=\"PaidAmount\" id=\"paidAmount\"><\/td>";
    salepay += "<\/tr>";
    salepay += "<tr>";
    salepay += "<td><input type=\"button\" value=\"Add\" id=\"addpay\">";
    salepay += "<input type=\"button\" value=\"Update\" id=\"updateSalePayment\"><\/td>";
    salepay += "<\/tr>";
    salepay += "<\/table>";
    salepay += "<\/div>";
    salepay += "<div class=\"getsalepay\"><\/div>";
    $('#lists')[0].innerHTML = salepay;
}

function saleline() {
    var saleline = "";
    saleline += "<div class=\"saleline\">";
    saleline += "<h2> Add SaleLineItem <\/h2>";
    saleline += "<table>";
    saleline += "<tr>";
    saleline += "<td>SaleLineId*:<\/td>";
    saleline += "<td><input type=\"text\" id=\"saleLineId\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<tr>";
    saleline += "<td>SaleId*:<\/td>";
    saleline += "<td><input type=\"text\"id=\"saleId\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<tr>";
    saleline += "<td>ProductId*:<\/td>";
    saleline += "<td><input type=\"text\" placeholder=\"productId\" id=\"productId\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<tr>";
    saleline += "<td>Quantity*:<\/td>";
    saleline += "<td><input type=\"text\" placeholder=\"Quantity\" id=\"quantity\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<td>Cost*:<\/td>";
    saleline += "<td><input type=\"text\" placeholder=\"Cost\" id=\"cost\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<td><input type=\"button\" value=\"Add\" id=\"addline\">";
    saleline += "<input type=\"button\" value=\"Update\" id=\"updateSaleLineItem\"><\/td>";
    saleline += "<\/tr>";
    saleline += "<\/table>";
    saleline += "<\/div>";
    saleline += "<div class=\"getsaleline\"><\/div>";
    $('#lists')[0].innerHTML = saleline;
}
