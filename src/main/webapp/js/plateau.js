canvas_height=0;
canvas_width=0;
largeur_plateau=0;
hauteur_plateau=0;
x=0 ;
y=0 ;



$(document).ready(function() {
	plateau();
});

function plateau() {
	var path = "webapi/jeu/plateau";
	$.getJSON(path, function(data) {
		afficherPlateau(data)
	});
}

function afficherPlateau(data) {

	var canvas = document.getElementById("canvasEl");
	canvas.height = window.innerHeight;
	canvas.width = document.body.clientWidth ;
	canvas_height = canvas.height;
	canvas_width = canvas.width;
	largeur_plateau = data.largeur;
	hauteur_plateau = data.hauteur;
	var context = canvas.getContext("2d");
	var height = canvas.height;
	var width = canvas.width;
	var largeur = data.largeur;
	var hauteur = data.hauteur;
	var colors = [ 'aqua', 'blue', 'green', 'lime', 'maroon',
	               'navy', 'olive', 'orange', 'purple', 'red', 'silver', 'teal',
	               'yellow' ];

	var img = new Image();
	img.src = "../img/case.png";
	img.onload = function () {
		for ( var i = 0; i < data.largeur; i++) {
			for ( var j = 0; j < data.hauteur; j++) {
				context.drawImage(img, (width / largeur) * i, (height / hauteur) * j, width / largeur, height / hauteur);
				if (data.plateau[i].item[j].estObstacle === true) {
					context.fillRect((width / largeur) * i, (height / hauteur)
							* j, width / largeur, height / hauteur);
				} else
					if (data.plateau[i].item[j].occupant != undefined) {

						context.fillStyle = colors[data.plateau[i].item[j].occupant.id];
						context.fillRect((width / largeur) * i, (height / hauteur)
								* j, width / largeur, height / hauteur);
						context.fillStyle = "#000000";
					} 
					else if (data.plateau[i].item[j].proprietaire != undefined) {

						context.fillStyle = colors[data.plateau[i].item[j].proprietaire.id];
						context.fillRect((width / largeur) * i, (height / hauteur)
								* j, (width / largeur), height / hauteur);
						context.fillStyle = "#000000";
					}
			}
		}
	}
}


$(document).ready(function() {

	$("#canvasEl").bind("click", function(e) {
		canvasClick(e);
		deplacement();
	});

});

function deplacement() {
	var path = "webapi/jeu/coord/"+Math.floor(x/ (canvas_width/largeur_plateau))+"/"+Math.floor(y/ (canvas_height/hauteur_plateau))+"/move";
	$.getJSON(path, function(data) {
		afficherPlateau(data);
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
