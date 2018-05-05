<?php
  include_once './db_info.php';

//json 형식으로 소화기 목록을 안드로이드로 보내주는 페이지
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



mysqli_close($conn);
?>
