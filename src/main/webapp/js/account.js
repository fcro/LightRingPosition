var createAccount = function() {
    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: 'webapi/account',
        data: JSON.stringify({ login: $('.signup .login').val(), password: $('.signup .password').val(), email: $('.signup #email').val(), nickname: $('.signup #nickname').val() }),
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

    return false;
}

var connect = function() {
    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: 'webapi/account/auth',
        data: JSON.stringify({ login: $('#navbar .login').val(), password: $('#navbar .password').val() }),

        success: function(res) {
            $("#indexmsg").append("<h2>Braval, tu viens de te connecter. Ça va les chevilles ?</h2>");
        },
        error: function(xhr, stat, exception) {
            $("#indexmsg").append("<h2>Tu sais pas te connecter ? Tu veux des cours d'internet ?</h2>");
        },
    });

    return false;
}

$("#signupbtn").click(createAccount);
$("#signinbtn").click(connect);
