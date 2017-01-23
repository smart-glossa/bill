$(document).ready(function() {
    $(document).on('click', '#image', function() {
        var dropdown = "";
        dropdown += "<ul>";
        dropdown += "<li id=\"productli\">Product<\/li>";
        dropdown += "<li id=\"dealerli\">Dealer<\/li>";
        dropdown += "<li id=\"customerli\">Customer<\/li>";
        dropdown += "<li id=\"purchaseli\">Purchase<\/li>";
        dropdown += "<li id=\"salesli\">Sales<\/li>";
        dropdown += "<li id=\"expenseli\">Expenses<\/li>";
        dropdown += "<\/ul>";
        $('.menu')[0].innerHTML = dropdown;
        $('.menu').toggle();
        $('.menutwo').css("display","none");
    });


    $(document).on('click', '#adddealer', function() {
        var add = "";
        add += "<table>";
        add += "<tr>";
        add += "<td>Dealer Id<\/td><td><input type=\"text\" placeholder=\"DealerId..!\" id=\"dealerid\"><\/td>";
        add += "<\/tr>";
        add += "<tr>";
        add += "<td>Dealer Name<\/td><td><input type=\"text\" placeholder=\"DealerName..!\" id=\"name\"><\/td>";
        add += "<\/tr>";
        add += "<tr>";
        add += "<td>Address<\/td><td><textarea placeholder=\"Address..!\" id=\"address\"><\/textarea><\/td>";
        add += "<\/tr>";
        add += "<tr>";
        add += "<td>Phone Number<\/td><td><input type=\"number\" placeholder=\"PhoneNumber..!\" id=\"phone\"><\/td>";
        add += "<\/tr>";
        add += "<tr>";
        add += "<td>TIN Number<\/td><td><input type=\"number\" placeholder=\"TINNumber..!\" id=\"tin\"><\/td><td><img src=\"images/nextButton.png\" id=\"ima\"><\/td>";
        add += "<\/tr>";
        add += "<tr>";
        add += "<td>";
        add += "<input type=\"submit\" id=\"submit\" value=\"Submit\">";
        add += "<input type=\"submit\" id=\"update\"  value=\"Update\">";
        add += "<\/td>";
        add += "<\/tr>";
        add += "<\/table>";

        $('#lists')[0].innerHTML = add;
    });

    $(document).on('click', '#dealerbill', function() {
        var billadd = "";
        billadd += "<table>";
        billadd += "<tr><td>dealerId<\/td><td><input type=\"text\" id=\"did\" placeholder=\"dealerId\"\/><\/td><\/tr>";
        billadd += "<tr><td>purchaseId<\/td><td><input type=\"text\" id=\"pid\" placeholder=\"purchaseId\"\/><\/td><\/tr>";
        billadd += "<tr><td><input type=\"submit\" id=\"add\" value=\"Add\"><\/td><\/tr>";
        billadd += "<\/table>";
        $('#lists')[0].innerHTML = billadd;
    });



});
