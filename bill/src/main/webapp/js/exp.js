function expensecat(){
	var cat="";
	cat += "<table>";
	cat += "<tr><th>Category Details<\/th>";
	cat += "<tr><td>CategoryId*:<\/td><td><input type=\"text\" id=\"catid\"><\/td><\/tr>";
	cat += "<tr><td>CategoryName*:<\/td><td><input type=\"text\" id=\"catname\"><\/td><\/tr>";
	cat += "<\/table>";
	$('.exp')[0].innerHTML=cat;

}
function expense() {
	var exp="";
	exp += "<table>";
	exp += "<tr><td>Expenses<\/td><\/tr>";
	exp += "<tr><td>ExpenseId*:<\/td><td><input type=\"text\"id=\"expid\"><\/td><\/tr>";
	exp += "<tr><td>CategoryId*:<\/td><td><input type=\"text\"id=\"catid\"><\/td><\/tr>";
	exp += "<tr><td>ExpenseDate*:<\/td><td><input type=\"text\"id=\"expdate\"><\/td><\/tr>";
	exp += "<tr><td>Description*:<\/td><td><input type=\"text\"id=\"des\"><\/td><\/tr>";
	exp += "<tr><td>Amount*:<\/td><td><input type=\"text\"id=\"amount\"><\/td><\/tr>";
	exp += "<\/table>";
	$('.expense')[0].innerHTML=exp;

	
}