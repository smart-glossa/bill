function customer(){
	var cus="";
	cus += "<table>";
	cus += "        <tr>";
	cus += "			<td>Customer Id<\/td><td><input type=\"text\" id=\"id\"><\/td>";
	cus += "		<\/tr>";
	cus += "		<tr>";
	cus += "			<td>Customer Name<\/td><td><input type=\"text\" id=\"name\"><\/td>";
	cus += "		<\/tr>";
	cus += "		<tr>";
	cus += "			<td>Address<\/td><td><textarea id=\"add\"><\/textarea><\/td>";
	cus += "		<\/tr>";
	cus += "		<tr>";
	cus += "			<td>Phone Number<\/td><td><input type=\"number\" id=\"num\"><\/td>";
	cus += "		<\/tr>";
	cus += "           <tr>";
	cus += "           <td><\/td>";
	cus += "				<td>";
	cus += "					<input type=\"submit\" id=\"submit\" value=\"Submit\">";
	cus += "					<input type=\"submit\" id=\"update\"  value=\"Update\">";
	cus += "				<\/td>";
	cus += "			<\/tr>";
	cus += "		<\/table>";
$('.cus')[0].innerHTML = cus;
	
}
function customerBill(){
	
}
