function check() {
    $.ajax({
        url: 'checkStrength',
        data: ({password : $('#password').val()}),
        success: function(data) {
            $('#srenghthValue').html(data);
        }
    });
}