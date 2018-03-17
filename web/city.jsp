<%--
  Created by IntelliJ IDEA.
  User: matek3022
  Date: 28.02.2018
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles.css"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script.js"></script>
<html>
<head>
    <title>Города</title>
</head>
<body onload = "selectCities()">
<ul id = "menu">
    <li onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')" style="background-color: #808080">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<ul id="buttons">
    <li id = "addCity" onclick = "showForm('formCity', false)">Добавить</li>
    <li id = "deleteCity" onclick = "deleteCity()">Удалить</li>
</ul>
<div class="baseForm" id = "formCity" style=" display: none">
    <div class ="titleForm">Добавление города</div>
    <div class ="close" onclick = "showForm('formCity', false)" title = "закрыть"></div>
    <div class = "form">
        <div class ="field">Наименование:</div>
        <input class ="input" id ="cityNameInput">
        <button class = "buttonOk" onclick="addCity(document.getElementById('cityNameInput').value)">ОК</button>
    </div>
</div>
</body>
</html>
