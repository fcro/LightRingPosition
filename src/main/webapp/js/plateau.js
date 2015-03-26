/* Variable Globale */

canvas_height=0;
canvas_width=0;
largeur_plateau=0;
hauteur_plateau=0;
x=0 ;
y=0 ;
liste="";


var colors = [ '#33F0FA', '#FA3333', '#33FA7C', '#E522EF', 'maroon',
               'navy', 'olive', 'orange', 'purple', 'red', 'silver', 'teal',
               'yellow' ];

/*Chargement des images nécessaires "  */

var preload = ["img/Mage-rouge.gif", "img/Mage-rouge.gif", "img/Mage-rouge.gif",
               "img/Mage-rouge.gif","img/Mage-rouge.gif", "img/Mage-rouge.gif", 
               "img/Mage-rouge.gif","img/Mage-rouge.gif","img/Mage-rouge.gif",
               "img/Mage-rouge.gif", "img/Mage-rouge.gif","img/Mage-rouge.gif"];
var images = [];
var JSONPlateau;

for (i = 0; i < preload.length; i++) {
	images[i] = new Image();
	images[i].src = preload[i];
}

/* Méthode permettant de calculer la taille d'un objet" */

Object.size = function(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};


/* Listage des parties en cours */

function listerPartie() {
	$(".signup").hide();
	$("#indexmsg").hide();
	$("#listePartie").show();

	var path = "webapi/loby/liste";
	$.getJSON(path, function(data) {
		var size = Object.size(data);
		for(var i = 0; i < size; i++) {

			document.getElementById("listePartie").innerHTML+="<li><a class=\"btn btn-default\" href=\"#\" role=\"button\" " +
			"onclick=\"rejoindrePartie("+i+")\" >Rejoindre Partie "+ i +"</a></li>";
		}
		document.getElementById("listePartie").innerHTML+="<br><a class=\"btn btn-default\" href=\"#\" role=\"button\" " +
		"onclick=\"creerPartie()\" >Creer Partie</a>";
	});

}

/*Rejoindre une partie créée */

function rejoindrePartie(data) {
	$.ajax({
		method: 'GET',
		contentType: 'text/html',
		url: 'webapi/loby/'+data+'/rejoindre',
		beforeSend : function(req) {
			req.setRequestHeader('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')));
			console.log('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')))
		},

		success: function(loby) {
			var size = Object.size(loby);
			document.getElementById("listePartie").innerHTML="";
			for(var i = 0; i < size; i++) {
				document.getElementById("listePartie").innerHTML += "<li>"+loby[i].nom+"</li>";	
			} 
			console.log(data +" rejoindre partie");
			document.getElementById("listePartie").innerHTML+="<br><a class=\"btn btn-default\" href=\"#\" role=\"button\" " +
			"onclick=\"buttonLancerPartie("+data+")\" >Lancer Partie</a>";
		}
	});
}

/*Création d'une partie*/

function creerPartie() {
	$.ajax({
		method: 'GET',
		contentType: 'text/html',
		url: 'webapi/loby/creer',
		beforeSend : function(req) {
			req.setRequestHeader('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')));
			console.log('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')))
		},

		success: function(data) {
			document.getElementById("listePartie").innerHTML = "";
			for(var i = 0; i<data.length;i++) {
				document.getElementById("listePartie").innerHTML += "<li>"+data[i].nom+"</li>";	
			}   
			document.getElementById("listePartie").innerHTML+="<br><a class=\"btn btn-default\" href=\"#\" role=\"button\" " +
			"onclick=\"quitterPartie()\" >Quitter Partie</a>";
		},
	});
}

/* Lance la partie et la supprime des parties disponibles*/

function buttonLancerPartie(data) {
	lancerPartie()
	plateau(data)
	supprimerPartie(data)
}



function supprimerPartie(data) {
	var path = "webapi/loby/"+data+"/delete";
	$.getJSON(path, function(data) {
		console.log("success");
	});
}

function lancerPartie() {
	$(".signup").hide();
	$("#indexmsg").hide();
	$("#listePartie").hide();
	$("#partie").show();

	document.getElementById("homecontent").style.width = "100%";

}

