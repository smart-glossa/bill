$(document).ready(function() {
    $(document).on('click', '#submit', function() {
        var dealerid = $("#dealerid").val();
        var name = $("#name").val();
        var address = $("#address").val();
        var phone = $("#phone").val();
        var tin = $("#tin").val();
        if (dealerid == "") {
            $('#dealerid').css("border-color", "red");
            return;
        }
        if (name == "") {
            $('#name').css("border-color", "red");
            return;
        }
        if (address == "") {
            $('#address').css("border-color", "red");
            return;
        }
        if (phone == "") {
            $('#phone').css("border-color", "red");
            return;
        }
        if (phone.length != 10) {
            $('#phone').after("<p style='color:red';>Only 10 Numbers</p>");
            return;
        }
        if (tin == "") {
            $('#tin').css("border-color", "red");
            return;
        }
        if (phone.length != 10) {
            $('#num').focus().css('outline-color', 'red');
            alert("Enter Phone Number as correct format");
            return;
        }
        if (phone.charAt(0) != "7" && phone.charAt(0) != "8" && phone.charAt(0) != "9") {
            $('#num').focus().css('outline-color', 'red');
            alert("Enter Phone Number as correct format");
            return;
        }
        var url = "http://localhost:8080/bill/dealer?operation=deaadd&dealerId=" + dealerid + "&name=" + name + "&address=" + address + "&phoneNumber=" + phone + "&TINNumber=" + tin;
        $("input[type=text]").val("");
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
  

        $(document).on('click', '#update', function() {
            var dealerid = $("#dealerid").val();
            var name = $("#name").val();
            var address = $("#address").val();
            var phone = $("#phone").val();
            var tin = $("#tin").val();
            if (dealerid == "") {
                $('#dealerid').css("border-color", "red");
                return;
            }
            if (name == "") {
                $('#name').css("border-color", "red");
                return;
            }
            if (address == "") {
                $('#address').css("border-color", "red");
                return;
            }
            if (phone == "") {
                $('#phone').css("border-color", "red");
                return;
            }
            if (phone.length != 10) {
                $('#phone').after("<p style='color:red';>Only 10 Numbers</p>");
                return;
            }
            if (tin == "") {
                $('#tin').css("border-color", "red");
                return;
            }
            var url = "http://localhost:8080/bill/dealer?operation=update&dealerId=" + dealerid + "&name=" + name + "&address=" + address + "&phoneNumber=" + phone + "&TINNumber=" + tin;
            $("input[type=text]").val("");
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

        $(document).on('keyup', '#dealerid', function() {
            var did = $("#dealerid").val();
            if (did != "") {
                var url = "http://localhost:8080/bill/dealer?operation=getone&dealerId=" + did;
                $.ajax({
                        url: url,
                        type: 'POST'
                    })
                    .done(function(result) {
                        result = JSON.parse(result);
                        $("#name").val(result.name);
                        $("#address").val(result.address);
                        $("#phone").val(result.phoneNumber);
                        $("#tin").val(result.TINNumber);
                    });
            }
        });


        $(document).on('click', '#add', function() {
            var did = $('#did').val();
            var pid = $('#pid').val();
            if (did == "") {
                $('#did').css("border-color", "red");
                return;
            }
            if (pid == "") {
                $('#pid').css("border-color", "red");
                return;
            }
            var url = "http://localhost:8080/bill/dealer?operation=billAdd&dealerId=" + did + "&purchaseId=" + pid;
            $("input[type=text]").val("");
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

        $(document).on('keyup', '#did', function() {
            var did = $('#did').val();
            if (did != "") {
                var url = "http://localhost:8080/bill/dealer?operation=billGet&dealerId=" + did;
                $.ajax({
                        url: url,
                        type: 'POST'
                    })
                    .done(function(result) {
                        var array = JSON.parse(result);
                        var qua = "<table border='2px'>";
                        qua += "<tr><th>productId</th><th>BillDate</th><th>Quantity</th><th>Vat</th><th>Discount</th><th>BillTotal</th><th>payId</th><th>payDate</th><th>payAmount</th></tr>";
                        for (var i = 0; i < array.length; i++) {
                            qua += "<tr>";
                            qua += "<td>" + array[i].productId + "</td>";
                            qua += "<td>" + array[i].billDate + "</td>";
                            qua += "<td>" + array[i].quantity + "</td>";
                            qua += "<td>" + array[i].vat + "</td>";
                            qua += "<td>" + array[i].discount + "</td>";
                            qua += "<td>" + array[i].billTotal + "</td>";
                            qua += "<td>" + array[i].payId + "</td>";
                            qua += "<td>" + array[i].payDate + "</td>";
                            qua += "<td>" + array[i].paidAmount + "</td>";
                            qua += "</tr>";

                        }
                        qua += "</table>";
                        $('#getalldiv')[0].innerHTML = qua;
                    });
            }
        });
        
        $(document).on('click', '#all', function() {
            var url = "http://localhost:8080/bill/dealer?operation=getall";
            $.ajax({
                    url: url,
                    type: 'POST'
                })
                .done(function(result) {
                    var result = JSON.parse(result);
                    var table = "<table border=2px>";
                    table += "<tr><th>dealerId</th><th>Name</th><th>Address</th><th>PhoneNumber</th><th>TINNumber</th></tr>";

                    for (var i = 0; i < result.length; i++) {

                        table += "<tr>";
                        table += "<td>" + result[i].dealerId + "</td>";
                        table += "<td>" + result[i].name + "</td>";
                        table += "<td>" + result[i].address + "</td>";
                        table += "<td>" + result[i].phoneNumber + "</td>";
                        table += "<td>" + result[i].TINNumber + "</td>";
                        table += "</tr>";
                    }

                    table += "</table>";
                    $("#lists")[0].innerHTML = table;
                });


    });
});
