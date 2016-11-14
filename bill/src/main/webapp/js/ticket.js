$(document).ready(function(){
	$(document).on("click","#sub",function(){
		var rId = $("#rId").val();
		var from = $("#from").val();
		var to = $("#to").val();
		var price = $("#price").val();
		
		var url = "http://localhost:8080/bill/ticket?operation=add&rId=" + rId +"&from="+ from +"&to="+ to +"&price="+ price;
		$.ajax({
			url: url,
			type:'POST'
		})
		.done(function(result){
			if(result==""){
				alert("Added successfully");
				$("#rId").val("");
				$("#from").val("");
				$("#to").val("");
				$("#price").val("");
			}else{
				result = JSON.parse(result);
				if(result.Message=="Error"){
					alert("Error occurs");
				}
			}
			
		})
	});
	
});