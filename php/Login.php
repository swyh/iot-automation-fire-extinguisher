<?php
  include_once "./db_info.php";

   //mysqli_set_charset($conn,"utf8");


   $userID = $_POST['ID'];
   $userPassword = $_POST['Password'];

   $statement = mysqli_prepare($conn,"SELECT * FROM user WHERE id = ? AND pw = ?");
   mysqli_stmt_bind_param($statement,"ss",$userID,$userPassword);
   mysqli_stmt_execute($statement);

   mysqli_stmt_store_result($statement);
   mysqli_stmt_bind_result($statement,$userID,$userPassword,$userName);


   $response = array();
   $response["success"] = false;

   while(mysqli_stmt_fetch($statement))
   {
      $response["success"] = true;
      $response["name"] = $userName;
      $response["ID"] = $userID;
   }

   echo json_encode($response);


?>
