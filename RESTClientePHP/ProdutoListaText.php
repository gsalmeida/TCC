<!DOCTYPE HTML>
<html lang="pt-br">
<head>
<title>Site com Bootstrap</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="css/default.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!--
<script src="/js/default.js"></script>
-->

<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 55%;
	margin: auto;
}
</style>
</head>
<body class="corpo">
	<div class="container">
		<header class="fundo-layout-bootstrap">
			<a href="index.php"> <img class="img-responsive" width="351"
				height="104" src="img/jersey-logo.png" alt="logo"></a>
			<!--
			<a href="index.php"> <img class="img-responsive" width="1114" height="162" src="img/glassfish_logo.png" alt="logo"></a>
			-->
		</header>
		<div class="row fundo-layout-bootstrap">
			<div class="btn-group centralizado menu">
				<a href="index.php" type="button" class="btn btn-primary">Home</a>
				<div class="btn-group">
					<a href="#" type="button"
						class="btn btn-primary dropdown-toggle active"
						data-toggle="dropdown"> Produto<span class="caret"></span></a>
					<ul class="dropdown-menu widthCem" role="menu">
						<li><a href="ProdutoNovo.php" class="btn botao-over">Novo</a></li>
						<li><a href="ProdutoListaJSON.php" class="btn botao-over">Lista JSON</a></li>
						<li><a href="ProdutoListaXML.php" class="btn botao-over">Lista XML</a></li>
						<li><a href="ProdutoListaHTML.php" class="btn botao-over">Lista HTML</a></li>
<!-- 						<li><a href="ProdutoListaText.php" class="btn botao-over">Lista Text(CSV)</a></li> -->
					</ul>
				</div>
			</div>
			<div role="main" class="row" id="principal">
				<div class="col-xs-4">
					<h5>
						Você está em: <a class="migalha" href="index.php">Página Inicial</a>
						<span class="glyphicon glyphicon-chevron-right"></span> Produto <span
							class="glyphicon glyphicon-chevron-right">Lista Text 
					
					</h5>
				</div>
				<div class="col-xs-8">
				</div>
				<div class="col-xs-12 text-justify">
					<div class="jumbotron">
						<h3>Lista de Produtos Text</h3>
					</div>
					
					<div class="row"></div>
					<?php
					
					
					?>

				</div>
			</div>
		</div>
		<footer class="row-footer fundo-layout-bootstrap">
<!-- 			<img class="img-responsive" width="1117" height="74" src="img/rodape.png" alt="logo"> -->
				<div class="col-xs-6"></div>
				<div class="col-xs-4">
					<div class="row"></div>
					<p style="text-align:left;">Gabriel Almeida</p>
					<p style="text-align:left;">Tecnologia em Análise e Desenvolvimento de Sistemas</p>
					<p style="text-align:left;">Universidade Federal do Rio Grande</p>
				</div>
				<div class="col-xs-2">
					<img class="img-responsive" width="133" height="126" src="http://www.furg.br/img/logo.png" alt="logo">
				</div>
			
		</footer>
	</div>
	</div>
</body>
</html>