<%--
  Created by IntelliJ IDEA.
  User: matek3022
  Date: 28.02.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles.css"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<html>
<head>
    <title>Тарифы</title>
</head>
<body onload = "selectTarif()">
<ul id = "menu">
    <li onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<ul id="buttons">
    <li id = "addTarif" onclick = "showForm('formTarif', false)">Добавить</li>
    <li>Удалить</li>
</ul>
<div class="baseForm" id = "formTarif" style=" display: none">
    <div class ="titleForm">Добавление тарифа</div>
    <div class ="close" onclick = "showForm('formTarif', false)" title = "закрыть"></div>
    <div class = "form">
        <div class ="field">Город:</div>
        <select class ="input" id ="cityNameSelect"  size="1" multiple name="cities[]">
            <option disabled>Выберите город</option>>
        </select>
        <button class = "buttonOk" onclick="addTarif(document.getElementById('cityNameSelect').value)">ОК</button>
    </div>
</div>
</body>
</html>
