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
	$("h2").text(sum);
}
function balanceAmount() {
	var sum = $("h2").text();
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
	var imgURL = "images/deleteButton.jpg";
	$
			.ajax({
				url : url,
				type : 'POST'
			})
			.done(
					function(result) {
						var array = JSON.parse(result);
						var query = "<table style='border: 1px solid black'>"
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
						$(".displayAll")[0].innerHTML = query;

					}).fail(function() {

			});

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
function printPreview() {
	var printWindow = window.open('', '', 'height=400,width=800');
	printWindow.document.write('<html><head><title>BILLING</title>');
	printWindow.document.write('</head><body >');
	printWindow.document.write('<div>');
	printWindow.document.write("<table>");
	printWindow.document.write("<tr><th>Serial Number</th><th>Product Name</th><th>Quantity</th><th>cost</th><th>Total<th></tr>");
	printWindow.document.write("</table></div>");
	printWindow.document.write('</body></html>');
	printWindow.document.close();
	printWindow.focus();
}