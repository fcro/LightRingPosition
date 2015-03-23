$(document).ready(function() {
    $("#signupbtn").click(createAccount);
    $("#signinbtn").click(connect);
});

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
    localStorage.setItem('login', btoa($('#navbar .login').val()));
    localStorage.setItem('pwd', btoa($('#navbar .password').val()));
    $.ajax({
        method: 'GET',
        contentType: 'text/html',
        url: 'webapi/index',
        beforeSend : function(req) {
            req.setRequestHeader('Authorization', localStorage.getItem('legin') + ':' + localStorage.getItem('pwd'));
        },

        success: function(res) {
            $("#indexmsg").append("<h2>Braval, tu viens de te connecter. Ça va les chevilles ?</h2>");
        },
        error: function(xhr, stat, exception) {
            $("#indexmsg").append("<h2>Tu sais pas te connecter ? Tu veux des cours d'internet ?</h2>");
        }
    });

    return false;
}
