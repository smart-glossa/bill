$(document).on('click','#addpay',function(){
	var saleId = $('#saleId').val();
	var billDate = $('#billdate').val();
	var vat  =$('#vat').val();
	var discount = $('#discount').val();
	var billTotal = $('#billtotal').val();
	var payDate = $('#payDate').val();
	var paidAmount = $('#paidAmount').val(); 
	if(saleId === ""){
		$('#saleId').focus().css('outline-color','red');
		return false;
	}
    if(billDate === ""){
    	$('#billdate').focus().css('outline-color','red');
    	return false;
    }
   
    if(vat === ""){
    	$('#vat').focus().css('outline-color','red');
    	return false;
    }
    if(discount === ""){
    	$('#discount').focus().css('outline-color','red');
    	return false;
    }
    if(billTotal === ""){
		$('#billtotal').focus().css('outline-color','red');
		return false;
	}
    if(payDate === ""){
    	$('#payDate').focus().css('outline-color','red');
    	return false;
    }
   
    if(paidAmount === ""){
    	$('#paidAmount').focus().css('outline-color','red');
    	return false;
    }
    
	var url = "http://localhost:8080/bill/SaleMetaData?operation=addSaleMetaData&saleid="+ saleId +"&billdate="+ billDate + "&vat=" + vat +"&discount="+ discount +"&billtotal="+ billTotal +"&paydate="+payDate+"&paidAmount="+paidAmount;
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
$(document).on('keyup','#saleId',function(){
	var saleId = $('#saleId').val();
	if(saleId !== "" ){
		
			var url = "http://localhost:8080/bill/SaleMetaData?operation=getOneSaleMetaData&saleid="+ saleId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
				$('#billdate').val(res.billDate);
				$('#vat').val(res.vat);
				$('#discount').val(res.discount);
				$('#billtotal').val(res.billTotal);
			})
			.fail(function(result){
				alert(result);
			})
	}else{
			    $('#billdate').val("");
				$('#vat').val("");
				$('#discount').val("");
				$('#billtotal').val("");
			}
	
});
$(document).on('click','#addline',function(){
	var saleId = $('#saleId').val();
	var productId  =$('#productId').val();
	var quantity = $('#quantity').val();
	var cost = $('#cost').val();
	var url = "http://localhost:8080/bill/SaleLineItem?operation=addSaleLine&saleid="+ saleId +"&productId="+ productId +"&quantity="+ quantity +"&cost="+ cost;
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
$(document).on('keyup','#saleLineId',function(){
	var saleLineId = $('#saleLineId').val();
	if(saleLineId !== "" ){
		
			var url = "http://localhost:8080/bill/SaleLineItem?operation=getOneSaleLineItem&salelineid="+ saleLineId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
				$('#saleId').val(res.saleId);
				$('#productId').val(res.productId);
				$('#quantity').val(res.quantity);
				$('#cost').val(res.cost);
			})
			.fail(function(result){
				alert(result);
			})
	}else{
			    $('#saleId').val("");
				$('#productId').val("");
				$('#quantity').val("");
				$('#cost').val("");
			}
	
});
$(document).on('click','#updateSaleLineItem',function(){
	var saleLineId = $('#saleLineId').val(); 
	var saleId = $('#saleId').val();
	var productId  =$('#productId').val();
	var quantity = $('#quantity').val();
	var cost = $('#cost').val();
	var url = "http://localhost:8080/bill/SaleLineItem?operation=updateSaleLineItem&salelineid="+ saleLineId +"&saleid="+ saleId +"&productid=" + productId +"&quantity="+ quantity +"&cost="+ cost; 
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		alert("updated Successfully");
	})
	.fail(function(result){
		alert(result);
	})
})
$(document).on('click','.saleline', function() {
	var url = "http://localhost:8080/bill/SaleLineItem?operation=getSaleLineItem";
    $.ajax({
            url: url,
            type: 'POST'
        })
        .done(function(result) {
            var res = JSON.parse(result);
            var length = res.length;
            var table = '<table>'
            table += '<tr><th>SaleLineId</th><th>SaleId</th><th>ProductId</th><th>Quantity</th><th>Cost</th></tr>';
            for (i = 0; i < length; i++) {
                table += '<tr class="row">'
                table += '<td>' + res[i].saleLineId + '</td>';
                table += '<td>' + res[i].saleId + '</td>';
                table += '<td>' + res[i]. productId+ '</td>';
                table += '<td>' + res[i].quantity + '</td>';
                table += '<td>' + res[i]. cost + '</td>';
            }
            table += '</table>';
            $('.getsaleline')[0].innerHTML = table;
        })
        .fail(function(result) {
            alert(result);
        });
})

