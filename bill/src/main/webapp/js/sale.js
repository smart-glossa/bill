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
    salemeta += "<div class=\"saleline\">";
    salemeta += "<h2> Add SaleLineItem <\/h2>";
    salemeta += "<table>";
    salemeta += "<tr>";
    salemeta += "<td>SaleId*:<\/td>";
    salemeta += "<td><input type=\"text\"id=\"saleId\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>ProductId*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"productId\" id=\"productId\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<tr>";
    salemeta += "<td>Quantity*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"Quantity\" id=\"quantity\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<td>Cost*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"Cost\" id=\"cost\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<td>Total*:<\/td>";
    salemeta += "<td><input type=\"text\" placeholder=\"Total\" id=\"cost\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<td><input type=\"button\" value=\"Add\" id=\"addline\">";
    salemeta += "<input type=\"button\" value=\"Update\" id=\"updateSaleLineItem\"><\/td>";
    salemeta += "<\/tr>";
    salemeta += "<\/table>";
    salemeta += "<\/div>";
    $('#lists')[0].innerHTML = salemeta;
}
function calculateBillAmount() {
    var count = $("div.lineProduct").length;
    var sum = 0;
    for (var i = 0; i < count; i++) {
        var div = $("div.lineProduct")[i];
        sum += parseInt($($("div.lineProduct")[i]).find(".lineTotal").val());
    }
    $(".billTotal").text(sum);
}

function balanceAmount() {
    var sum = $(".billTotal").text();
    var balance = 0;
    if ($("#cash").val().trim() == "") {
        $("#balance").val("");
    } else {
        var cash = $("#cash").val();
        balance = cash - sum;
        $("#balance").val(balance);
    }

}
