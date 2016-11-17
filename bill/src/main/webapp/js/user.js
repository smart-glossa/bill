$(document).on("click", "#signup", function(key) {
        var name = $('#name').val();
        var uname = $('#uname').val();
        var pass = $('#pass').val();
        if (name == "") {
            alert("Please Enter Name");
            $("#name").focus().css("outline-color", "#ff0000");
            return;
        }
        if (uname == "") {
            alert("Please Enter username ");
            $("#uname").focus().css("outline-color", "#ff0000");
            return;
        }
        if (pass == "") {
            alert("Please Enter password");
            $("#pass").focus().css("outline-color", "ff0000");
            return;
        }
        var url = "http://localhost:8080/bill/bill?operation=addUser&name=" + name + "&uname=" + uname + "&pass=" + pass;
        $.ajax({
            url: url,
            type: 'POST'
        }).done(function(result) {
            if (result == "") {
                alert("Added SuccessFully");
                $('#name').val("");
                $('#uname').val("");
                $('#pass').val("");
            } else {
                result = JSON.parse(result);
                if (result.Message == "Error") {
                    alert("Error occurs");
                }
            }

        }).fail(function(result) {
            console.log(result);
        });
});
        $(document).on("click", "#login", function(key) {
            var user = $('#user').val();
            var passw = $('#passw').val();
           
            if (user == "") {
                alert("Please Enter username ");
                $("#user").focus().css("outline-color", "#ff0000");
            }
            if (passw == "") {
                alert("Please Enter password");
                $("#passw").focus().css("outline-color", "ff0000");
                return;
            }
            var url = "http://localhost:8080/bill/bill?operation=login&user=" + user + "&passw=" + passw;
            $.ajax({
                url: url,
                type: 'POST'
            }).done(function(result) {
            	result = JSON.parse(result);
                if (result.Status == "success") {
                	document.cookie = "uname=" + user; 
                	 window.location.href = 'home.html';
                } else {
                    result = JSON.parse(result);
                    if (result.Message == "Error") {
                        alert("Error occurs");
                    }
                }

            }).fail(function(result) {
                console.log(result);
            });
        
        
    });
    