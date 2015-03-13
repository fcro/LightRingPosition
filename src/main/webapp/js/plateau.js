$(document).ready(function() {
	var path = "webapi/jeu/plateau";
	$.getJSON(path, function(data) {
		afficherPlateau(data)
	});
});
;

canvas_height=0;
canvas_width=0;
taille_plateau=0;
x=0 ;
y=0 ;

function afficherPlateau(data) {

	var canvas = document.getElementById("canvasEl");
	canvas.height = document.body.clientWidth / 2 - 200;
	canvas.width = document.body.clientWidth / 2 - 200;
	canvas_height = canvas.height;
	canvas_width = canvas.width;
	taille_plateau = data.taille;
	var context = canvas.getContext("2d");
	var height = canvas.height;
	var width = canvas.width;
	var taille = data.taille;
	var colors = [ 'aqua', 'blue', 'gray', 'green', 'lime', 'maroon',
	               'navy', 'olive', 'orange', 'purple', 'red', 'silver', 'teal',
	               'yellow' ];

	for ( var i = 0; i < data.taille; i++) {
		for ( var j = 0; j < data.taille; j++) {
			context.strokeRect((width / taille) * i, (height / taille) * j,
					width / taille, height / taille);
			if (data.plateau[i].item[j].estObstacle === true) {
				context.fillRect((width / taille) * i, (height / taille)
						* j, width / taille, height / taille);
			} else if (data.plateau[i].item[j].occupant != undefined) {

				context.fillStyle = colors[data.plateau[i].item[j].occupant.id];
				context.fillRect((width / taille) * i, (height / taille)
						* j, width / taille, height / taille);
				context.fillStyle = "#000000";
			}
		}
	}
}

$(document).ready(function() {

	$("#canvasEl").bind("click", function(e) {
		canvasClick(e);
		envoiCoord();
	});

});

function envoiCoord() {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "webapi/jeu/coord/"+Math.floor(x/ (canvas_height/taille_plateau))+"/"+Math.floor(y/ (canvas_width/taille_plateau)),
		dataType : "json",
		data : JSON.stringify({
		}),
		success : function(data, textStatus, jqXHR) {
			console.log("ca marche");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error');
		}
	});
}

function canvasClick(e) {
	var targ;
	if (!e)
		e = window.event;
	if (e.target)
		targ = e.target;
	else if (e.srcElement)
		targ = e.srcElement;
	if (targ.nodeType == 3) // defeat Safari bug
		targ = targ.parentNode;
	x = e.pageX - $(targ).offset().left;
	y = e.pageY - $(targ).offset().top;

}