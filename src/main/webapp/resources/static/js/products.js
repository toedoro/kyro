$(document).ready(function () {
    // Date picker
    $( "#inp_registerDate_device" ).datepicker();


    $("#formProducts").submit(function (event) {
        event.preventDefault();
        save();
    });
    
    $(".custom-file-input").on("change", function(){
    	var fileName = $(this).val().split("\\").pop();
    	$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
});

function save() {
	let form = new FormData();
	let oily = $('#oily')[0].files[0];
	let good = $('#good')[0].files[0];
	let dry = $('#dry')[0].files[0];
	let complexion = $('#complexion')[0].files[0];
	let wrinkles = $('#wrinkles')[0].files[0];
	let impurities = $('#impurities')[0].files[0];
	let keratin = $('#keratin')[0].files[0];
	let moisture = $('#moisture')[0].files[0];
	let pores = $('#pores')[0].files[0];
	let spots = $('#spots')[0].files[0];
	
	form.append("oily", oily);
	form.append("good", good);
	form.append("dry", dry);
	form.append("complexion", complexion);
	form.append("wrinkles", wrinkles);
	form.append("impurities", impurities);
	form.append("keratin", keratin);
	form.append("moisture", moisture);
	form.append("pores", pores);
	form.append("spots", spots);
	
    $.ajax({
        url: "/kyro/api/system/recommended-product/files",
        data : form,
        cache: false,
        contentType: false,
        processData: false,
        method: 'POST',
        type: 'POST',
        success : function(result) {
            $.notify("Successfully upload files!","success");
        }, error : function(e) {
        	$.notify("Failed to upload files!", "error");
        }, complete: function() {
            reset();
        }
    });
    
}

function reset() {
	document.getElementById("formProducts").reset();
    $("#oily").val("");
    $("#good").val("");
    $("#dry").val("");
    $("#complexion").val("");
    $("#wrinkles").val("");
    $("#impurities").val("");
    $("#keratin").val("");
    $("#moisture").val("");
    $("#pores").val("");
	$("#spots").val("");
}