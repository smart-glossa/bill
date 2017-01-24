function customer() {
    var cus = "";
    cus += "<table>";
    cus += "        <tr>";
    cus += "			<td>customer Id<\/td><td><input type=\"text\" placeholder=\"CustomerId..!\" id=\"id\"><\/td>";
    cus += "		<\/tr>";
    cus += "		<tr>";
    cus += "			<td>customer Name<\/td><td><input type=\"text\" placeholder=\"CustomerName..!\" id=\"name\"><\/td>";
    cus += "		<\/tr>";
    cus += "		<tr>";
    cus += "			<td>Address<\/td><td><textarea placeholder=\"Address..!\" id=\"add\"><\/textarea><\/td>";
    cus += "		<\/tr>";
    cus += "		<tr>";
    cus += "			<td>Phone Number<\/td><td><input type=\"number\" placeholder=\"PhoneNumber..!\" id=\"num\"><\/td>";
    cus += "		<\/tr>";
    cus += "           <tr>";
    cus += "           <td><\/td>";
    cus += "				<td>";
    cus += "					<input type=\"submit\" id=\"submit\" value=\"Submit\">";
    cus += "<input type=\"submit\" id=\"update\"  value=\"Update\">";
    cus += "				<\/td>";
    cus += "			<\/tr>";
    cus += "		<\/table>";
    $('#lists')[0].innerHTML = cus;

}

function customerBill() {
    var cusBill = "";
    cusBill += "<table>";
    cusBill += "        <tr>";
    cusBill += "			<td>customer Id<\/td><td><input type=\"text\" placeholder=\"CustomerId..!\" id=\"cid\"><\/td>";
    cusBill += "		<\/tr>";
    cusBill += "		<tr>";
    cusBill += "			<td>Sale Id<\/td><td><input type=\"text\" placeholder=\"SaleId..!\" id=\"sid\"><\/td>";
    cusBill += "		<\/tr>";
    cusBill += "		<tr>";
    cusBill += "           <tr>";
    cusBill += "           <td><\/td>";
    cusBill += "				<td>";
    cusBill += "					<input type=\"submit\" id=\"sub\" value=\"Submit\">";
    cusBill += "				<\/td>";
    cusBill += "			<\/tr>";
    cusBill += "		<\/table>";
    $('#lists')[0].innerHTML = cusBill;

}
