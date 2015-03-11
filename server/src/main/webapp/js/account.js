var createAccount = function() {
    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: '/webapi/account',
        data: JSON.stringify({ login: $('#login').val(), password: $('#password').val(), email: $('#email').val(), nickname: $('#nickname').val() }),
        dataType: 'text',

        success: function(res) {
            console.log('réussi : ' + res);
        },
        error: function(xhr, stat, exception) {
            console.log('fail : ' + stat + '\n'); 
            console.log(xhr);
            console.log(exception);
            console.log(this);
        }
    });
}
