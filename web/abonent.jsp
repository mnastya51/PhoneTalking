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
    <title>Абоненты</title>
</head>
<body onload = "selectAbonent()">
<ul id = "menu">
    <li onclick="redirecting('/abonent')" style="background-color: #808080">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')">Разговоры</li>
</ul>
<div class = "filtr" style="left: 160px; top:65px">
    <input class ="inputFiltr" placeholder="ФИО" id ="fioFiltr">
    <input class ="inputFiltr" style="left: 170px" placeholder="Телефон" id ="phoneFiltr">
    <input class ="inputFiltr" style="left: 180px" id ="addressFiltr" placeholder="Адрес">
    <div class ="fieldFiltr" style="left: 655px; top: -57px">Льготы:</div>
    <input type="checkbox" style="position:relative; left: 735px; top: -85px" class="checkbox" id ="facilityFiltr">
    <button class = "buttonOkFiltr" style="left: 740px; top: -90px" onclick="filterAbonent(document.getElementById('fioFiltr').value,
                document.getElementById('phoneFiltr').value,
                document.getElementById('addressFiltr').value,
                document.getElementById('facilityFiltr').checked)">ОК</button>
    <button class = "buttonOkFiltr" style="left: 750px; top:-90px; width: 150px" onclick="filterAbonentAll()">Показать все</button>
</div>
<ul id="buttons">
    <li id = "addAbonent" onclick = "showForm('formAbonent', false)">Добавить</li>
    <li id = "editAbonent" onclick = "showForm('formAbonent', true)">Изменить</li>
    <li  id = "deleteAbonent" onclick = "deleteAbonent()">Удалить</li>
</ul>
<div class = "baseForm" id = "formAbonent" style="display: none; height: 220px; width: 420px">
    <div class ="titleForm" id = "titleFormAbonent">Добавление абонента</div>
    <div class ="close" onclick = "showForm('formAbonent', false)" title = "закрыть"></div>
    <div class = "form" style="height: 197px">
        <div class ="field" style="top: 5px">ФИО:</div>
        <input class ="input"  style="left: 230px" id ="abonentNameInput">
        <div class ="field" id="phone" style="width: 220px; top: 10px">Телефон:</div>
        <input class ="input" style="left: 230px; top: 60px" id ="abonentPhoneInput">
        <div class ="field" style="top: 15px">Адрес:</div>
        <input class ="input" style="left: 230px; top: 90px" id ="abonentAddressInput">
        <div class ="field" style="top: 20px">Льготы:</div>
        <input type="checkbox" class="checkbox" id ="abonentFacilityInput">
        <button class = "buttonOk" style="top: 160px; left:180px"
                onclick="addAndEditAbonent(document.getElementById('abonentNameInput').value,
                document.getElementById('abonentPhoneInput').value,
                document.getElementById('abonentAddressInput').value,
                document.getElementById('abonentFacilityInput').checked)">ОК</button>
    </div>
</div>
</body>
</html>
