<?php
    $conn = mysqli_connect("localhost","root",940520);
    mysqli_select_db($conn, "firedb");

    if(!$conn){
      echo "mysql 접속 에러";
      echo mysqli_connect_error();
      exit();
    }
    mysqli_set_charset($conn,"utf8");
?>
