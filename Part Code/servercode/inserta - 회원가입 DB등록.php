<?php
	
	$con = mysqli_connect("127.0.0.1","root","apmsetup","userinfo")
	or die('Error Connection to MySQL server');
	
	$id = $_POST['id'];
	$pass = $_POST['pass'];
	$name = $_POST['name'];
	$sex = $_POST['sex'];
	$age = $_POST['age'];
	$email = $_POST['email'];
	$number = $_POST['number'];
	$address = $_POST['address'];	
	

	
	
	if($id == NULL || $pass == NULL || $name == NULL || $sex == NULL || $age == NULL || $email == NULL || $number == NULL || $address == NULL)
		echo "noInput";
	
	else{

		$query = "set names euckr";
	
		$res = mysqli_query($con,$query) or die('Error Querying databases2');
		$query = "select *from user where id='$id'";
		$res = mysqli_query($con,$query) or die('Error Querying databases2');
		$fos = mysqli_fetch_array($res);
	
		if($id == $fos['id'])
			echo "dup";
		
		else{

		$query = "insert into user values('$id', '$pass','$name','$sex','$age','$email','$number','$address')";
	
		$res = mysqli_query($con,$query) or die('Error Querying databases2');

		mysqli_close($con);
		}
		
	}
?>
