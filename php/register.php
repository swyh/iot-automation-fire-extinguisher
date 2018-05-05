<?php
echo "?!";

	if(isset($_POST["Token"])){
		echo "?!";

		$token = $_POST["Token"];
		//데이터베이스에 접속해서 토큰을 저장
		include_once 'config.php';
		$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
		$query = "INSERT INTO users(Token) Values ('$token') ON DUPLICATE KEY UPDATE Token = '$token'; ";
		echo "dk" + $query;
		echo "??";
		mysqli_query($conn, $query);

		mysqli_close($conn);
	}

?>
