function purchaseproduct() {
    var pur = "";
    pur += "<div class=\"pur\">";
    pur += "<center><h2> ADD PURCHASE <\/h2><\/center>";
    pur += "<table>";
    pur += "<tr>";
    pur += "<th>Product Id</th><th>Purchase Id <\/th><th>ProductName<\/th><th>Buy Price<\/th><th>Sell Price<\/th><th>Quantity<\/th><th>Amount</th>";
    pur += "</tr>";
    pur += "<tr>";
    pur += "<td><input type=\"text\" placeholder=\"ProductId..!\" id=\"pro\"<\/td>"
    pur += "<td><input type = \"text\" placeholder=\"purchase Id..!\" id=\"pId\"><\/td>";
    pur +="<td><input type=\"text\"placeholder=\"ProductName..!\"id=\"pur\"><\/td>";
    pur += "<td><input type=\"text\" placeholder=\"Buy Price..!\" id=\"buy\"><\/td>";
    pur += "<td><input type=\"text\" placeholder=\"Sell Price..!\" id=\"sel\"<\/td>"
    pur += "<td><input type=\"text\" placeholder=\"Quantity..!\" id=\"quantity\"<\/td>"
    pur += "<td><input type=\"text\" placeholder=\"Total..!\" id=\"amu\"<\/td>";
    pur += "<td><button id=\"purpaid\">AmountPaid<\/button><\/td>";
    pur += "<td><img src=\"images/nextButton.png\" id=\"ima\"><\/td>";
    pur += "<\/tr>";
    pur += "<\/table>";
    pur += "<\/div>";
   
    $('#lists')[0].innerHTML = pur;
}
