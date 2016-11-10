function addNewLine(){
		var div = document.createElement("div");
		div.className='lineProduct';
		div.innerHTML = "<input type=text class='pid'>" +
	        "<input type=text class='pname'>" +
	        "<input type=text class='quantity'>" +
	        "<input type=text class='pcost'>" +
	        "<input type=text class='lineTotal' value=0>" +
	        "<input type=submit value='next' class='nextLine'>";
		document.body.appendChild(div);
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