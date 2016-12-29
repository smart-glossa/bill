$(document).ready(function(){
	$(document).on("click","#submit",function(){
		var cid=$("#id").val();
		var cname=$("#name").val();
		var caddr=$("#add").val();
		var cphno=$("#num").val();
		if(cid==""){
				alert("Please Enter CustomerId");
				return;
		}
		if(cname=""){
			alert("Please Enter CustomerName");
			return;
		}
		if(caddr==""){
			alert("Please Enter Address");
			return;
		}
		if(cphno==""){
			alert("Please Enter PhoneNumber");
			return;
		}
		var url="http://localhost:8080/bill/customer?operation=cusAdd&cid="+cid+"&cname="+cname+"&caddr="+caddr+"&cphno="+cphno;
		$.ajax({
			url:url,
			type:'POST'
		}).done(function(result){
			
				alert("Successfully Added");
		}).fail(function(result){
				alert("Please check details");
				})

});
	$(document).on("click","#update",function(){
		var cid=$("#id").val();
		var cname=$("#name").val();
		var caddr=$("#add").val();
		var cphno=$("#num").val();
		if(cid==""){
			alert("Please Enter CustomerId");
			return;
		}
		if(cname==""){
			alert("Please Enter CustomerName");
			return;
		}
		if(cphno==""){
			alert("Please Enter Address");
			return;
		}
		if(caddr==""){
			alert("Please Enter PhoneNumber");
			return;
		}
		var url="http://localhost:8080/bill/customer?operation=cusUpdate&cid="+cid+"&cname="+cname+"&caddr="+caddr+"&cphno="+cphno;
		$.ajax({
			url:url,
			type:'POST'
		}).done(function(result){
			alert("Successfully Updated");
		}).fail(function(result){
			alert("Error occurs");
		})
	});
	$(document).on("keyup","#id",function(){
		var cid=$("#id").val();
		if(cid !==""){
			var url="/bill/customer?operation=cusOne&id="+cid;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				result=JSON.parse(result);
				$("#name").val(result.name);
				$("#add").val(result.address);
				$("#num").val(result.phonenumber);
			});
		}
	});
	$(document).on("click","#getAll",function(){
		var url="http://localhost:8080/bill/customer?operation=cusAll";
		$.ajax({
			url:url,
			type:'POST'
		})
		.done(function(result){
			var array=JSON.parse(result);
			var table="<table border=2>";
			table+="<tr><th>CustomerId</th><th>CustomerName</th><th>Address</th><th>PhoneNumber</th></tr>";
			for(i=0;i<array.length;i++){
				table+="<tr>";
				table+="<td>"+array[i].cid+"</td>";
				table+="<td>"+array[i].name+"</td>";
				table+="<td>"+array[i].address+"</td>";
				table+="<td>"+array[i].phonenumber+"</td>";
				table+="</tr>";
				
			}
			table+="</table>";
			$('.table')[0].innerHTML=table;
		});
	});
	
	
	
});