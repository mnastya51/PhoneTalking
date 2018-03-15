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
<html>
<head>
    <title>Тарифы</title>
</head>
<body>
<ul id = "menu">
    <li onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<ul id="buttons">
    <li>Добавить</li>
    <li>Удалить</li>
</ul>
<div class="baseForm" id = "formTarif" style=" display: none">
    <div class ="titleForm">Добавление тарифа</div>
    <div class ="close" onclick = "showForm('formTarif', false)" title = "закрыть"></div>
    <div class = "form">
        <div class ="field">Наименование:</div>
        <input class ="input" id ="cityNameInput">
        <button class = "buttonOk" onclick="addCity(document.getElementById('cityNameInput').value)">ОК</button>
    </div>
</div>
<table id = "tableTarif">
    <tr>
        <th>Начало периода</th>
        <th>Конец периода</th>
        <th>Город</th>
        <th>Цена за минуту</th>
    </tr>
</table>
</body>
</html>
