<?php
	
	$file_name = $_FILES['image']['name'];
	$tmp_file = $_FILES['image']['tmp_name'];
	
	$file_path = './image/'.$file_name;
	
	$r = move_uploaded_file($tmp_file, $file_path);

	$con = mysqli_connect("127.0.0.1","root","apmsetup","userinfo") 
	or die('Error Connection to MySQL server');
	
	$id = $_POST['id'];
	$term = $_POST['term'];
	$price = $_POST['price'];
	$back = $_POST['back'];
	$pet = $_POST['pet'];
	$petsize = $_POST['petsize'];
	$memo = $_POST['memo'];
	
	if($term==NULL || $price==NULL || $back==NULL || $pet==NULL || $petsize==NULL ||$memo==NULL){
		echo "noInput";
	}
	else{
	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases');

	$query = "select *from sitter where id='$id'";
	$res = mysqli_query($con,$query) or die('Error Queryin databases');

	$row = mysqli_fetch_array($res);
	
	if($id == $row['id'])
		echo "dup";
	
	else{
		$query = "set names euckr";
		$res = mysqli_query($con,$query) or die('Error Querying databases');
		$query = "insert into sitter values('$id', '$term','$price','$back','$pet','$petsize','$memo')";
		$res = mysqli_query($con,$query) or die('Error Querying databases2');
		mysqli_close($con);		
}
	}
	
?>