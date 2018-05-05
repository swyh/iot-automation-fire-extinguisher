<?php

include_once './android/db_info.php';

   $name = $_GET['temp'];

   //$name = '1234';

   $statement = mysqli_prepare($conn,"INSERT INTO val (value) VALUES (?)");
   mysqli_stmt_bind_param($statement,"i",$name);
   mysqli_stmt_execute($statement);



?>
