<?php

if($_POST['Data1'] != ""){
    $response = array();
    $response["success"] = true;
    echo json_encode($response);

    //아래는 주석처리
    include_once './db_info.php';

        $data_stream = "'".$_POST['Data1']."'";
        $query = "insert into val(value) values (".$data_stream.")";

        echo $query;

        $result = mysqli_query($conn, $query);

        echo "result : ";
        echo $result;

        if($result)
            echo "1";
        else
            echo "-1";

        mysqli_close($conn);


        //여기까지


  }
  else{

  }

?>
