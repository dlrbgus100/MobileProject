<?php
	
	$file_name = $_FILES['image']['name'];
	$tmp_file = $_FILES['image']['tmp_name'];
	
	$file_path = './image/'.$file_name;
	
	$r = move_uploaded_file($tmp_file, $file_path);

	$con = mysqli_connect("127.0.0.1","root","apmsetup","userinfo") 
	or die('Error Connection to MySQL server');

	$type = $_POST['type'];
	$size = $_POST['size'];
	$price = $_POST['price'];
	$term = $_POST['term'];
	$id = $_POST['id'];
	$memo = $_POST['memo'];
	
	if($type==NULL || $size==NULL || $price==NULL || $term==NULL ||$memo==NULL){
		echo "noInput";
	}
	else{
	$query = "set names euckr";
	$res = mysqli_query($con,$query) or die('Error Querying databases');

	$query = "select *from pet where id='$id'";
	$res = mysqli_query($con,$query) or die('Error Queryin databases');

	$row = mysqli_fetch_array($res);
	
	if($id == $row['id'])
		echo "dup";
	
	else{
		$query = "set names euckr";
		$res = mysqli_query($con,$query) or die('Error Querying databases');
		$query = "insert into pet values('$type', '$size','$price','$term','$id','$memo')";
		$res = mysqli_query($con,$query) or die('Error Querying databases2');
		mysqli_close($con);		
}
	}
	
?>