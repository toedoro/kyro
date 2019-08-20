var sequence;

// Call the dataTables jQuery plugin
$(document).ready(function() {
    devicesTable = $('#deviceTable').DataTable({
        columnDefs: [ {
            orderable: false,
            className: 'select-checkbox',
            targets:   0
        } ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        order: [[ 1, 'asc' ]]
    } );


    // Getting the selected item in database
    $('#deviceTable tbody').on('click', 'tr', function () {
        sequence = devicesTable.row(this).data()[1];
        document.cookie = sequence;
    });


    // Transfer data
    $("#btn_delete_device_yes").click(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        delete_device(sequence);
    });
});

// Delete user function
function delete_device(sequence) {

    var userData = {
        sequence: sequence
    };

    if (!userData) {
        $.notify("Error please choose device to delete", "error");
    } else {

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/deviceadmin/del",
            data: JSON.stringify(userData),
            dataType: 'json',
            success: function (result) {
                if (result.status === "Success") {
                    $.notify("Successfully deleted device", "success");
                    window.location.replace("devices");
                } else {
                    $.notify("Failed to deleted device", "error");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });

    }
}