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
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/jquery-3.3.1.js"></script>
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
<div class = "filtr" style="left: 220px">
    <input class ="inputFiltr" style="left: 60px; top: -10px" placeholder = "Город" id ="cityFiltr">
    <input class ="inputFiltr" style="left: 70px; top: -10px" placeholder = "Начало периода" id ="periodStartFiltr">
    <input class ="inputFiltr" style="left: 80px; top: -10px" placeholder = "Конец периода"  id ="periodEndFiltr">
    <input class ="inputFiltr" style="left: 90px; top: -10px" placeholder = "Цена за минуту" id ="minCostFiltr">
    <button class = "buttonOkFiltr" style="left: 100px; top: -10px" onclick="filterTarif(document.getElementById('cityFiltr').value,
                document.getElementById('periodStartFiltr').value,
                document.getElementById('periodEndFiltr').value,
                document.getElementById('minCostFiltr').value)">ОК</button>
</div>
<ul id="buttons">
    <li id = "addTarif" onclick = "showForm('formTarif', false)">Добавить</li>
    <li id = "deleteTarif" onclick = "deleteTarif()">Удалить</li>
</ul>
<div class="baseForm" id = "formTarif" style="display: none; width: 325px">
    <div class ="titleForm">Добавление тарифа</div>
    <div class ="close" onclick = "showForm('formTarif', false)" title = "закрыть"></div>
    <div class = "form" style="height: 180px">
        <div class ="field">Город:</div>
        <select class ="input" id ="cityNameSelect" style="width: 150px; height: 25px; left: 160px; top: 30px"></select>
        <div class ="field">Начало периода:</div>
        <input class ="input" type="time" value = "00:00" id ="startPeriod" style="width: 150px; left: 160px; top: 60px">
        <div class ="field">Конец периода:</div>
        <input class ="input" type="time" value = "00:00" id ="finishPeriod" style="width: 150px; left: 160px; top: 90px">
        <div class ="field">Цена за минуту:</div>
        <input class ="input" id ="minCost" style="width: 150px; left: 160px; top: 120px">
        <button class = "buttonOk" style="left: 140px; top: 150px" onclick="addTarif(document.getElementById('cityNameSelect').options[document.getElementById('cityNameSelect').selectedIndex].text,
        document.getElementById('startPeriod').value,
        document.getElementById('finishPeriod').value,
        document.getElementById('minCost').value)">ОК</button>
    </div>
</div>
</body>
</html>
