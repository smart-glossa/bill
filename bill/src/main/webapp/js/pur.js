function purchaseproduct() {
    var pur = "";
    pur += "<div class=\"pur\">";
    pur += "<center><h2> ADD PURCHASE <\/h2><\/center>";
    pur += "<table>";
    pur += "<tr>";
    pur += "<th>Purchase Id <\/th><th>Bill Date<\/th><th>Value Added Tax<\/th><th> Discount<\/th><th>Bill Total<\/th><th>Payment Date</th><th>Paid Amount</th>";
    pur += "</tr>";
    pur += "<tr>";
    pur += "<td><input type = \"text\" placeholder=\"purchase Id..!\" id=\"pId\"><\/td>";
    pur += "<td><input type=\"date\" placeholder=\"Bill Date..!\" id=\"date\"><\/td>";
    pur += "<td><input type=\"text\" placeholder=\"Value Added Tax..!\" id=\"vat\"><\/td>";
    pur += "<td><input type=\"text\" placeholder=\"Discount..!\" id=\"discount\"><\/td>";
    pur += "<td><input type=\"text\" placeholder=\"Bill Total..!\" id=\"tot\"<\/td>";
    pur += "<td><button id=\"paid\">AmountPaid<\/button><\/td>";
    pur += "<td><input type=\"submit\" value=\"Update\" id=\"update\"><\/td>";
    pur += "<td><img src=\"images/nextButton.png\" id=\"ima\"><\/td>";
    pur += "<\/tr>";
    pur += "<\/table>";
    pur += "<\/div>";
    $('#lists')[0].innerHTML = pur;
}
function purchaseLineItem() {
    var purLine = "";
    purLine += "<div class=\"purLine\">";
    purLine += "<h2>PurchaseLineItem<\/h2>";
    purLine += "<table>";
    purLine += "<tr>";
    purLine += "<td>purchase Line Id:<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"purchase Line Id\" id=\"lineId\"><\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td>Purchase Id:<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"Purchase Id\" id=\"pId\"><\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td>Product Id:<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"product Id\" id=\"proId\"><\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td>Quantity :<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"quantity\" id=\"quantity\"<\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td>Buy Price:<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"Buy Price\" id=\"buy\"<\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td>Sell Price:<\/td>";
    purLine += "<td><input type=\"text\" placeholder=\"Sell Price\" id=\"sel\"><\/td>";
    purLine += "<\/tr>";
    purLine += "<tr>";
    purLine += "<td><input type=\"submit\"  id=\"sub\">";
    purLine += "<input type=\"button\" value=\"Update\" id=\"up\"><\/td>";
    purLine += "<\/tr>";
    purLine += "<\/table>";
    purLine += "<\/div>";
    $('#lists')[0].innerHTML = purLine;
}
