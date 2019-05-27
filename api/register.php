<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $nm_user = $_POST['nm_user'];
  $email= $_POST['email'];
  $pass = $_POST['pass'];
 $nm_lengkap = $_POST['nm_lengkap'];
  $no_kk = $_POST['no_kk'];
  $no_ktp = $_POST['no_ktp'];
  $id_user = uniqid();
  $alamat = $_POST['alamat'];





require_once('config.php');
//cek npm terdaftar apa belum

$sql ="SELECT * FROM user WHERE nm_user = '$nm_user'";

$check = mysqli_fetch_array(mysqli_query($con,$sql));
if (isset($check)) {
  # code...
  $response["value"] = 0;
  $response["message"] = "oops! nama user sudah terdaftar";
  echo json_encode($response);
}  else {

$sql ="INSERT INTO user (id_user,nm_user,email,pass,nm_lengkap, no_kk,no_ktp,alamat) VALUES ('$id_user','$nm_user','$email','$pass','$nm_lengkap', '$no_kk','$no_ktp','$alamat')";
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


}
mysqli_close($con);
}else
{

$response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);

}
?>