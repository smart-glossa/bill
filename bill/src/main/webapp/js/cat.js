$(document).ready(function() {
    $(document).on("click", "#submit", function() {
        var id = $('#catid').val();
        var name = $('#cname').val();
        if (id == "") {
            alert("please enter CategoryId");
            $("#catid").focus().css(
                "outline-color", "#ff0000");
            return;
        }
        if (name == "") {
            alert("please enter your categoryName");
            $("#cname").focus().css(
                "outline-color", "#ff0000");
            return;
        }
        if (!isNaN(name)) {
            alert("please only characters");
            name.value = "";
            return;
        }
        var url = "/bill/cat?operation=add&catid=" + id + "&cname=" + name;
        $('input[type=text]').val();
        $.ajax({
                url: url,
                type: 'post'
            })
            .done(function(result) {

                alert("Successfully Added");

            })
            .fail(function(result) {
                alert("Error Occurs");
            })
    });
    $(document).on("click", "#update", function() {
        var id = $('#catid').val();
        var name = $('#cname').val();
        if (id == "") {
            alert("Please Enter CategoryId");
            $("#catid").focus().css(
                "outline-color", "#ff0000");
            return;
        }
        if (name == "") {
            alert("Please Enter CategoryName");
            $("#cname").focus().css(
                "outline-color", "#ff0000");
            return;
        }
        var url = "/bill/cat?operation=update&catid=" + id + "&cname=" + name;
        $.ajax({
                url: url,
                type: 'post'
            })
            .done(function(result) {
                result = JSON.parse(result);
                if (result.status == "1") {
                    alert("Update Successfully");
                }
            })
            .fail(function(result) {
                alert(result);
            });



    });
    $(document).on('keyup', '#catid', function() {
        var cid = $('#catid').val();
        if (cid != "") {
            var url = "/bill/cat?operation=getOne&catid=" + cid;
            $.ajax({
                    url: url,
                    type: 'post'
                })
                .done(function(result) {
                    result = JSON.parse(result);
                    $("#cname").val(result.cname);
                });
        }
    });
    $(document).on('click', '#getallcat', function() {
        var url = "/bill/cat?operation=getAll";
        $.ajax({
                url: url,
                type: 'post'
            })  
            .done(function(result) {
                var array = JSON.parse(result);
                var table = "<table border=2>"
                table += "<tr><th>CategoryId</th><th>CategoryName</th></tr>";
                for (i = 0; i < array.length; i++) {
                    table += "<tr>";
                    table += "<td>" + array[i].catid + "</td>";
                    table += "<td>" + array[i].cname + "</td>";
                    table += "</tr>";
                }
                table += "</table>";
                $("#lists")[0].innerHTML = table;
            });
    });
    $(document).on('keyup', '#catid', function() {
        var cid = $('#catid').val();
        if (cid != "") {
            var url = "/bill/Expense?operation=getOne&catid=" + cid;
            $.ajax({
                    url: url,
                    type: 'post'
                })
                .done(function(result) {
                    result = JSON.parse(result);
                    $("#cname").val(result.cname);

                });
        }
    });
    $(document).on("click", "#sub", function() {
        var cid = $('#catid').val();
        var cname = $('#cname').val();
        var date = $('#expdate').val();
        var des = $('#description').val();
        var amount = $('#amount').val();
        if (cid == "") {
            alert("please enter id");
        }
        if (cname == "") {
            alert("plese enter categoryname");
        }
        if (date == "") {
            alert("please enter expensedate");

        }
        if (des == "") {
            alert("plese enter Some description");
        }
        if (amount == "") {
            alert("please enter amount");
        }
        var url = "http://localhost:8080/bill/Expense?operation=add&catid=" + cid + "&cname=" + cname + "&expDate=" + date + "&description=" + des + "&amount=" + amount;
        $('input[type=text]').val();
        $.ajax({
                url: url,
                type: 'post'
            })
            .done(function(result) {

                alert("Successfully Added");

            })
            .fail(function(result) {
                alert("Error Occurs");
            });
    });
    $(document).on("click", "#up", function() {
        var cid = $('#catid').val();
        var date = $('#expdate').val();
        var des = $('#description').val();
        var amount = $('#amount').val();
        if (cid == "") {
            alert("please enter categoryId");
            return;
        }

        if (date == "") {
            alert("please enter expensedate");
            return;
        }
        if (des == "") {
            alert("please enter description");
            return;
        }
        if (amount == "") {
            alert("please enter amount");
        }

        var url = "/bill/Expense?operation=expUpdate&catid=" + cid + "&expDate=" + date + "&description=" + des + "&amount=" + amount;
        $.ajax({
                url: url,
                type: 'post'
            })
            .done(function(result) {
                alert("Updated successfully");
            })
            .fail(function(result) {
                alert("Error occurs");
            });

    });
    $(document).on('keyup', '#expid', function() {
        var eid = $('#expid').val();
        if (eid != "") {
            var url = "/bill/Expense?operation=getOne&expId=" + eid;
            $.ajax({
                    url: url,
                    type: 'post'
                })
                .done(function(result) {
                    result = JSON.parse(result);
                    $("#catid").val(result.catid);
                    $("#cname").val(result.cname);
                    $('#expdate').val(result.expDate);
                    $('#description').val(result.description);
                    $('#amount').val(result.amount);
                });
        }
    });
    $(document).on('click', '#expgetall', function() {
        var url = "/bill/Expense?operation=getAll";
        $.ajax({
                url: url,
                type: 'post'
            })
            .done(function(result) {
                var array = JSON.parse(result);
                var table = "<table border=2>"
                table += "<tr><th>CategoryId</th><th>ExpenseDate</th><th>Description</th><th>Amount</tr>";
                for (i = 0; i < array.length; i++) {
                    table += "<tr>";
                    table += "<td>" + array[i].catid + "</td>";
                    table += "<td>" + array[i].expDate + "</td>";
                    table += "<td>" + array[i].description + "</td>";
                    table += "<td>" + array[i].amount + "</td>";
                    table += "</tr>";
                }
                table += "</table>";
                $("#lists")[0].innerHTML = table;
            });

    });
    $(document).on('keypress', '#expdate', function(press) {
        if (press.which != 8 && press.which != 0 && (press.which < 48 || press.which > 57)) {
            alert("Numbers Only");
            return;
        }
    })
    $(document).on('keypress', '#amount', function(e) {
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                alert("Numbers Only");
                return;
            }
        })
        .on('keypress', '#cname', function(key) {
            var inputValue = key.which;
            if (!(inputValue >= 65 && inputValue != 122) && (inputValue != 32 && inputValue != 0 && inputValue != 13)) {
                alert("Alphabets only");

            }

        })
        .on('keypress', '#description', function(event) {
            var inputValue = event.which;
            if (!(inputValue >= 65 && inputValue != 122) && (inputValue != 32 && inputValue != 0 && inputValue != 13)) {
                alert("Alphabets Only");
                key.preventDefault();

            }
        })

});
