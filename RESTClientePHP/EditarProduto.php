<?php 

if(isset($_GET["id"])) {
	$idGet = $_GET["id"];
	$formato = !empty(isset($_GET["lista"])) ? $_GET["lista"] : "json";
	if(($formato == "XML") || ($formato == "xml")) $formato = "xml";
	else $formato = "json";
	
	if($formato == "json") {
		$url = file_get_contents("http://localhost:8080/TCCRESTful/rest/produtos/json/".$idGet);
		if($url != "{\"codigoResposta\":\"500\"}") {
			$arrayJSON = json_decode($url, true);
?>

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
							class="glyphicon glyphicon-chevron-right">Editar 
					
					</h5>
				</div>
				<div class="col-xs-8">
				</div>
				<div class="col-xs-12 text-justify">
					<div class="jumbotron">
						<h3>Editar Produto</h3>
					</div>
					<div class="row"></div>
					<form action="acaoEditarProduto.php?lista=json" method="post">
						<div class="row"></div>
						<div class="form-group" style="display:none;">
							<div class="col-xs-1"></div>
							<label class="control-label col-xs-3" for="idProduto">Id:</label>
							<div class="col-xs-8"></div>
							<div class="row input-formulario"></div>
							<div class="col-xs-2"></div>
							<div class="col-xs-9">
								<input type="text" class="form-control" id="idProduto"
									name="idProduto" autofocus required value='<?php echo $arrayJSON["id"];?>'>
							</div>
							<div class="col-xs-1"></div>
						</div>
						
						<div class="form-group">
							<div class="col-xs-1"></div>
							<label class="control-label col-xs-3" for="nomeProduto">Nome:</label>
							<div class="col-xs-8"></div>
							<div class="row input-formulario"></div>
							<div class="col-xs-2"></div>
							<div class="col-xs-9">
								<input type="text" class="form-control" id="nomeProduto"
									name="nomeProduto" autofocus required value='<?php echo $arrayJSON["nome"];?>'>
							</div>
							<div class="col-xs-1"></div>
						</div>

						<div class="row"></div>

						<div class="row"></div>
						<div class="form-group">
							<div class="col-xs-1"></div>
							<label class="control-label col-xs-3" for="precoProduto">Preço:</label>
							<div class="col-xs-8"></div>
							<div class="row input-formulario"></div>
							<div class="col-xs-2"></div>
							<div class="col-xs-9">
								<div class="input-group">
									<span class="input-group-addon">$</span> 
										<input class="form-control" id="precoProduto" name="precoProduto"
												type="number" step="0.00001" data-number-to-fixed="2" required value='<?php echo $arrayJSON["preco"];?>'>
									<!--
							    	<input type="number" value="1000" min="0"   data-number-stepfactor="100" class="form-control currency" id="c2" />
									-->
								</div>
							</div>
						</div>
						<div class="col-xs-1"></div>
						<div class="row"></div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Submit</button>
							</div>
						</div>
						<?php
							$qtdeLinks = count($arrayJSON["links"]);
							for($b = 0; $b < $qtdeLinks; $b++) {
								if(!empty($arrayJSON["links"][$b]["rel"]))
									if($arrayJSON["links"][$b]["rel"] == "anterior") {
										$idAnterior = explode("/", $arrayJSON["links"][$b]["href"]);
										$idAnterior = $idAnterior[count($idAnterior)-1];
										echo '<div class="col-xs-8">'.
											 	'<div class="row"><a href="EditarProduto.php?id='.$idAnterior.'&lista=json"><img src="img/anterior.png"></a></div>'.
											 '</div>';
									} else if($arrayJSON["links"][$b]["rel"] == "proximo") {
										$idProximo = explode("/", $arrayJSON["links"][$b]["href"]);
										$idProximo = $idProximo[count($idProximo)-1];
										echo '<div class="col-xs-4">'.
											 	'<div class="row"><a href="EditarProduto.php?id='.$idProximo.'&lista=json"><img src="img/proximo.png"></a></div>'.
											 '</div>';
									}
							}
						?>
					</form>
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

<?php 
		
		}
	} else {
		$urlXML = file_get_contents("http://localhost:8080/TCCRESTful/rest/produtos/xml/".$idGet);
// 		echo $urlXML;die();
		$xml = simplexml_load_string($urlXML);
		
		?>
		
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
<!-- 									<li><a href="ProdutoListaText.php" class="btn botao-over">Lista Text(CSV)</a></li> -->
								</ul>
							</div>
						</div>
						<div role="main" class="row" id="principal">
							<div class="col-xs-4">
								<h5>
									Você está em: <a class="migalha" href="index.php">Página Inicial</a>
									<span class="glyphicon glyphicon-chevron-right"></span> Produto <span
										class="glyphicon glyphicon-chevron-right">Novo 
								
								</h5>
							</div>
							<div class="col-xs-8">
							</div>
							<div class="col-xs-12 text-justify">
								<div class="jumbotron">
									<h3>Editar Produto</h3>
								</div>
								<div class="row"></div>
								<form action="acaoEditarProduto.php?lista=xml" method="post">
									<div class="row"></div>
									<div class="form-group" style="display:none;">
										<div class="col-xs-1"></div>
										<label class="control-label col-xs-3" for="idProduto">Id:</label>
										<div class="col-xs-8"></div>
										<div class="row input-formulario"></div>
										<div class="col-xs-2"></div>
										<div class="col-xs-9">
											<input type="text" class="form-control" id="idProduto"
												name="idProduto" autofocus required value='<?php echo $xml->produto[0]->id;?>'>
										</div>
										<div class="col-xs-1"></div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-1"></div>
										<label class="control-label col-xs-3" for="nomeProduto">Nome:</label>
										<div class="col-xs-8"></div>
										<div class="row input-formulario"></div>
										<div class="col-xs-2"></div>
										<div class="col-xs-9">
											<input type="text" class="form-control" id="nomeProduto"
												name="nomeProduto" autofocus required value='<?php echo $xml->produto[0]->nome;?>'>
										</div>
										<div class="col-xs-1"></div>
									</div>
			
									<div class="row"></div>
			
									<div class="row"></div>
									<div class="form-group">
										<div class="col-xs-1"></div>
										<label class="control-label col-xs-3" for="precoProduto">Preço:</label>
										<div class="col-xs-8"></div>
										<div class="row input-formulario"></div>
										<div class="col-xs-2"></div>
										<div class="col-xs-9">
											<div class="input-group">
												<span class="input-group-addon">$</span> 
													<input class="form-control" id="precoProduto" name="precoProduto"
															type="number" step="0.00001" data-number-to-fixed="2" required value='<?php echo $xml->produto[0]->preco;?>'>
												<!--
										    	<input type="number" value="1000" min="0"   data-number-stepfactor="100" class="form-control currency" id="c2" />
												-->
											</div>
										</div>
									</div>
									<div class="col-xs-1"></div>
									<div class="row"></div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-default">Submit</button>
										</div>
									</div>
									
									<?php 
									$qtdeLinks = count($xml->produto[0]->linksHATEOAS->link);
									for($b = 0; $b < $qtdeLinks; $b++) {
										if(!empty($xml->produto[0]->linksHATEOAS->link[$b]->rel))
											if($xml->produto[0]->linksHATEOAS->link[$b]->rel == "anterior") {
												$idAnterior = explode("/", $xml->produto[0]->linksHATEOAS->link[$b]->href);
												$idAnterior = $idAnterior[count($idAnterior)-1];
												echo '<div class="col-xs-8">'.
													 	'<div class="row"><a href="EditarProduto.php?id='.$idAnterior.'&lista=xml"><img src="img/anterior.png"></a></div>'.
													 '</div>';
											} else if($xml->produto[0]->linksHATEOAS->link[$b]->rel == "proximo") {
												$idProximo = explode("/", $xml->produto[0]->linksHATEOAS->link[$b]->href);
												$idProximo = $idProximo[count($idProximo)-1];
												echo '<div class="col-xs-4">'.
													 	'<div class="row"><a href="EditarProduto.php?id='.$idProximo.'&lista=xml"><img src="img/proximo.png"></a></div>'.
													 '</div>';
											} 
												
									}
									?>
																		
									
								</form>
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
		<?php 
	}
}

?>
