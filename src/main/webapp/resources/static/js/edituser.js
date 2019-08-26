var username = document.cookie;
var id;

$(document).ready(function () {
//    get_user_details();

    $(function () {
        $('#chkToggle1').change(function () {
            $('#console-event').html('Toggle: ' + $(this).prop('checked'));
            if ($(this).is(":checked")) {
                disableForm();
            } else if ($(this).is(":not(:checked)")) {
                enableForm();
            }
        })
    });

});

function disableForm() {
    $("#storeName").prop('disabled', true);
    $("#userName").prop('disabled', true);
    $("#userId").prop('disabled', true);
    $("#countryCode").prop('disabled', true);
    $("#city").prop('disabled', true);
    $("#address").prop('disabled', true);
    $("#email").prop('disabled', true);
    $("#telNo").prop('disabled', true);
    $("#hpNo").prop('disabled', true);
    $("#permission").prop('disabled', true);
    $("#managerSequence").prop('disabled', true);

    $("#btn_save_user_edit").prop('disabled', true);
}

function enableForm() {
	$("#storeName").prop('disabled', false);
    $("#userName").prop('disabled', false);
    $("#userId").prop('disabled', false);
    $("#countryCode").prop('disabled', false);
    $("#city").prop('disabled', false);
    $("#address").prop('disabled', false);
    $("#email").prop('disabled', false);
    $("#telNo").prop('disabled', false);
    $("#hpNo").prop('disabled', false);
    $("#permission").prop('disabled', false);
    $("#managerSequence").prop('disabled', false);

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

$("#formUserDetails").submit(function (event) {
    event.preventDefault();
    update();
});

function update() {
    var user = {
        storeName  	: $("#storeName").val(),
        userName    : $("#userName").val(),
        userId    	: $("#userId").val(),
        countryCode	: $("#countryCode").val(),
        city        : $("#city").val(),
        address     : $("#address").val(),
        email       : $("#email").val(),
        telNo      	: $("#telNo").val(),
        hpNo       	: $("#hpNo").val(),
        permission  : $("#permission").val(),
        manager  	: { sequence: $("#managerSequence").val() }
    };

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url: "/kyro/api/system/user",
        data : JSON.stringify(user),
        dataType : 'json',
        success : function(result) {
        	$.notify("User sucessfully updated!","success");
        }, error : function(e) {
        	$.notify("Error on updating user!", "error");
        }
    });

}