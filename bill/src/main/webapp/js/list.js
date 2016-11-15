function addNewLine() {
	var nextURL = "http://copasi.org/images/next.png";
	var div = document.createElement("div");
	div.className = 'lineProduct';
	div.innerHTML = "<input type=text class='pid'>"
			+ "<input type=text class='pname' readonly>"
			+ "<input type=text class='quantity'>"
			+ "<input type=text class='pcost' readonly>"
			+ "<input type=text class='lineTotal' value=0 readonly>"
			+ "<img class='nextLine' alt='next' width='25px' height='25px' src='" + nextURL + "'/>";

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
	if($("#cash").val().trim()==""){
		$("#balance").val("");
	}else{
	var cash = $("#cash").val();
	balance = cash - sum;
	$("#balance").val(balance);
	}

}
function displayProducts() {
	var url = "/bill/bill?operation=getAllProduct";
	var imgURL = "http://images.all-free-download.com/images/graphiclarge/round_red_close_button_5095.jpg";
	$.ajax({
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
							query += "<td> <img class='deleteProduct' src='" + imgURL + "' width='25px' height='25px'/></td></tr>"
						}
						query += "</table>"
						$(".displayAll")[0].innerHTML = query;

					}).fail(function() {

			});

}