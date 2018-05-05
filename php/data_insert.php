<?php

include_once './db_info.php';

    $data_stream = "'".$_GET['Data1']."','".$_GET['Data2']."','".$_GET['Data3']."'";
    $query = "insert into fire(id,name,startdate) values (".$data_stream.")";

    echo $query;

    $result = mysqli_query($conn, $query);

    echo "result";
    echo $result;

    if($result)
        echo "1";
    else
        echo "-1";

    mysqli_close($conn);
?>
