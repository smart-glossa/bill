    $(document).ready(function() {
    $(document).on("click", "#add", function() {
        var purchaseId = $('#pId').val();
        var billDate = $('#Date').val();
        var vat = $('#vat').val();
        var discount = $('#discount').val();
        var billTotal = $('#tot').val();
        var payDate = $('#date').val();
        var paidAmount = $('#amount').val();
        if (purchaseId == "") {
            $('#pId').focus().css("outline-color", "red");
            return false;
        }
        if (billDate === "") {
            $("#Date").focus().css("outline-color", "red");
            return false;
        }
        if (vat == "") {
            $('#vat').focus().css("outline-color", "red");
            return false;
        }
        if (discount == "") {
            $('#discount').focus().css("outline-color", "red");
            return false;
        }
        if (billTotal == "") {
            $('#tot').focus().css("outline-color", "red");
            return false;
        }
        if (payDate == "") {
            $('#date').focus().css("outline-color", "red");
            return false;
        }
        if (paidAmount == "") {
            $('#amount').focus().css("outline-color", "red");
            return false;
        }
        var url = "http://localhost:8080/bill/purchase?operation=addPurchase&purchaseId=" + purchaseId + "&billDate=" + billDate + "&vat=" + vat + "&discount=" + discount + "&billTotal=" + billTotal + "&payDate=" + payDate + "&paidAmount=" + paidAmount;
        $.ajax({
                url: url,
                type: 'POST'
            })
            .done(function(result) {
                alert(result);
            })
            .fail(function(result) {
                alert(result);
            })
    });
    $(document).on('keyup', '#pId', function() {
        var purchaseId = $('#pId').val();
        if (purchaseId !== "") {
            var url = "http://localhost:8080/bill/purchase?operation=getOne&purchaseId=" + purchaseId;
            $.ajax({
                    url: url,
                    type: 'POST'
                })
                .done(function(result) {
                    var res = JSON.parse(result);
                    $('#date').val(res.billDate);
                    $('#vat').val(res.vat);
                    $('#discount').val(res.discount);
                    $('#tot').val(res.billTotal);
                })
                .fail(function(result) {
                    alert(result);
                })
        } else {
            $('#date').val("");
            $('#vat').val("");
            $('#discount').val("");
            $('#tot').val("")
        }
    });
    $(document).on('click', '#update', function() {
        var purchaseId = $('#pId').val();
        var billDate = $('#Date').val();
        var vat = $('#vat').val();
        var discount = $('#discount').val();
        var billTotal = $('#tot').val();
        var url = "/bill/purchase?operation=update&purchaseId=" + purchaseId + "&billDate=" + billDate + "&vat=" + vat + "&discount=" + discount + "&billTotal=" + billTotal;
        $.ajax({
                url: url,
                type: 'POST'
            })
            .done(function(result) {
                alert(result);
            })
            .fail(function(result) {
                alert(result);
            });
    });
    $(document).on("click", "#sub", function() {
        var purchaseId = $('#pId').val();
        var productId = $('#proId').val();
        var quantity = $('#quantity').val();
        var buyPrice = $('#buy').val();
        var sellPrice = $('#sel').val();
        if (purchaseId == "") {
            $('#pId').focus().css("outline-color", "red");
            return false;
        }
        if (productId == "") {
            $('#proId').focus().css("outline-color", "red");
            return false;
        }
        if (quantity == "") {
            $('#quantity').focus().css("outline-color", "red");
            return false;
        }
        if (buyPrice == "") {
            $('#buy').focus().css("outline-color", "red");
            return false;
        }
        if (sellPrice == "") {
            $('#sel').focus().css("outline-color", "red");
            return false;
        }
        var url = "http://localhost:8080/bill/purchaseMeta?operation=addItem&purchaseId=" + purchaseId + "&productId=" + productId + "&quantity=" + quantity + "&buyPrice=" + buyPrice + "&sellPrice=" + sellPrice;
        $.ajax({
                url: url,
                type: 'POST'
            }).done(function(result) {
                alert(result);
            })
            .fail(function(result) {
                alert(result);
            });
    });
    $(document).on('keypress', '#pId', function(key) {
        if (key.which == 13) {
            $('#proId').focus();
        }

    })
    $(document).on('keypress', '#proId', function(key) {
        if (key.which == 13) {
            $('#quantity').focus();
        }
        if (key.which == 38) {
            $('#pId').focus();
        }
    })
    $(document).on('keypress', '#quantity', function(key) {
        if (key.which == 13) {
            $('#buy').focus();
        }
        if (key.which == 38) {
            $('#proId').focus();
        }

    })
    $(document).on('keypress', '#buy', function(key) {
        if (key.which == 13) {
            $('#sel').focus();
        }
        if (key.which == 38) {
            $('#quantity').focus();
        }
    })
    $(document).on('keypress', '#sel', function(key) {
        if (key.which == 13) {
            $("#sub").click();

        } else if (key.which == 38) {
            div.prev().children('#buy').focus();
        }
    })
    $(document).on('keypress', '#pId', '#proId', '#quantity', '#buy', '#sel', function(e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            alert("Numbers Only");
            return;
        }
    })

});
