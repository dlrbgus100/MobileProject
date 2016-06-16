<?php
	$con = mysqli_connect("127.0.0.1","root","apmsetup","userinfo")
	or die('Error Connection to MySQL server');
	
	$id = $_POST['id'];

	if($id == NULL){
		echo "noInput";
	}
	else if(strlen($id)<=3 && strlen($id)>=1){
		echo "short";
	}
	else{
	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases');

	$query = "select id from user where id='$id'";
	$res = mysqli_query($con,$query) or die('Error Querying databases2');
	$row = mysqli_num_rows($res);
		
	echo $row;
	

	mysqli_close($con);
}
?>