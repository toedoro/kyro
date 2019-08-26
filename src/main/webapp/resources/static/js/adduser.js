$(document).ready(function () {
    $("#formAddUser").submit(function (event) {
        event.preventDefault();
        save();
    });

    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
    
    function validatePassword() {
        if (password.value !== confirmPassword.value) {
        	confirmPassword.setCustomValidity("Passwords Don't Match");
        } else {
        	confirmPassword.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;


    function save() {
	    var user = {
	        storeName  	: $("#storeName").val(),
	        userName    : $("#userName").val(),
	        userId    	: $("#userId").val(),
	        password    : $("#password").val(),
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
            type : "POST",
            contentType : "application/json",
            url: "/kyro/api/system/user",
            data : JSON.stringify(user),
            dataType : 'json',
            success : function(result) {
            	$.notify("Successful created user","success");
            }, error : function(e) {
            	$.notify("Failed to created user", "error");
            }
        });

        reset();
    }

    function reset(){
        $("#storeName").val("");
        $("#userName").val("");
        $("#userId").val("");
        $("#country").val("");
        $("#password").val("");
        $("#confirmPassword").val("");
        $("#city").val("");
        $("#address").val("");
        $("#email").val("");
        $("#telNo").val("");
        $("#hpNo").val("");
        $("#permission").val("");
        $("#managerId").val("");
    }
});


