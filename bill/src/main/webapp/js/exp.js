function expensecat() {
    var cat = "";
    cat += "<table>";
    cat += "<tr><th>Category Details<\/th>";
    cat += "<tr><td>CategoryId*:<\/td><td><input type=\"text\" id=\"catid\"  placeholder=\"CategoryId..!\"><\/td><\/tr>";
    cat += "<tr><td>CategoryName*:<\/td><td><input type=\"text\" id=\"cname\"  placeholder=\"CategoryName..!\"><\/td><\/tr>";
    cat += "<tr><td><button id=\"submit\">Submit<\/button><\/td><td><button id=\"update\">Update<\/button><\/td><\/tr>";
    cat += "<\/table>";
    $('#lists')[0].innerHTML = cat;

}

function expense() {
    var exp = "";
    exp += "<table>";
    exp += "<tr><th>Expenses<\/th><\/tr>";
    exp += "<tr><td>ExpenseId*:<\/td><td><input type=\"text\"id=\"expid\" placeholder=\"ExpenseId..!\"><\/td><\/tr>";
    exp += "<tr><td>CategoryId*:<\/td><td><input type=\"text\"id=\"catid\"  placeholder=\"CategoryId..!\"><\/td><\/tr>";
    exp += "<tr><td>CategoryName*:<\/td><td><input type=\"text\"id=\"cname\"  placeholder=\"CategoryName..!\"><\/td><\/tr>";
    exp += "<tr><td>ExpenseDate*:<\/td><td><input type=\"date\"id=\"expdate\"  placeholder=\"ExpenseDate..!\"><\/td><\/tr>";
    exp += "<tr><td>Description*:<\/td><td><input type=\"text\"id=\"description\"  placeholder=\"Description..!\"><\/td><\/tr>";
    exp += "<tr><td>Amount*:<\/td><td><input type=\"text\"id=\"amount\"  placeholder=\"Amount..!\"><\/td><\/tr>";
    exp += "<tr><td><button id=\"sub\">Submit<\/button><\/td><td><button id=\"up\">Update<\/button><\/td><\/tr>";
    exp += "<\/table>";
    $('#lists')[0].innerHTML = exp;
}
