<?php
  //include_once './db_info.php';
/*
    $sql = "select * from fire";
    $result=mysqli_query($conn,$sql);
    $data = array();

    if($result){

    while($row=mysqli_fetch_array($result)){
        array_push($data,
            array('id'=>$row[0],
            'name'=>$row[1],
            'startdate'=>$row[2],
            'image'=>$row[3]
        ));
    }

    header('Content-Type: application/json; charset=utf8');
    $json = json_encode(array("webnautes"=>$data));
    echo $json;
}
else{
    echo "SQL문 처리중 에러 발생 : ";
    echo mysqli_error($link);
}
*/

   $response = array();
   $response["success"] = true;
   echo json_encode($response);
?>
