<?php
$part = "./upload/";

 $response= array();
 $value="";
 $message ="";



 if ($_SERVER['REQUEST_METHOD']=='POST') {
 	$urlname = $_POST['urlname'];
 	$filename ="img".$urlname.".jpg";
 	$destinationfile =$part.$filename;

 	
if ($_FILES['imageupload']) {

if (move_uploaded_file($_FILES['imageupload']['tmp_name'], $destinationfile)) {


$value =1;

$message ="sukses";
	# code...
} else{
	$value =0;

$message ="ggl";
}

	# code...
} else{

	$value =0;

$message ="req error";
}
}else{

	$value =0;

$message ="req novalid";
}
$response['value']= $value;
$response['message']= $message;
echo json_encode($response);


?>