var username = document.cookie;
var id;

$(document).ready(function () {
    get_user_details();

    $(function () {
        $('#chkToggle1').change(function () {
            $('#console-event').html('Toggle: ' + $(this).prop('checked'));
            if ($(this).is(":checked")) {
                disable_form();
            } else if ($(this).is(":not(:checked)")) {
                enable_from();
            }
        })
    });

    // confirm password checking
    var password = document.getElementById("inp_password_edit"),
        confirm_password = document.getElementById("inp_compassword_edit");

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;



});

function disable_form() {
    $("#inp_storeName_edit").prop('disabled', true);
    $("#inp_userName_edit").prop('disabled', true);
    $("#inp_userID_edit").prop('disabled', true);
    $("#inp_country_edit").prop('disabled', true);
    $("#inp_password_edit").prop('disabled', true);
    $("#inp_compassword_edit").prop('disabled', true);
    $("#inp_city_edit").prop('disabled', true);
    $("#inp_address_edit").prop('disabled', true);
    $("#inp_email_edit").prop('disabled', true);
    $("#inp_tel_No_edit").prop('disabled', true);
    $("#inp_hp_No_edit").prop('disabled', true);
    $("#inp_permission_edit").prop('disabled', true);
    $("#inp_ManagerID_edit").prop('disabled', true);

    $("#btn_save_user_edit").prop('disabled', true);
}

function enable_from() {
    $("#inp_storeName_edit").prop('disabled', false);
    $("#inp_userName_edit").prop('disabled', false);
    $("#inp_userID_edit").prop('disabled', false);
    $("#inp_country_edit").prop('disabled', false);
    $("#inp_password_edit").prop('disabled', false);
    $("#inp_compassword_edit").prop('disabled', false);
    $("#inp_city_edit").prop('disabled', false);
    $("#inp_address_edit").prop('disabled', false);
    $("#inp_email_edit").prop('disabled', false);
    $("#inp_tel_No_edit").prop('disabled', false);
    $("#inp_hp_No_edit").prop('disabled', false);
    $("#inp_permission_edit").prop('disabled', false);
    $("#inp_ManagerID_edit").prop('disabled', false);

    $("#btn_save_user_edit").prop('disabled', false);
}

function get_user_details() {
    if (!username) {
        $.notify("Error please choose item to view", "error");
    } else {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/useradmin/all",
            success: function (result) {
                if (result.status === "Success") {
                    // Convert JSON to JSON Array
                    var arr = JSON.parse(result.data);

                    console.log(arr);

                    // Populate input field for edit
                    var i;
                    for (i = 0; i < arr.length; i++) {
                        if ( arr[i]['username'] === username) {
                            id = arr[i]['sequence'];
                            $("#inp_storeName_edit").val(arr[i]['store_name']);
                            $("#inp_userName_edit").val(arr[i]['full_name']);
                            $("#inp_userID_edit").val(arr[i]['username']);
                            $("#inp_country_edit").val(arr[i]['country_code']);
                            $("#inp_password_edit").val(arr[i]['password']);
                            $("#inp_compassword_edit").val(arr[i]['password']);
                            $("#inp_city_edit").val(arr[i]['city']);
                            $("#inp_address_edit").val(arr[i]['address']);
                            $("#inp_email_edit").val(arr[i]['email']);
                            $("#inp_tel_No_edit").val(arr[i]['tel_no']);
                            $("#inp_hp_No_edit").val(arr[i]['hp_no']);
                            $("#inp_permission_edit").val(arr[i]['permission']);
                            $("#inp_ManagerID_edit").val(arr[i]['manager_id'])
                        }
                    }

                } else {
                    $.notify("Failed to Retrieve User Details", "error");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });

    }
}

// Form submit
$("#form_editUser").submit(function (event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    updateUser();
});

function updateUser() {

    var userData = {
        sequence    : id,
        store_name  : $("#inp_storeName_edit").val(),
        full_name   : $("#inp_userName_edit").val(),
        username    : $("#inp_userID_edit").val(),
        country_code: $("#inp_country_edit").val(),
        password    : $("#inp_password_edit").val(),
        city        : $("#inp_city_edit").val(),
        address     : $("#inp_address_edit").val(),
        email       : $("#inp_email_edit").val(),
        tel_no      : $("#inp_tel_No_edit").val(),
        hp_no       : $("#inp_hp_No_edit").val(),
        permission  : $("#inp_permission_edit").val(),
        manager_id  : $("#inp_ManagerID_edit").val()
    };

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url: "/api/useradmin/update",
        data : JSON.stringify(userData),
        dataType : 'json',
        success : function(result) {
            if(result.status === "Success"){
                $.notify("Successful update user","success");
                disable_form();
            }else{
                $.notify("Failed to update user", "error");
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}