<?php
    $con = mysqli_connect("localhost", "id844316_admin", "admin", "id844316_parkhunt");
    
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $name, $email, $password);
    mysqli_stmt_execute($statement);
    
	mysqli_stmt_close($statement);
	
	mysqli_close($con);
	
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>