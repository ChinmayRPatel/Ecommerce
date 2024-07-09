<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Forget Password</title>
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
        }

        input[type="text"] {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: calc(100% - 22px); /* Adjust for padding and border */
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

        a {
            display: inline-block;
            text-decoration: none;
            color: #007bff;
            margin-top: 20px;
        }

        a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<form action="sendotp" method="post">
    Email: <input type="text" name="email"/><br>
    <input type="submit" value="Send OTP"/>
</form>
<br>
<div class="error">${error}</div>
<br>
<a href="login">Login</a>

</body>
</html>
