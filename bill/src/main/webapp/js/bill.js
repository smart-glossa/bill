$(document).ready(function() {
    addNewLine();
    displayProducts();
    $(document).on("click", "#submit", function() {
        var pId = $('#pId').val();
        var pName = $('#pName').val();
        var cost = $('#cost').val();
        if (pId == "") {
            alert("Please Enter ProductId");
            $("#pId").focus().css("outline-color", "#ff0000");
            return;
        }
        if (pName == "") {
            alert("Please Enter Product Name");
            $("#pName").focus().css("outline-color", "#ff0000");
            return;
        }
        if (cost == "") {
            alert("Please Enter cost");
            $("#cost").focus().css("outline-color", "ff0000");
            return;
        }
        var url = "http://localhost:8080/bill/bill?operation=addProduct&pid=" + pId + "&pname=" + pName + "&cost=" + cost;
        $.ajax({
            url: url,
            type: 'POST'
        }).done(function(result) {
            if (result == "") {
                alert("Added SuccessFully");
                $('#pId').val("");
                $('#pName').val("");
                $('#cost').val("");
            } else {
                result = JSON.parse(result);
                if (result.Message == "Error") {
                    alert("Error occurs");
                }
            }
            displayProducts();

        }).fail(function(result) {
            console.log(result);
        });
    });
    $(document).on("click", ".nextLine", function() {
        if ($(this).attr("value") === "next") {
            $(this).attr("value", "X");
            addNewLine();
        } else if ($(this).attr("value") === "X") {
            $(this).parent().remove();
            calculateBillAmount();
            balanceAmount();
        }

    });
   

    $(document).on("keyup", ".pid", function(key) {
       if (key.which == 39) {
            $(".quantity").focus();
        }
       var div = $(this).parent();
       if($(this).val()==""){
    	   div.children(".pname").val("")
           div.children(".quantity").val("");
           div.children(".pcost").val("");
           div.children(".lineTotal").val(0);
           calculateBillAmount();
       }
       
       
        var getProductUrl = "/bill/bill?operation=getProduct&pid=" + $(this).val();
        $.ajax({
                url: getProductUrl,
                type: "POST"
            })
            .done(function(result) {
                result = JSON.parse(result);
                if (jQuery.isEmptyObject(result)) {
                	div.children(".pname").val("")
                    div.children(".quantity").val("");
                    div.children(".pcost").val("");
                    div.children(".lineTotal").val(0);
                    calculateBillAmount();
                    return false;
                }else{
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
                }
               
            })
    });

    $(document).on("keyup", ".quantity", function(key) {
        if (key.which == 37) {
            $(".pid").focus();
        }
        var div = $(this).parent();
        if (key.which == 13) {
            div.children(".nextLine").click();
        }
        if (key.which == 40) {
            div.next().children(".quantity").focus();
        }
        if (key.which == 38) {
            div.prev().children(".quantity").focus();
        }
        var a = div.children(".quantity").val();
        var b = div.children(".pcost").val();
        var c = a * b;
        div.children(".lineTotal").val(c);
        calculateBillAmount();

    })
    $(document).on("keyup", "#pId", function() {
        var pId = $('#pId').val();
        if (pId != "") {
            var getProductUrl = "/bill/bill?operation=getProduct&pid=" + pId;
            $.ajax({
                    url: getProductUrl,
                    type: "POST"
                })
                .done(function(result) {
                    result = JSON.parse(result);
                    var pName = result.name;
                    var cost = result.cost;
                    $("#pName").val(pName);
                    $("#cost").val(cost);
                })
                .fail(function(result) {
                    alert("Some Errors Please Enter correct value");
                });
        } else {
            $("#pName").val("");
            $("#cost").val("");
        }

    });
    $(document).on("keypress", ".add", function(key) {
        if (key.which == 13) {
            $("#submit").click();
        }
    })
    $(document).on("click", "#update", function() {
        var pId = $('#pId').val();
        var pName = $('#pName').val();
        var cost = $('#cost').val();
        if (pId == "") {
            alert("Please Enter ProductId");
            $("#pId").focus().css("outline-color", "#ff0000");
            return;
        }
        if (pName == "") {
            alert("Please Enter Product Name");
            $("#pName").focus().css("outline-color", "#ff0000");
            return;
        }
        if (cost == "") {
            alert("Please Enter cost");
            $("#cost").focus().css("outline-color", "ff0000");
            return;
        }
        var url = "http://localhost:8080/bill/bill?operation=updateProduct&pid=" + pId + "&name=" + pName + "&cost=" + cost;
        $.ajax({
                url: url,
                type: 'POST'
            })
            .done(function(result) {
                if (result == "") {
                    alert("Updated SuccessFully");
                    $('#pId').val("");
                    $('#pName').val("");
                    $('#cost').val("");
                } else {
                    result = JSON.parse(result);
                    if (result.Message == "Error") {
                        alert("Error occurs");
                    }
                }
                displayProducts();
            }).fail(function(result) {
                console.log(result);
            });

    })

    $(document).on("click", ".productRow", function() {
        var tag = $(this);
        var pid = $(this).children(".productId")[0].innerHTML;
        var url = "/bill/bill?operation=deleteProduct&pid=" + pid;
        $.ajax({
            url: url,
            type: 'POST'
        }).done(function(result) {
            tag.remove();
        }).fail(function(result) {
            console.log("")
        });
    })
    $(document).on("keyup", "#cash", function() {
    	if($(this).val().trim()===""){
    		$("#balance").val("");
    	} else {
            balanceAmount();
    	}
    })

    // $(document).on("keypress",".lineProduct",function(key){
    // if(key.which==13){
    // $(".nextLine").click();
    // }
    // })

});