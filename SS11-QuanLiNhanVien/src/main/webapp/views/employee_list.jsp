<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/24/2023
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }

    h2 {
        color: #333;
    }

    a {
        text-decoration: none;
        color: #3498db;
    }

    a:hover {
        color: #207cca;
    }

    p {
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        padding: 10px;
        text-align: left;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #e5e5e5;
    }

    td a {
        display: inline-block;
        padding: 5px 10px;
        background-color: #3498db;
        color: white;
        text-decoration: none;
    }

    td a:hover {
        background-color: #207cca;
    }

    button {
        background-color: #4CAF50;
        border: none;
        color: white;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 4px;
    }

    button a {
        color: inherit;
        text-decoration: none;
    }

    button:hover {
        background-color: #45a049;
    }
    div {
        text-align: center; /* Center the links */
    }

    div a {
        display: inline-block; /* Display links in a row */
        margin: 5px; /* Add some space between links */
        padding: 8px 12px; /* Add padding to the links */
        text-decoration: none; /* Remove underline from links */
        border: 1px solid #333; /* Add a border to the links */
        border-radius: 5px; /* Add rounded corners to the links */
        background-color: #f0f0f0; /* Set background color for the links */
        color: #333; /* Set text color for the links */
    }

    div a:hover {
        background-color: #ddd; /* Change background color on hover */
    }
</style>
<body>
<h2>User</h2>
<p>
    <button><a href="Employee?action=add">Thêm mới nhân viên</a></button>
    <button><a href="/index.jsp">Back Home</a></button>
</p>
<table border="1" cellspacing="0">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Phone</td>
        <td>Address</td>
        <td>Birthday</td>
        <td>Sex</td>
        <td>Salary</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items='${list_employee}' var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.phone}</td>
            <td>${item.address}</td>
            <td>${item.birthday}</td>
            <td>${item.sex ? "nam" : "nữ"}</td>
            <td>${item.salary}</td>
            <td><a href="/Employee?action=edit&id=${item.id}">Edit</a></td>
            <td><a href="/Employee?action=delete&id=${item.id}&noPage=1" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div>
    <div id="pagination">
        <c:forEach var="i" begin="1" end="${totalPage}">
            <a href="Employee?noPage=${i}" class="pagination-link">${i}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>
