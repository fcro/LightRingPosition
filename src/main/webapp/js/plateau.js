canvas_height=0;
canvas_width=0;
largeur_plateau=0;
hauteur_plateau=0;
x=0 ;
y=0 ;

var colors = [ '#33F0FA', '#FA3333', '#33FA7C', '#E522EF', 'maroon',
               'navy', 'olive', 'orange', 'purple', 'red', 'silver', 'teal',
               'yellow' ];
var preload = ["img/Mage-rouge.gif", "img/Mage-rouge.gif", "img/Mage-rouge.gif","img/Mage-rouge.gif"];
var images = [];
for (i = 0; i < preload.length; i++) {
    images[i] = new Image();
    images[i].src = preload[i];
}

$(document).ready(function() {

	$("#canvasEl").bind("click", function(e) {
		canvasClick(e);
		deplacement();

	});

});

$(document).ready(function() {
	plateau();
});


function plateau() {
	var path = "webapi/jeu/plateau";
	$.getJSON(path, function(data) {
		afficherPlateau(data)
		afficherInformation(data)
		afficherScore(data);
	});
}

function afficherScore(data) {
	var path = "webapi/jeu/count";
	$.getJSON(path,function(donnees) {
		document.getElementById('score').innerHTML ="";
		for(var i = 0; i<donnees.score.length;i++) {
			document.getElementById('score').innerHTML += " Joueur "+ i +" : " 
			+ data.listeJoueurs[i].nom + " possede " + donnees.score[i] + " cases <br>";
		}
	});
}

function afficherInformation(data) {
	document.getElementById('information').innerHTML = "Au tour du <b><FONT COLOR="+colors[data.idx]+" > Joueur "
	+ data.idx + " : " + data.listeJoueurs[data.idx].nom + " </font></b> de jouer ";
	document.getElementById('information').innerHTML +="<br> Il reste "+data.tour+" tour de jeu !";
	
}
	
	

function afficherPlateau(data) {

	var canvas = document.getElementById("canvasEl");
	canvas.height = window.innerHeight - 400;
	canvas.width = document.body.clientWidth - 100 ;
	canvas_height = canvas.height;
	canvas_width = canvas.width;
	largeur_plateau = data.largeur;
	hauteur_plateau = data.hauteur;
	var context = canvas.getContext("2d");
	var height = canvas.height;
	var width = canvas.width;
	var largeur = data.largeur;
	var hauteur = data.hauteur;


	var img = new Image();
	img.src = "img/case.png";
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
						context.drawImage(images[data.plateau[i].item[j].occupant.id], (width / largeur) * i, (height / hauteur) * j, width / largeur, height / hauteur);
						
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
	afficherInformation(data);
	afficherScore(data);

}


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
