<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-top: 20px;
        }

        .menu {
            text-align: center;
            margin-top: 20px;
        }

        .menu a {
            display: block;
            margin: 10px 0;
            color: #007bff;
            text-decoration: none;
            font-size: 18px;
        }

        .menu a:hover {
            text-decoration: underline;
        }

        a.logout {
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin-top: 20px;
        }

        a.logout:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2>Home</h2>

<div class="menu">
    <a href="listproducts">List Products</a>
    <a href="cart">Cart</a>
    <a href="orders">Orders</a>
</div>

<a href="logout" class="logout">Logout</a>

</body>
</html>
