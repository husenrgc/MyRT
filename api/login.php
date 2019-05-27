<?php


if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $nm_user = $_POST['nm_user'];

  $pass = $_POST['pass'];

require_once('config.php');

//cek npm terdaftar apa belum

$sql ="SELECT * FROM user WHERE nm_user ='$nm_user' AND pass ='$pass'";
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
    array_push($result, array('id_user'=>$row[0], 'nm_user'=>$row[1],'nm_lengkap'=>$row[4]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));

}

mysqli_close($con);


}
?>