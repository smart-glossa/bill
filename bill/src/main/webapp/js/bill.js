$(document).ready(function() {
    $(document).on("click", "#logout", function() {
        postToServer("logout");
        document.cookie = 'uname=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
        window.location.href = '/bill/';
    });

    $(document).on('click', '#menuLogo', function() {
        $('#profileupload').click();
    });

    $(document).on('change', '#profileupload', function(e) {
        updateProfile();
    });
    $(document).on("click", "#submit", function(key) {
        var productId = $('#productId').val();
        var pName = $('#pName').val();
        var sellPrice = $('#sellPrice').val();
        var quantity = $('#quantity').val();

        if (productId == "") {
            alert("Please Enter ProductId");
            $("#productId").focus().css("outline-color", "#ff0000");
            return;
        }
        if (pName == "") {
            alert("Please Enter Product Name");
            $("#pName").focus().css("outline-color", "#ff0000");
            return;
        }
        if (sellPrice == "") {
            alert("Please Enter SellPrice");
            $("#sellPrice").focus().css("outline-color", "ff0000");
            return;
        }
        if(quantity == ""){
        	alert("Please Enter Quantity");
        	$("#quantity").focus().css("outline-color","ff0000");
        	return;
        }
        var url = "/bill/bill?operation=addProduct&productId=" + productId + "&pName=" + pName + "&sellPrice=" + sellPrice + "&quantity=" + quantity;
        var request = new FormData();
        request.append('file', $('#profile')[0].files[0]);
        $.ajax({
            url: url,
            type: 'POST',
            data: request,
            processData: false,
            contentType: false
        }).done(function(result) {
            result = JSON.parse(result);
            if (result.status == 1) {
                alert("Added SuccessFully");
                $('#productId').val("");
                $('#pName').val("");
                $('#sellPrice').val("");
                $('#quantity').val("");
                postToServer("product");
                $(".displayAll").remove();
                $(".mainArea")[0].appendChild(displayProducts());
            } else {
                alert("Error caused: " + result.message);
            }

        }).fail(function(result) {
            console.log(result);
        });
    });
    $(document).on("click", ".nextLine", function() {
        var deleteURL = "images/deleteButton.jpg";
        if ($(this).attr("alt") === "next") {
            if ($($(this).parent().children(".pid")[0]).val().trim() === "") {
                return;
            }
            var duplicate = checkAndRemoveDuplicate($(this).parent());
            if (duplicate) {
                $(this).parent().remove();
                addNewLine();
            } else {
                $(this).attr("src", deleteURL);
                $(this).attr("alt", "delete")
                addNewLine();
            }

        } else if ($(this).attr("alt") === "delete") {
            $(this).parent().remove();
            calculateBillAmount();
            balanceAmount();
        }

    });


    $(document).on("keyup", ".pid", function(key) {
        var div = $(this).parent();

        if ($(this).val() == "") {
            div.children(".pname").val("")
            div.children(".quantity").val("");
            div.children(".pcost").val("");
            div.children(".lineTotal").val(0);
            calculateBillAmount();
            balanceAmount();
            return;
        }
        if (key.which == 39) {
            div.children(".quantity").focus();
            return;
        }
        if (key.which == 13) {
            var name = div.children(".pname").val();
            var cost = div.children(".pcost").val();
            if (name == "" || cost == "") {
                return;
            } else {
                div.children(".nextLine").click();
                return;
            }

        }
        if (key.which == 40) {
            div.next().children(".pid").focus();
            return;
        }
        if (key.which == 38) {
            div.prev().children(".pid").focus();
            return;
        }
        var getProductUrl = "/bill/bill?operation=getProduct&pid=" + $(this).val();
        $.ajax({
                url: getProductUrl,
                type: "POST"
            })
            .done(function(result) {
                result = JSON.parse(result);
                if (result.status == 0) {
                    div.children(".pname").val("")
                    div.children(".quantity").val("");
                    div.children(".pcost").val("");
                    div.children(".lineTotal").val(0);
                    calculateBillAmount();
                    balanceAmount();
                    return false;
                } else {
                    var product = result.product;
                    var pname = product.name;
                    var pcost = product.cost;
                    div.children(".pname").val(pname)
                    div.children(".quantity").val(1);
                    div.children(".pcost").val(pcost)
                    var a = div.children(".quantity").val();
                    var b = div.children(".pcost").val();
                    var c = a * b;
                    div.children(".lineTotal").val(c);
                    calculateBillAmount();
                    balanceAmount();
                }

            })
            .fail(function(result) {
                console.log(result);
            })
    });

    $(document).on("keyup", ".quantity", function(key) {
        var div = $(this).parent();
        if (key.which == 37) {
            div.children(".pid").focus();
            return;
        }
        if (key.which == 13) {
            var name = div.children(".pname").val();
            var cost = div.children(".pcost").val();
            if (name == "" || cost == "") {
                // Dont need to do anything
            } else {
                div.children(".nextLine").click();
            }
            return;
        }
        if (key.which == 40) {
            div.next().children(".quantity").focus();
            return;
        }
        if (key.which == 38) {
            div.prev().children(".quantity").focus();
            return;
        }
        var a = div.children(".quantity").val();
        var b = div.children(".pcost").val();
        var c = a * b;
        div.children(".lineTotal").val(c);
        calculateBillAmount();
        balanceAmount();

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
                    if (result.status == 1) {
                        var product = result.product;
                        var pName = product.name;
                        var cost = product.cost;
                        $("#pName").val(pName);
                        $("#cost").val(cost);
                    } else {
                        alert("Error caused: " + result.message);
                    }

                })
                .fail(function(result) {
                    console.log(result);
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
        var url = "/bill/bill?operation=updateProduct&pid=" + pId + "&name=" + pName + "&cost=" + cost;
        var request = new FormData();
        request.append('file', $('#profile')[0].files[0]);
        $.ajax({
                url: url,
                type: 'POST',
                data: request,
                processData: false,
                contentType: false
            })
            .done(function(result) {
                result = JSON.parse(result);
                if (result.status == 1) {
                    alert("Updated SuccessFully");
                    $('#pId').val("");
                    $('#pName').val("");
                    $('#cost').val("");
                    postToServer("product");
                    $(".displayAll").remove();
                    $(".mainArea")[0].appendChild(displayProducts());
                } else {
                    alert("Error caused: " + result.message);
                }

            }).fail(function(result) {
                console.log(result);
            });


    })

    $(document).on("click", ".deleteProduct", function() {
        var tag = $(this).parent().parent();
        var pid = tag.children(".productId")[0].innerHTML;
        var url = "/bill/bill?operation=deleteProduct&pid=" + pid;
        $.ajax({
            url: url,
            type: 'POST'
        }).done(function(result) {
            result = JSON.parse(result);
            if (result.status == 1) {
                tag.remove();
                postToServer("product");
            } else {
                alert("Error caused : " + result.message)
            }

        }).fail(function(result) {
            console.log(result)
        });
    })
    $(document).on("keyup", "#cash", function() {
        if ($(this).val().trim() === "") {
            $("#balance").val("");
        } else {
            balanceAmount();
        }
    })
    $(document).on("keyup", "#pId", function(key) {
        var td = $(this).parent();
        var tr = td.parent();
        if (key.which == 40) {
            tr.next().children().children("#pName").focus();
        }
    })
    $(document).on("keyup", "#pName", function(key) {
        var td = $(this).parent();
        var tr = td.parent();
        if (key.which == 40) {
            tr.next().children().children("#cost").focus();
        }
        if (key.which == 38) {
            tr.prev().children().children("#pId").focus();
        }
    })
    $(document).on("keyup", "#cost", function(key) {
        var td = $(this).parent();
        var tr = td.parent();
        if (key.which == 38) {
            tr.prev().children().children("#pName").focus();
        }
        if (key.which == 40) {
            tr.next().children().children("#submit").focus();
        }
    })
    $(document).on("click", "#print", function() {
        previewBillReceipt();
    });

    $(document).on("click", "#billMenu", function() {
        var productMenu = $("#productMenu");
        productMenu.removeClass();
        $(this).addClass("active");
        $(".mainArea").empty();
        $(".mainArea")[0].appendChild(billing());
        addNewLine();
    });

    $(document).on("click", "#productMenu", function() {
        var productMenu = $("#billMenu");
        productMenu.removeClass();
        $(this).addClass("active");
        $(".mainArea").empty();
        $(".mainArea")[0].appendChild(product());
        $(".mainArea")[0].appendChild(displayProducts());
    });

});
