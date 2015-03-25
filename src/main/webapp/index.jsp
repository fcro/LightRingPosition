<!DOCTYPE html>
<html lang="fr">
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Light Ring Position</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="bootstrap/css/jumbotron.css" rel="stylesheet">
<link href="plateau.css" rel="stylesheet">


<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src='js/plateau.js'></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="img/logo.png" /></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" class="form-control login"
							placeholder="Identifiant">
					</div>
					<div class="form-group">
						<input type="password" class="form-control password"
							placeholder="Mot de passe">
					</div>
					<input type="submit" class="btn btn-success" id="signinbtn"
						value="Connexion">
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<div class="container" id="homecontent">
		<aside class="signup" >
			<p>Inscription</p>
			<form>
				<table>
					<tr>
						<td><input type="text" class="form-control login"
							maxlength=20 placeholder="Identifiant"></td>
					</tr>
					<tr>
						<td><input type="email" id="email" maxlength=50
							placeholder="Adresse e-mail" class="form-control"></td>
					</tr>
					<tr>
						<td><input type="text" id="nickname" maxlength=20
							placeholder="Pseudo" class="form-control"></td>
					</tr>
					<tr>
						<td><input type="password" class="form-control password"
							maxlength=20 placeholder="Mot de passe"></td>
					</tr>
					<tr>
						<td><input type="submit" id="signupbtn"
							class="btn btn-primary btn-lg" role="button" value="M'inscrire">
						<td>
					</tr>
				</table>
			</form>
		</aside>

		<div class="row" id="accueil">
			<div id="indexmsg" class="col-md-7">
				<h1>Viens, on est bien</h1>
				<p>Sans déconner ce jeu déchire. Inscrivez-toi vite !</p>
				<p>
					<a class="btn btn-default" href="#" role="button">En savoir
						plus &raquo;</a>
				</p>
				<p>
					<a class="btn btn-default" href="#" role="button"
						 onclick="listerPartie()" >Lancer Partie 0</a>
				</p>
				
			</div>
			<div id="listePartie"></div>
			<div id="partie" >
				<center>
					<canvas id="canvasEl"></canvas>
				</center>

							<div id="information" align="center"></div>
					 
							<div id="score" align="center" ></div>
				
			</div>
		</div>



		<hr>

		<footer>
		<center>
			<p>&copy;Tout droit réservés - Light Ring Position 2015 - IUT A
				Lille</p></center>
		</footer>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src=js/account.js></script>
</body>
</html>

