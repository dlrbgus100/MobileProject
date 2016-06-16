<?php
	$con = mysqli_connect("127.0.0.1","root","apmsetup","userinfo")
	or die('Error Connection to MySQL server');
	
	$id = $_POST['id'];
	$pass = $_POST['pass'];
		
	if($id == NULL || $pass == NULL){
		echo "noInput";
	}
	else{
	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases');

	

	$query = "select *from user where id = '$id' and pass = '$pass'";
	$res = mysqli_query($con,$query) or die('Error Querying databases 2');

	$row = mysqli_fetch_array($res);
	if( $id == $row['id'] && $pass == $row['pass']){
		echo $id;
	}
	else
		echo "fail";

	}
?>