function salemeta() {
    var salemeta = "";
    salemeta += "<div class=\"salemeta\">";
    salemeta += "<center><h2> Add SaleMetaData <\/h2><\/center>";
    salemeta += "<table>";
    salemeta += "<tr>";
    salemeta += "<th>Sale Id<\/th><th>Bill Date<\/th><th>ValueAddedTax<\/th><th>Discount<\/th><th>Bill Total<\/th>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td><input type=\"text\" id=\"saleId\" placeholder=\"sale Id\"><\/td>";
    salemeta += "<td><input type=\"date\"id=\"billdate\"><\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"VAT\" id=\"vat\"><\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"discount\" id=\"discount\"><\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"billTotal\" id=\"billtotal\"><\/td>";
    salemeta += "<td><button id=\"salpaid\">AmountPaid<\/button><\/td>";
    salemeta += "<td><img src=\"images/nextButton.png\" id=\"ima\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<\/table>";
    salemeta += "<\/div>";
    $('#lists ')[0].innerHTML = salemeta;
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
    $('#lists')[0].innerHTML = saleline;
}
