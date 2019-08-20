var sequence = document.cookie;

$(document).ready(function () {
    get_device_details();

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
});

function disable_form() {
    $("#inp_opticNumber_device").prop('disabled', true);
    $("#inp_registerDate_device").prop('disabled', true);
    $("#inp_appVersion_device").prop('disabled', true);
    $("#inp_userName_device").prop('disabled', true);

    $("#btn_save_device").prop('disabled', true);
}

function enable_from() {
    $("#inp_opticNumber_device").prop('disabled', false);
    $("#inp_registerDate_device").prop('disabled', false);
    $("#inp_appVersion_device").prop('disabled', false);
    $("#inp_userName_device").prop('disabled', false);

    $("#btn_save_device").prop('disabled', false);
}

// Form submit
$("#form_editDevice").submit(function (event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    updateDevices();
});


function get_device_details() {
    if (!sequence) {
        $.notify("Error please choose item to view", "error");
    } else {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/deviceadmin/all",
            success: function (result) {
                if (result.status === "Success") {
                    // Convert JSON to JSON Array
                    var arr = JSON.parse(result.data);

                    // Populate input field for edit
                    var i;
                    for (i = 0; i < arr.length; i++) {
                        if (arr[i]['sequence'] === sequence) {
                            $("#inp_opticNumber_device").val(arr[i]['optical_number']);
                            $("#inp_registerDate_device").val(arr[i]['reg_date']);
                            $("#inp_appVersion_device").val(arr[i]['app_version']);
                            $("#inp_userName_device").val(arr[i]['user_sequence']);
                        }
                    }

                } else {
                    $.notify("Failed to Retrieve Devices Details", "error");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }
}

function updateDevices() {

    var user = {
        sequence: $("#inp_userName_device").val()
    };


    var devicesData = {
        sequence: sequence,
        optical_number: $("#inp_opticNumber_device").val(),
        reg_date: $("#inp_registerDate_device").val(),
        app_version: $("#inp_appVersion_device").val(),
        user_sequence: user
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/deviceadmin/update",
        data: JSON.stringify(devicesData),
        dataType: 'json',
        success: function (result) {
            if (result.status === "Success") {
                $.notify("Successful update devices", "success");
                disable_form();
            } else {
                $.notify("Failed to update devices", "error");
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}
