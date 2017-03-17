<?php
    $con = mysqli_connect("localhost", "id844316_admin", "admin", "id844316_parkhunt");
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM users WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email,$password);
    mysqli_stmt_execute($statement);
	
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $ID, $name, $email, $password);
    
	mysqli_stmt_close($statement);
	mysqli_close($con);
	
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement))
	{
            $response["success"] = true;  
            $response["name"] = $name;
			$response["email"] = $email;
			$response["password"] = $password;
    }
    echo json_encode($response);
	
	
?> 