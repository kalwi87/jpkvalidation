jQuery(document).ready(function () {

    jQuery("#btnSubmit").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

});



function fire_ajax_submit() {

    // Get form
    var form = jQuery('#fileUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    jQuery("#btnSubmit").prop("disabled", true);

    jQuery.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        beforeSend:function showMessages() {
            jQuery('#message').append('<div>Pobieranie JPK</div>');
            jQuery('#message').append('<div>Sprawdzanie poprawności pól</div>');
            jQuery('#message').append('<div>Sprawdzanie poprawności pól VAT</div>');

        },
        success: function (data) {
            jQuery('#message').hide();
            jQuery("#result").text(data);
            console.log("SUCCESS : ", data);
            jQuery("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            jQuery("#result").text(e.responseText);
            console.log("ERROR : ", e);
            jQuery("#btnSubmit").prop("disabled", false);

        }
    });
}
