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
    <li style = "border-top-right-radius: 20px" onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')"  style="background-color: saddlebrown">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<div class = "filtr" style="left: 280px; top: 55px">
    <input class ="inputFiltr" style="left: 60px" placeholder = "Город" id ="cityFiltr">
    <input class ="inputFiltr" style="left: 70px" placeholder = "Начало периода" id ="periodStartFiltr">
    <input class ="inputFiltr" style="left: 80px" placeholder = "Конец периода"  id ="periodEndFiltr">
    <input class ="inputFiltr" style="left: 90px" placeholder = "Цена за минуту" id ="minCostFiltr">
    <button class = "buttonOkFiltr" style="left: 100px" onclick="filterTarif(cityFiltr.value,periodStartFiltr.value,
                periodEndFiltr.value,minCostFiltr.value)">ОК</button>
    <button class = "buttonOkFiltr" style="left: 110px; width: 150px" onclick="filterTarifAll()">Показать все</button>
</div>
<ul id="buttons" style="left: 40px">
    <li id = "addTarif" onclick = "showForm('formTarif', false)">Добавить</li>
    <li id = "deleteTarif" onclick = "deleteTarif()">Удалить</li>
</ul>
<div class="baseForm" id = "formTarif" style="display: none; width: 325px">
    <div class ="titleForm">Добавление тарифа</div>
    <div class ="close" onclick = "showForm('formTarif', false)" title = "закрыть"></div>
    <div class = "form" style="height: 195px">
        <div class ="field" style="top: 10px">Город:</div>
        <select class ="input" id ="cityNameSelect" style="width: 150px; height: 25px; left: 160px"></select>
        <div class ="field" style="top: 15px">Начало периода:</div>
        <input class ="input" type="time" value = "00:00" id ="startPeriod" style="width: 150px; left: 160px; top: 63px">
        <div class ="field" style="top: 20px">Конец периода:</div>
        <input class ="input" type="time" value = "00:00" id ="finishPeriod" style="width: 150px; left: 160px; top: 95px">
        <div class ="field" style="top: 25px">Цена за минуту:</div>
        <input class ="input" id ="minCost" style="width: 150px; left: 160px; top: 127px">
        <button class = "buttonOk" style="left: 140px; top: 160px" onclick="addTarif(cityNameSelect.options[cityNameSelect.selectedIndex].text,
        startPeriod.value,finishPeriod.value,minCost.value)">ОК</button>
    </div>
</div>
</body>
</html>
