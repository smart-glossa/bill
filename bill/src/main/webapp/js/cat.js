$(document).ready(function(){
	$(document).on("click","#submit",function(){
		var id=$('#catid').val();
		var name=$('#cname').val();
		if(id==""){
        alert("please enter CategoryId");
        return;
		}
		if(name==""){
			alert("please enter your categoryName");
			return;
		}
		var url="/bill/cat?operation=add&catid="+id+"&cname="+name;
		$('input[type=text]').val();
		$.ajax({
			url:url,
			type:'post'
		})
		.done(function(result){
			result=JSON.parse(result);
			if(result.status=="success"){
				alert("Successfully Added");
			}
		})
		.fail(function(result){
			alert("Error Occurs");
		})
	});
	$(document).on("click","#update",function(){
		var id=$('#catid').val();
		var name=$('#cname').val();
		if(id==""){
			alert("Please Enter CategoryId");
			return;
		}
		if(name==""){
				alert("Please Enter CategoryName");
				return;
		}
		var url="/bill/cat?operation=update&catid="+id+"&cname="+name;
		$.ajax({
			url:url,
			type:'post'
		})
		.done(function(result){
			result=JSON.parse(result);
			if(result.status=="1"){
			alert("Update Successfully");
			}
		})
		.fail(function(result){
			alert(result);
		});
		
				
	
	});
	$(document).on('keyup','#catid',function(){
		var cid=$('#catid').val();
		if(cid!=""){
		var url="/bill/cat?operation=getOne&catid="+cid;
		$.ajax({
			url:url,
			type:'post'
		})
		.done(function(result){
			result=JSON.parse(result);
			$("#cname").val(result.cname);
		});
		}
	});
	$(document).on('click','#getall',function(){
		var url="/bill/cat?operation=getAll";
		$.ajax({
			url:url,
			type:'post'
		})
		.done(function(result){
			var array=JSON.parse(result);
			var table="<table border=2>"
				table+="<tr><th>CategoryId</th><th>CategoryName</th></tr>";
			for(i=0;i<array.length;i++){
				table+="<tr>";
				table+="<td>"+array[i].catid+"</td>";
				table+="<td>"+array[i].cname+"</td>";
				table+="</tr>";
			}
			table+="</table>";
			$(".cat")[0].innerHTML=table;
		});
	});
});