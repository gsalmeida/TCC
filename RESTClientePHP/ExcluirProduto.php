<?php 
	if(isset($_GET["id"])) {
		$id = $_GET["id"];
		$result = file_get_contents(
				'http://localhost:8080/TCCRESTful/rest/produtos/'.$id,
				false,
				stream_context_create(array(
						'http' => array(
								'method' => 'DELETE'
						)
				))
		);
		
		$resultAux = $result;
		$resultAux = str_replace('\'', '"', $result);
		$resultAux2 = str_replace('"', '\"', $resultAux);
		$resultAux3 = str_replace('\"', '"', $resultAux2);
		
		$arrayJSONResposta = json_decode($resultAux3, true);
		$codigoDeResposta = $arrayJSONResposta["codigoResposta"];
		$arrayHeadersRespostaCabecalhos = $arrayJSONResposta["headersRespostaCabecalhos"];
		$stringRetornoAlert = '{"codigoResposta": "'.$codigoDeResposta.'", "headersRespostaCabecalhos":[';
		for($a = 0; (!empty($arrayHeadersRespostaCabecalhos[$a])); $a++)
			$stringRetornoAlert .= ($a == 0) ? 
										'{"header": "'.$arrayHeadersRespostaCabecalhos[$a]["header"].'"}' 
											 : 
										',{"header": "'.$arrayHeadersRespostaCabecalhos[$a]["header"].'"}';
		$stringRetornoAlert .= ']}';
  		echo $stringRetornoAlert;
  		echo "<br/><br/><br/><a href='ProdutoListaJSON.php'>Lista de produtos JSON</a>";
  		die();
	}
?>
