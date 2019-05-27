<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $id_user = $_POST['id_user'];
  $caption= $_POST['caption'];
  $gambar = $_POST['gambar'];
 $id_feed = uniqid();
  // $waktu = $_POST['waktu'];
  





require_once('config.php');
//cek npm terdaftar apa belum



$sql ="INSERT INTO feed (id_user,caption,gambar,id_feed) VALUES ( '$id_user','$caption','$gambar', '$id_feed')";
if (mysqli_query($con,$sql)) {
  # code...
$response['value'] = 1;
$response["message"]= "sukses mendaftar silahkan login";
echo json_encode($response);

} else {

  $response['value'] = 0;
  $response["message"] ="ups coba lagi";
  echo json_encode($response);
}

mysqli_close($con);
}else
{

$response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);

}
?>