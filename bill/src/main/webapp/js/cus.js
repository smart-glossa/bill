$(document).ready(function() {
$(document).on("click","#submit",function() {
				var cid = $("#id").val();
				var cname = $("#name").val();
				var caddr = $("#add").val();
				var cphno = $("#num").val();
				if (cid == "") {
				$('#id').focus().css("outline-color", "red");
				return;
				}
				if (cname == "") {
					$("#name").focus().css("outline-color", "red");
					return;
				}
				if (caddr == "") {
					$("#add").focus().css("outline-color", "red");
					return;
				}
				if (cphno == "") {
					$("#num").focus().css("outline-color", "red");
					return;
				}							
				if (cphno.length != 10) {
					$('#num').focus().css('outline-color', 'red');
					alert("Enter Phone Number as correct format");
					return;
				}						
				if (cphno.charAt(0) != "7"&& cphno.charAt(0) != "8"&& cphno.charAt(0) != "9") {
				$('#num').focus().css('outline-color', 'red');
				alert("Enter Phone Number as correct format");
				return;
			    }
			   var url = "/bill/customer?operation=cusAdd&cid="+ cid+ "&cname="+ cname+ "&caddr="+ caddr+ "&cphno="+ cphno;
			       $.ajax({
				        url : url,
				        type : 'POST'
			            }).done(function(result) {
                            alert("Successfully Added");
			           }).fail(function(result) {
				          alert("Please check details");
			     })
        });
      $(document).on("click","#update",function() {						
    	    var cid = $("#id").val();
			var cname = $("#name").val();
			var caddr = $("#add").val();
			var cphno = $("#num").val();
			if (cid == "") {
				$("#id").focus().css("outline-color", "red");
				return;
			}							
			if (cname == "") {
				$("#name").focus().css("outline-color", "red");
				return;
			}									
			if (caddr == "") {
				$("#add").focus().css("outline-color", "red");
				return;
			}
			if (cphno == "") {
				$("#num").focus().css("outline-color", "red");
				return;
			}
			if (cphno.length != 10) {
				$('#num').focus().css("outline-color", "red");
			    alert("Enter Phone Number as correct format");
				return;
			}									
			if (cphno.charAt(0) != "7"&& cphno.charAt(0) != "8"&& cphno.charAt(0) != "9") {
				$('#num').focus().css('outline-color', 'red');
				alert("Enter Phone Number as correct format");
				return;
			}									
			var url = "/bill/customer?operation=cusUpdate&cid="+ cid+ "&cname="+ cname+ "&caddr="+ caddr+ "&cphno="+ cphno;
			$.ajax({
				url : url,
				type : 'POST'
			}).done(function(result) {
				alert("Successfully Updated");
			}).fail(function(result) {
				alert("Error occurs");
			})
		});
		
      $(document).on("keyup","#id",function() {
    	  var cid = $("#id").val();
			if (cid !== "") {
				var url = "/bill/customer?operation=cusOne&id="+ cid;
				$.ajax({
					url : url,
					type : 'POST'
				})
				.done(function(result) {
						    result = JSON.parse(result);
							$("#name").val(result.name);	
							$("#add").val(result.address);
							$("#num").val(result.phonenumber);
				});
             }
        });											
      $(document).on("click","#cusAll",function() {
			var url = "/bill/customer?operation=cusAll";
			$.ajax({
				url : url,
				type : 'POST'
			})
			.done(function(result) {
				var array = JSON.parse(result);
				var table = "<table border=2>";
				table += "<tr><th>CustomerId</th><th>CustomerName</th><th>Address</th><th>PhoneNumber</th></tr>";
				for (i = 0; i < array.length; i++) {
				table += "<tr>";
				table += "<td>"+ array[i].cid+ "</td>";
				table += "<td>"+ array[i].name+ "</td>";
				table += "<td>"+ array[i].address+ "</td>";
				table += "<td>"+ array[i].phonenumber+ "</td>";
				table += "</tr>";
	          }
				table += "</table>";
				$('.customer')[0].innerHTML = table;
			});
       });		

      $(document).on("click","#sub",function() {																				
    	  var cid = $("#cid").val();
			var sid = $("#sid").val();
			if (cid == "") {
				$("#cid").focus().css("outline-color", "#ff0000");
				return;
			}
			if (sid == "") {
				$("#sid").focus().css("outline-color", "#ff0000");
				return;
			}
			var url = "/bill/customer?operation=customerAdd&cId="+ cid + "&saleId=" + sid;
			$.ajax({
				url : url,
				type : 'POST'
			}).done(function(result) {
				alert("Successfully Added");
			}).fail(function(result) {
				alert("Error Occurs");
			});
		});																					
      $(document).on("keyup","#cid",function() {
    	  var cid = $("#cid").val();
			if (cid !== "") {
				var url = "/bill/customer?operation=cusale&cId="+ cid;
				$.ajax({
					url : url,
					type : 'POST'
				})
				.done(
						function(result) {
							var array = JSON.parse(result);
							var table = "<table border='2px  ' class='table'><tr><th>BillDate</th><th>VAT</th><th>Discount</th><th>BillTotal</th><th>SaleLineId</th><th>ProductId</th><th>Quantity</th><th>Cost</th><th>PayId</th><th>PayDate</th><th>PaidAmount</th></tr>"
                            for (i = 0; i < array.length; i++) {
						    table += "<tr>"
						    	table += "<td>"+ array[i].billDate+ "</td>"
								table += "<td>"+ array[i].vat+ "</td>"
								table += "<td>"+ array[i].discount+ "</td>"
								table += "<td>"+ array[i].billTotal+ "</td>"
								table += "<td>"+ array[i].saleLineId+ "</td>"
								table += "<td>"+ array[i].productId+ "</td>"
								table += "<td>"+ array[i].quantity+ "</td>"
								table += "<td>"+ array[i].cost+ "</td>"
								table += "<td>"	+ array[i].payId+ "</td>"
								table += "<td>"	+ array[i].payDate+ "</td>"
								table += "<td>"	+ array[i].paidAmount+ "</td>"
								table += "</tr>";
                              }
                         table += "</table>";
                         $(".cust")[0].innerHTML = table;

                      })
                  }		
                })
                $(document).on('keypress','#num',function(key) {
					if (key.which != 8&& key.which != 0&& (key.which < 48 || key.which > 57)) {
						alert("Numbers Only");
						return;
					}
				})	
				.on('keypress','#name',function(event) {			
					var inputValue = event.which;
					if (!(inputValue >= 65 && inputValue <= 122)
							&& (inputValue != 32&& inputValue != 0 && inputValue != 13)) {
					    alert("Alphabets only");
						event.preventDefault();
					}
				})

});				

				
									
										
										
										
										

										
										
										
												
												
												
										
							
									
									
									
													
											
													
																		
																				
																		
																				
																
						
							
									
									
									
										
																									
												
												


																	
																
																
																							
     																	
																					
							
									
									
									
										
							
									
									
      										


																				
													
																		
																
																																		
																			
																			
																	
																					
																			
																			
																			
																	
																			
																			
																	
																			
																			
																	
																			
																			

																	
																
									
														
										
												
												
							
									
									
									
										