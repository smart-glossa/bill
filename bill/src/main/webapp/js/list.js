function addNewLine(){
		var div = document.createElement("div");
		div.innerHTML = "<input type=text class='pid'>" +
	        "<input type=text class='pname'>" +
	        "<input type=text class='quantity'>" +
	        "<input type=text class='pcost'>" +
	        "<input type=text class='lineTotal'>" +
	        "<input type=submit value='next' class='nextLine'>";
		document.body.appendChild(div);
}