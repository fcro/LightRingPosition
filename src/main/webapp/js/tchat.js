$(document).ready(function() {
    $("#submitmsg").click(submit);
});

var msg = $("#usermsg");
var submit = function() {
    var oldscrollHeight = $("#chatbox").attr("scrollHeight") - 20; // redimensionner avant requete
    $.ajax({
        method: "post",
        dataType: "text/html",
        data: msg,
        url: "webapi/tchat/send",
        cache: false,

        success: function() {
            $("#chatbox").html(msg);
            //Auto-scroll			
            var newscrollHeight = $("#chatbox").attr("scrollHeight") - 20; // redimensionner avant requete
            if (newscrollHeight > oldscrollHeight) {
                $("#chatbox").animate({ scrollTop: newscrollHeight }, 'normal');
            }
        }
    });
};

function loadLog () {
    $.ajax({
        method: "get",
        url: "webapi/tchat",
        success: function(content){		
            $("#chatbox").html(content);
        }
    });
};
setInterval(loadLog, 2500);	// et on rafraichit toutes les 2500 ms }
