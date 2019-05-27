<?php
require_once('config.php');
if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM feed ORDER BY pk DESC";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('caption'=>$row[1],'id_user'=>$row[2], 'gambar'=>$row[3]));
  }
  echo json_encode(array("value"=>1,"results"=>$result));
  mysqli_close($con);
}