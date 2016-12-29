$(document).ready(function(){
	$(document).on("click","#add",function(){
		var purchaseId = $('#pId').val();
		var billDate = $('#Date').val();
		var vat = $('#vat').val();
		var discount = $('#discount').val();
		var billTotal = $('#tot').val();
		var payDate = $('#date').val();
		var paidAmount = $('#amount').val();
		var url ="http://localhost:8080/bill/purchase?operation=addPurchase&purchaseId="+purchaseId+"&billDate="+billDate+"&vat="+vat+"&discount="+discount+"&billTotal="+billTotal +"&payDate="+payDate+"&paidAmount="+paidAmount;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		alert(result);
	})
	.fail(function(result){
		alert(result);
	});
	});


		$(document).on('click','#update',function(){
			var purchaseId = $('#pId').val();
			var billDate = $('#Date').val();
			var vat = $('#vat').val();
			var discount = $('#discount').val();
			var billTotal = $('#tot').val();
			var url = "/bill/purchase?operation=update&purchaseId="+purchaseId+"&billDate="+billDate+"&vat="+vat+"&discount="+discount+"&billTotal="+billTotal;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				alret(result);
			})
			.fail(function(result){
			alert(result);
		})
		});
	});
	
