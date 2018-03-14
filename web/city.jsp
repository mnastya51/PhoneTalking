<%--
  Created by IntelliJ IDEA.
  User: matek3022
  Date: 28.02.2018
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles.css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script.js"></script>
<html>
<head>
    <title>Города</title>
</head>
<body onload = "selectCities()">
<ul id = "menu">
    <li onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<ul id="buttons">
    <li id = "addCity" onclick = "showFormCity('formCity')">Добавить</li>
    <li id = "deleteCity" onclick = "deleteCity()">Удалить</li>
</ul>
<div id="formCity" style=" display: none">
    <div id="titleFormCity">Добавление города</div>
    <div class ="close" onclick = "showFormCity('formCity')" title = "закрыть">x</div>
    <div id = "fieldCity">
        <p>Наименование:</p>
        <input id="cityName">
        <button class = "buttonOk" onclick="addCity(document.getElementById('cityName').value)">ОК</button>
    </div>
</div>
</body>
</html>
