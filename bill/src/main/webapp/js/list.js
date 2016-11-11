function addNewLine(){
		var div = document.createElement("div");
		div.className='lineProduct';
		div.innerHTML = "<input type=text class='pid'>" +
	        "<input type=text class='pname'>" +
	        "<input type=text class='quantity'>" +
	        "<input type=text class='pcost'>" +
	        "<input type=text class='lineTotal' value=0>" +
	        "<input type=submit value='next' class='nextLine'>";
		
		$(".billing")[0].appendChild(div);
		calculateBillAmount();
}

function calculateBillAmount() {
	var count = $("div.lineProduct").length;
	var sum= 0;
	for (var i =0; i < count; i++) {
		var div = $("div.lineProduct")[i];
		sum += parseInt($($("div.lineProduct")[i]).find(".lineTotal").val());
	}
	$("h2").text(sum);
}

function displayProducts() {
	var url = "/bill/bill?operation=getAllProduct";
	$.ajax({
		url: url,
		type: 'POST'
	})
	.done(function(result){
		var array = JSON.parse(result);
		var query = "<table style='border: 1px solid black'>"
			query += "<tr><th>ProductId</th> <th>ProductName</th>  <th>ProductCost</th></tr>"
			for (var i=0; i<array.length; i++) {
				query += "<tr class='productRow'><td class='productId'>" + array[i].productId + "</td>";
				query += "<td>" + array[i].name + "</td>";
				query += "<td>" + array[i].cost + "</td></tr>";
			}
			query += "</table>"
			$(".displayAll")[0].innerHTML = query;
			
	})
	.fail(function(){
		
	});
	
}