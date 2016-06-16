<?php

	$con=mysqli_connect("127.0.0.1","root","apmsetup","userinfo")
	or die('Error Connection to MySQL server');

	$id = $_POST['id'];

	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases.');
	
	$query = "select s.price, u.address, s.term, s.pet, s.back, s.petsize, u.email, u.number, s.memo from user u JOIN sitter s where s.id=u.id";
	
	$res = mysqli_query($con,$query) or die('Error Querying databases2.');
	
	
	while($row = mysqli_fetch_array($res)){
	 
	echo $row['price']."*";
	echo $row['address']."*";
	echo $row['term']."*";
	echo $row['pet']."*";
	echo $row['back']."*";
	echo $row['petsize']."*";
	echo $row['email']."*";
	echo $row['number']."*";
	echo $row['memo']."*";
	}


	mysqli_close($con);
?>