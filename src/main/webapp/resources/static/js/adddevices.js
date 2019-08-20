$(document).ready(function () {
    // Date picker
    $( "#inp_registerDate_device" ).datepicker();


    // Form submit
    $("#form_addDevice").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        saveDevice();
    });
});

function saveDevice() {

    var user = {
        sequence : $("#inp_userName_device").val()
    };

    var deviceData = {
        optical_number  : $("#inp_opticNumber_device").val(),
        reg_date   : $("#inp_registerDate_device").val(),
        app_version    : $("#inp_appVersion_device").val(),
        user_sequence : user
        // user_sequence: $("#inp_userName_device").val()
    };

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url: "/api/deviceadmin/save",
        data : JSON.stringify(deviceData),
        dataType : 'json',
        success : function(result) {
            if(result.status === "Success"){
                $.notify("Successful created devices","success");
            }else{
                $.notify("Failed to created devices", "error");
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });

    // Reset FormData after Posting
    resetData();
}

function resetData() {
    $("#inp_opticNumber_device").val("");
    $("#inp_registerDate_device").val("");
    $("#inp_appVersion_device").val("");
    $("#inp_userName_device").val("");
}