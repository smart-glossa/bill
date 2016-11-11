$(document).ready(function(){
	addNewLine();
	displayProducts();
	$(document).on("click", "#submit", function(){
		var pId = $('#pId').val();
		var pName = $('#pName').val();
		var cost = $('#cost').val();
		if(pId==""){
			alert("Please Enter ProductId");
			$("#pId").focus();
			return;
		}
		if(pName==""){
			alert("Please Enter Product Name");
			$("#pName").focus();
			return;
		}
		if(cost==""){
			alert("Please Enter cost");
			$("#cost").focus();
			return;
		}
		var url = "http://localhost:8080/bill/bill?operation=addProduct&pid=" + pId +"&pname="+ pName +"&cost="+ cost;
		$.ajax({
				url: url,
				type:'POST'})
		.done(function(result){
			if(result==""){
				alert("Added SuccessFully");
				$('#pId').val("");
				$('#pName').val("");
				$('#cost').val("");
			}
			else{
				result = JSON.parse(result);
				if(result.Message=="Error"){
					alert("Error occurs");
				}
			}
			
		})
		.fail(function(result){
			console.log(result);
		});
		displayProducts();
	});
	
	$(document).on("click", ".nextLine", function(){
		if ($(this).attr("value") === "next") {
			$(this).attr("value", "X");
			addNewLine();
		} else if ($(this).attr("value") === "X") {
			$(this).parent().remove();
			calculateBillAmount();
		}
		
	});
	
	$(document).on("keyup", ".pid", function() {
		if ($(this).val()=="") {
			var div = $(this).parent();
			div.children(".pname").val("")
			div.children(".quantity").val("");
			div.children(".pcost").val("");
			div.children(".lineTotal").val(0);
			calculateBillAmount();
			
		}
		var div = $(this).parent();
		var getProductUrl = "/bill/bill?operation=getProduct&pid=" + $(this).val();
		$.ajax({
			url: getProductUrl,
			type: "POST"
		})
		.done(function(result) {
			result = JSON.parse(result);
			if (jQuery.isEmptyObject(result)) {
				return;
			}
			var pname = result.name;
			var pcost = result.cost;
			div.children(".pname").val(pname)
			div.children(".quantity").val(1);
			div.children(".pcost").val(pcost)
			var a = div.children(".quantity").val();
			var b = div.children(".pcost").val();
			var c = a * b;
			div.children(".lineTotal").val(c);
			calculateBillAmount();
		})
	});
	
	$(document).on("keyup", ".quantity", function() {
		var div = $(this).parent();
		var a = div.children(".quantity").val();
		var b = div.children(".pcost").val();
		var c = a * b;
		div.children(".lineTotal").val(c);
		calculateBillAmount();
	})
	$(document).on("keyup", "#pId", function(){
		var pId = $('#pId').val();
		if(pId!=""){
			var getProductUrl = "/bill/bill?operation=getProduct&pid=" + pId;
			$.ajax({
				url: getProductUrl,
				type: "POST"
			})
			.done(function(result){
				result = JSON.parse(result);
				var pName = result.name;
				var cost = result.cost;
				$("#pName").val(pName);
				$("#cost").val(cost);
			})
			.fail(function(result){
				alert("Some Errors Please Enter correct value");
			});
		}
		else{
			$("#pName").val("");
			$("#cost").val("");
		}
		
	});
	$(document).on("keypress",".add",function(key){
		if(key.which==13){
			$("#submit").click();
		}
	})
	$(document).on("click","#update",function(){
		var pId = $('#pId').val();
		var pName = $('#pName').val();
		var cost = $('#cost').val();
		var url = "http://localhost:8080/bill/bill?operation=updateProduct&pid="+ pId +"&name=" + pName +"&cost="+ cost ;
		$.ajax({
				url: url,
				type:'POST'})
		.done(function(result){
			if(result==""){
				alert("Updated SuccessFully");
				$('#pId').val("");
				$('#pName').val("");
				$('#cost').val("");
			}
			else{
				result = JSON.parse(result);
				if(result.Message=="Error"){
					alert("Error occurs");
				}
			}
			
		})
		.fail(function(result){
			console.log(result);
		});
		displayProducts();
	})
	
	$(document).on("click",".productRow",function(){
		var tag = $(this);
		var pid = $(this).children(".productId")[0].innerHTML;
		var url = "/bill/bill?operation=deleteProduct&pid=" + pid
		$.ajax({
			url: url,
			type: 'POST'
		})
		.done(function(result) {
			tag.remove();
		})
		.fail(function(result) {
			console.log("")
		});
	})
	
//	$(document).on("keypress",".lineProduct",function(key){
//		if(key.which==13){
//			$(".nextLine").click();
//		}
//	})
	
});

