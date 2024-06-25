<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Signup</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f4f4f9;
			margin: 0;
			padding: 0;
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
		}

		form {
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
			text-align: center;
			width: 300px;
		}

		h2 {
			color: #333;
			margin-bottom: 20px;
		}

		input[type="text"],
		input[type="password"] {
			width: calc(100% - 22px); /* Adjust for padding and border */
			padding: 10px;
			margin: 10px 0;
			border: 1px solid #ccc;
			border-radius: 5px;
		}

		input[type="radio"] {
			margin: 0 5px;
		}

		input[type="submit"] {
			padding: 10px 20px;
			background-color: #007bff;
			color: white;
			border: none;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.3s ease;
		}

		input[type="submit"]:hover {
			background-color: #0056b3;
		}

		label {
			display: block;
			margin: 10px 0;
			text-align: left;
		}

		.gender {
			display: flex;
			justify-content: center;
			align-items: center;
		}
	</style>
</head>
<body>
<h2>Signup</h2>

<form method="post" action="signup">
	<label>First Name:</label>
	<input type="text" name="firstName"/><br>
	<div class="gender">
		<label>Gender:</label>
		<input type="radio" name="gender" value="male"> Male
		<input type="radio" name="gender" value="female"> Female
	</div><br>
	<label>Email:</label>
	<input type="text" name="email"/><br>
	<label>Password:</label>
	<input type="password" name="password"/><br><br>
	<input type="submit" value="Signup"/>
</form>
</body>
</html>
