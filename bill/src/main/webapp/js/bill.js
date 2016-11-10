$(document).ready(function(){
	addNewLine();
	$(document).on("click", "#submit", function(){
		var pId = $('#pId').val();
		var pName = $('#pName').val();
		var cost = $('#cost').val();
		var url = "http://localhost:8080/bill/bill?operation=addProduct&pid=" + pId +"&pname="+ pName +"&cost="+ cost;
		$.ajax({
				url: url,
				type:'POST'})
		.done(function(result){
			console.log(result);
			
		})
		.fail(function(result){
			console.log(result);
		});
	});
	
	$(document).on("click", ".nextLine", function(){
		if ($(this).attr("value") === "next") {
			$(this).attr("value", "X");
			addNewLine();
		} else if ($(this).attr("value") === "X") {
			$(this).parent().remove();
		}
		
	});
	
	$(document).on("keyup", ".pid", function() {
		var div = $(this).parent();
		var getProductUrl = "/bill/bill?operation=getProduct&pid=" + $(this).val();
		$.ajax({
			url: getProductUrl,
			type: "POST"
		})
		.done(function(result) {
			result = JSON.parse(result);
			var pname = result.name;
			var pcost = result.cost;
			div.children(".pname").val(pname)
			div.children(".pcost").val(pcost)
		})
	});
	
	$(document).on("keyup", ".quantity", function() {
		var div = $(this).parent();
		var a = div.children(".quantity").val();
		var b = div.children(".pcost").val();
		var c = a * b;
		div.children(".lineTotal").val(c);
	})
	
});

