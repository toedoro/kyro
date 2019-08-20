// Call the dataTables jQuery plugin
var username;

$(document).ready(function () {
    userTable = $('#userTable').DataTable({
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }],
        select: {
            style: 'os',
            selector: 'td:first-child'
        },
        order: [[1, 'asc']]
    });

    // Getting the selected item in database
    $('#userTable tbody').on('click', 'tr', function () {
        username = userTable.row(this).data()[2];
        document.cookie = username;
    });


    // userTable.on("click", "th.select-checkbox", function () {
    //     if ($("th.select-checkbox").hasClass("selected")) {
    //         userTable.rows().deselect();
    //         $("th.select-checkbox").removeClass("selected");
    //     } else {
    //         userTable.rows().select();
    //         $("th.select-checkbox").addClass("selected");
    //     }
    // }).on("select deselect", function () {
    //     ("Some selection or deselection going on")
    //     if (userTable.rows({
    //         selected: true
    //     }).count() !== userTable.rows().count()) {
    //         $("th.select-checkbox").removeClass("selected");
    //     } else {
    //         $("th.select-checkbox").addClass("selected");
    //     }
    // });

    // Transfer data
    $("#btn_delete_user_yes").click(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        delete_user(username);
    });
});

// Delete user function
function delete_user(pr_user) {

    var userData = {
        username: pr_user,
        del: 1
    };

    if (!userData) {
        $.notify("Error please choose user to delete", "error");
    } else {

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/useradmin/del",
            data: JSON.stringify(userData),
            dataType: 'json',
            success: function (result) {
                if (result.status === "Success") {
                    $.notify("Successfully deleted user", "success");
                    window.location.replace("/");
                } else {
                    $.notify("Failed to deleted user", "error");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });

    }
}

// Get User
// function get_user_details() {
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: "/api/useradmin/all",
//         success: function (result) {
//             if (result.status === "Success") {
//
//             } else {
//                 $.notify("Failed to Retrieve User Details", "error");
//             }
//         },
//         error: function (e) {
//             console.log("ERROR: ", e);
//         }
//     });
// }