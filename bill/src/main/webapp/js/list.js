function addNewLine() {
	var nextURL = "images/nextButton.png";
	var div = document.createElement("div");
	div.className = 'lineProduct';
	div.innerHTML = "<input type=text class='pid'>"
			+ "<input type=text class='pname' readonly>"
			+ "<input type=text class='quantity'>"
			+ "<input type=text class='pcost' readonly>"
			+ "<input type=text class='lineTotal' value=0 readonly>"
			+ "<img class='nextLine' alt='next' width='25px' height='25px' src='"
			+ nextURL + "'/>";

	$(".billing")[0].appendChild(div);
	calculateBillAmount();
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
function displayProducts() {
	var url = "/bill/bill?operation=getAllProduct";
	var div= document.createElement("div");
	var imgURL = "images/deleteButton.jpg";
	$
			.ajax({
				url : url,
				type : 'POST'
			})
			.done(
					function(result) {
						var array = JSON.parse(result);
						div.className = "displayAll";
						var query = '<h3>Display Products</h3>'
						     + "<table style='border: 1px solid black'>";
						query += "<tr><th>ProductId</th> <th>ProductName</th>  <th>ProductCost</th><th>Delete</th></tr>"
						for (var i = 0; i < array.length; i++) {
							query += "<tr class='productRow'><td class='productId'>"
									+ array[i].productId + "</td>";
							query += "<td>" + array[i].name + "</td>";
							query += "<td>" + array[i].cost + "</td>";
							query += "<td> <img class='deleteProduct' src='"
									+ imgURL
									+ "' width='25px' height='25px'/></td></tr>"
						}
						query += "</table>"
						div.innerHTML = query;
					}).fail(function() {

			});
	return div;
}

function calculateLineTotal(div) {
	var a = div.children(".quantity").val();
	var b = div.children(".pcost").val();
	var c = a * b;
	div.children(".lineTotal").val(c);
	calculateBillAmount();
	balanceAmount();
}

function checkAndRemoveDuplicate(div) {
	var duplicate = false;
	var currentPidValue = div.children(".pid").val();
	var currentQuantity = parseInt(div.children(".quantity").val());
	var parentSiblings = div.siblings();
	for (var i = 0; i < parentSiblings.length; i++) {
		if (currentPidValue === $(parentSiblings[i]).children(".pid").val()) {
			var siblingQuantity = parseInt($(parentSiblings[i]).children(
					".quantity").val());
			var sum = currentQuantity + siblingQuantity;
			$(parentSiblings[i]).children(".quantity").val(sum);
			calculateLineTotal($(parentSiblings[i]));
			duplicate = true;
			break;
		}
	}
	return duplicate;
}
function previewBillReceipt() {
	var printWindow = window.open('', '', 'height=400,width=800');
	printWindow.document.write(getBillDetails());
	printWindow.document.close();
	printWindow.focus();
}

function getBillDetails() {
	var htmlFile = "";
	htmlFile += '<html><head><title>RECEIPT</title>';
	htmlFile += '</head><body >';
	htmlFile += "<center><h3>SMARTGLOSSA RESTAURANT<br>Chennai<br>Website: www.smartglossa.com</h3> </center>";
	htmlFile += '<center><div>';
	htmlFile += "<table>";
	htmlFile += "<tr><th>Serial Number</th><th>Product Name</th><th>Quantity</th><th>cost</th><th>Total<th></tr>";
	var lineProducts = $(".billing")[0].children;
	for (var i = 0; i < lineProducts.length; i++) {
		var div = $(lineProducts[i]);
		if ($(div.children(".pid")[0]).val().trim() !== "") {
			var sno = i + 1;
			var row = "<tr><td>" + sno + "</td>"
			row += "<td>" + $(div.children(".pname")[0]).val() + "</td>";
			row += "<td>" + $(div.children(".quantity")[0]).val() + "</td>";
			row += "<td>" + $(div.children(".pcost")[0]).val() + "</td>";
			row += "<td>" + $(div.children(".lineTotal")[0]).val() + "</td>";
			row += "</tr>";
			htmlFile += row;
		}
	}
	row = "<td></td><td></td><td></td><td><b>Total</b></td><td>" + $(".billTotal").text() + "</td>"
    htmlFile += row;
	htmlFile += "";
	htmlFile += "</table></div></center>";
	htmlFile += "<center><input type='submit' value='PRINT' onclick='window.print()'>";
	htmlFile += "<input type='submit' value='CANCEL' onclick='window.close()'></center>";
	htmlFile += '</body></html>';
	return htmlFile;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return undefined;
}

function applyUserDetails() {
	var username = getCookie("uname");
	
	$.ajax({
		url: "/bill/bill?operation=getUserDetail&uname=" + username,
	    type: 'POST'
	})
	.done(function(result){
		result = JSON.parse(result);
		if (result.Status === "success") {
			$(".UserDetails").text("Welcome Mr. " + result.name);
		}
	})
	.fail(function(result){
		
	});
	
}

function product() {
	var div = document.createElement("div");
	div.className = "addProduct";
	var html = '<h3>Add Product</h3>'
		+ '<table class="Product">'
		+ '<tr><td><label>ProductId:</label></td><td> <input type=text id="pId" class="add"></td></tr>'
		+ '<tr><td><label>Product Name:</label></td> <td><input type=text id="pName" class="add"></td></tr>'
		+ '<tr><td><label>Cost:</label></td><td> <input type=text id="cost" class="add"></td></tr>'
		+ '<tr><td></td><td><input type=submit value="ADD" id="submit">'
		+ '<input type=submit value="UPDATE" id="update"></td></tr>'
		+'</table>';
	 div.innerHTML = html;
	return div;
}

function billing() {
	var div = document.createElement("div");
	div.className = "billSection";
	var strVar="";
	strVar += "<h3 class=\"bill\">Billing<\/h3>";
	strVar += "	<h4>";
	strVar += "		<label>ProductId";
	strVar += "			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	strVar += "			ProductName";
	strVar += "			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	strVar += "			Quantity";
	strVar += "			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	strVar += "			Cost";
	strVar += "			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total<\/label>";
	strVar += "	<\/h4>";
	strVar += "	<div class=\"total\">";
	strVar += "		<h2>Bill Total<\/h2>";
	strVar += "		<h3 class=\"billTotal\">0<\/h3>";
	strVar += "		<h2>Payment<\/h2>";
	strVar += "		<table>";
	strVar += "		<tr><td><label>Cash:<\/label><\/td><td><input type=text id=\"cash\"><\/td><\/tr>";
	strVar += "		<tr><td><label>Balance:<\/label><\/td><td><input type=text id=\"balance\" readonly><\/td><\/tr>";
	strVar += "		<\/table>";
	strVar += "	<\/div>";
	strVar += "	<script>";
	strVar += "		calculateBillAmount()";
	strVar += "	<\/script>";
	strVar += "	<div class=\"billing\"><\/div>";
	strVar += "	<br><br><br><center><input type=button value=\"print\" id=\"print\"><center>";
	div.innerHTML = strVar;
	return div;
}

function menu() {
	var div = document.createElement("div");
	div.className = "menuBar";
	var strVar="";
	strVar += "<img src='images/sample-logo.png' alt=\"logo\" style=\"float:left\" width=70px height=70px>";
	strVar += "<br>";
	strVar += "<ul>";
	strVar += "  <li><a class=\"active\" id='productMenu'>Products<\/a><\/li>";
	strVar += "  <li><a id='billMenu'>Billing<\/a><\/li>";
	strVar += "  <li><a id='paymentMenu'>Payment<\/a><\/li>";
	strVar += "  <li style=\"float:right\"><a id=\"logout\">Logout<\/a><\/li>";
	strVar += "  <li style=\"float:right\"><a class=\"UserDetails\" href=\"#about\">About<\/a><\/li>";
	strVar += "<\/ul>";
	div.innerHTML = strVar;
	return div;
}