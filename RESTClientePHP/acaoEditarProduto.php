<?php 

	if( (isset($_POST["nomeProduto"])) and (isset($_POST["precoProduto"])) ) {
		$nomeProduto = $_POST["nomeProduto"];
		$precoProduto = $_POST["precoProduto"];
		$idProduto = $_POST["idProduto"];
		
		$data = array("nome" => "".$nomeProduto."", "preco" => "".$precoProduto."");
		$data_string = json_encode($data);
		$data_string = $data_string;
		
		$result = file_get_contents('http://localhost:8080/TCCRESTful/rest/produtos/'.$idProduto, null,
				stream_context_create(array(
						'http' => array(
								'method' => 'PUT',
								'header' => array('Content-Type: application/json'."\r\n"
										. 'Authorization: username:key'."\r\n"
										. 'Content-Length: ' . strlen($data_string) . "\r\n"
								),
								'content' => $data_string
						)
				)
				));
		
		echo $result;
		
		$formato = !empty(isset($_GET["lista"])) ? $_GET["lista"] : "json";
		if(($formato == "XML") || ($formato == "xml")) $formato = "xml";
		else $formato = "json";
		if($formato == "json") echo "<br/><br/><br/><a href='ProdutoListaJSON.php'>Lista de produtos JSON</a>";
		else echo "<br/><br/><br/><a href='ProdutoListaXML.php'>Lista de produtos XML</a>";
		die();
	}
?>
