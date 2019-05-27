<?php


if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $id_user = $_POST['id_user'];

  

require_once('config.php');

//cek npm terdaftar apa belum

$sql ="SELECT * FROM feed WHERE id_user ='$id_user'";
$check = mysqli_fetch_array(mysqli_query($con,$sql));
if (!isset($check)) {
  # code...
   $response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);
}  else {



$res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('caption'=>$row[1],'id_user'=>$row[2],'gambar'=>$row[3]));
  }
  echo json_encode(array("value"=>1,"results"=>$result));

}

mysqli_close($con);


}
?>