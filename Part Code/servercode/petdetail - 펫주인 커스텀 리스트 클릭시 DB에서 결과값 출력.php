<?php

	$con=mysqli_connect("127.0.0.1","root","apmsetup","userinfo")
	or die('Error Connection to MySQL server');

	$id = $_POST['id'];

	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases.');
	
	$query = "select p.ptype, p.psize, p.price, p.term, u.address, u.email, u.number, p.memo from user u JOIN pet p where p.id=u.id";
	
	$res = mysqli_query($con,$query) or die('Error Querying databases2.');
	
	
	while($row = mysqli_fetch_array($res)){
	 
	echo $row['ptype']."*";
	echo $row['psize']."*";
	echo $row['price']."*";
	echo $row['term']."*";
	echo $row['address']."*";
	echo $row['email']."*";
	echo $row['number']."*";
	echo $row['memo']."*";
	}


	mysqli_close($con);
?>
