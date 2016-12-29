$(document).on('click','#add',function(){
	var saleId = $('#saleId').val();
	var billDate = $('#billDate').val();
	var vat $('#vat').val();
	var discount = $('#discount').val();
	var billTotal = $('#billTotal').val();
	var url = "/bill/SaleMetaData?operation=addSaleMetData&saleid=" +saleId +"&billdate="+ billDate +"&vat="+ vat +"&discount="+ discount +"&billtotal="+ billTotal;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		alert("Added Successfully");
	})
	.fail(function(result){
		alert(result);
	})
});