function plateau(num) {
	var path = "webapi/jeu/plateau/"+num;
	$.getJSON(path, function(data) {
		JSONPlateau = data;
		afficherPlateau(JSONPlateau,num)
		$("#canvasEl").bind("click", function(e) {
			canvasClick(e);
			deplacement(num);

		});
	});
}

/* Affichage du nombres de cases que possède chaque joueur et
 * met en surbrillance le joueur qui doit jouer
 */

function afficherScore(data,num) {
	var path = "webapi/jeu/"+num+"/count";

	$.getJSON(path,function(donnees) {
		var nom="";
		for(var i = 0; i<donnees.score.length;i++) {
			if(data.idx == i) {
				nom += "<td align=\"center\" width="+canvas_width/donnees.score.length+" style=\"color:"+colors[i]+"; background-color: #ffff42\"><b>"+ 
				data.listeJoueurs[i].nom + "</td>";
			} else {
				nom += "<td align=\"center\" width="+canvas_width/donnees.score.length+" style=\"color:"+ colors[i]+";background-color: #ffffff\">" +
				data.listeJoueurs[i].nom + "</td>";
			}
		}

		var score="";
		for(var i = 0; i<donnees.score.length;i++) {
			score += "<td align=\"center\" width="+canvas_width/donnees.score.length+"> " + donnees.score[i] + " </td>";
		}

		document.getElementById('score').innerHTML ="<br><table><tr>"+nom+"</tr><tr>"+score+"</tr></table>";

	});
}

function afficherInformation(data) {
	document.getElementById('information').innerHTML = "<span class=\"marge\"> Au tour de <b><FONT COLOR="+colors[data.idx]+" >" + data.listeJoueurs[data.idx].nom + " </font></b> de jouer </span>";
	document.getElementById('information').innerHTML +="<span class=\"marge\"> Il reste "+data.tour+" tour de jeu !</span>";

}

/* Affichage du plateau avec la mise en couleurs des cases */

function afficherPlateau(data,num) {

	var canvas = document.getElementById("canvasEl");
	canvas.height = window.innerHeight - 250;
	canvas.width = document.body.clientWidth - 50  ;
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
				if (data.plateau[i][j].estObstacle === true) {
					context.fillRect((width / largeur) * i, (height / hauteur)
							* j, width / largeur, height / hauteur);
				} else if (data.plateau[i][j].effetAleatoire === true) {
					context.fillStyle = "#ffffff";
					context.fillRect((width / largeur) * i, (height / hauteur)
							* j, (width / largeur), height / hauteur);
					context.fillStyle = "#000000";
				} else if (data.plateau[i][j].occupant != undefined) {

					context.fillStyle = colors[data.plateau[i][j].occupant.id];
					context.fillRect((width / largeur) * i, (height / hauteur)
							* j, width / largeur, height / hauteur);
					context.fillStyle = "#000000";
					context.drawImage(images[data.plateau[i][j].occupant.id], (width / largeur) * i, (height / hauteur) * j, width / largeur, height / hauteur);

				} else if (data.plateau[i][j].proprietaire != undefined) {

					context.fillStyle = colors[data.plateau[i][j].proprietaire.id];
					context.fillRect((width / largeur) * i, (height / hauteur)
							* j, (width / largeur), height / hauteur);
					context.fillStyle = "#000000";
				} 
			}
		}
	}
	afficherInformation(data);
	afficherScore(data,num);
}

/* Permet le déplacement du joueur. Fait une vérification que le joueur qui doit jouer est
 * celui connecté
 */

function deplacement(num) {
	$.ajax({
		method: 'GET',
		contentType: 'text/html',
		url: 'webapi/jeu/'+num+'/coord/'+Math.floor(x/ (canvas_width/largeur_plateau))+'/'+Math.floor(y/ (canvas_height/hauteur_plateau))+'/move',
		beforeSend : function(req) {
			req.setRequestHeader('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')));
			console.log('AUTHORIZATION', 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('pwd')))
		},
		success: function(data) {
			afficherPlateau(data,num);
		}
	});
}

/* Permet de récuperer la valeur (en pixels) 
 * l'endroit où l'on a cliquer sur le canvas
 */

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


$(document).ready(function() {
	$("#partie").hide();
	window.addEventListener("resize", function(){afficherPlateau(JSONPlateau)}, true);
});