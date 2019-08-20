$(document).ready(function () {
    // Form submit
    $("#form_addUser").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        saveUser();
    });

    // confirm password checking
    var password = document.getElementById("inp_password_edit"),
        confirm_password = document.getElementById("inp_compassword");

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;


    // Save user function
    function saveUser() {
        var userData = {
            store_name  : $("#inp_storeName").val(),
            full_name   : $("#inp_userName").val(),
            username    : $("#inp_userID").val(),
            country_code: $("#inp_country").val(),
            password    : $("#inp_password").val(),
            city        : $("#inp_city").val(),
            address     : $("#inp_address").val(),
            email       : $("#inp_email").val(),
            tel_no      : $("#inp_tel_No").val(),
            hp_no       : $("#inp_hp_No").val(),
            permission  : $("#inp_permission").val(),
            manager_id  : $("#inp_ManagerID").val()
        };

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url: "/api/useradmin/save",
            data : JSON.stringify(userData),
            dataType : 'json',
            success : function(result) {
                if(result.status === "Success"){
                    $.notify("Successful created user","success");
                    // $("#crumbs").notify("Successful created user","success");
                }else{
                    $.notify("Failed to created user", "error");
                    // $("#crumbs").notify("Failed to created user", "error");
                }
                console.log("Success");
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();
    }

    function resetData(){
        $("#inp_storeName").val("");
        $("#inp_userName").val("");
        $("#inp_userID").val("");
        $("#inp_country").val("");
        $("#inp_password").val("");
        $("#inp_compassword").val("");
        $("#inp_city").val("");
        $("#inp_address").val("");
        $("#inp_email").val("");
        $("#inp_tel_No").val("");
        $("#inp_hp_No").val("");
        $("#inp_permission").val("");
        $("#inp_ManagerID").val("");
    }
});


