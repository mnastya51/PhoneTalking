<%--
  Created by IntelliJ IDEA.
  User: matek3022
  Date: 28.02.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles.css"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script.js"></script>
<html>
<head>
    <title>Разговоры</title>
</head>
<body onload = "selectTalking()">
<ul id = "menu">
    <li style = "border-top-right-radius: 20px" onclick="redirecting('/abonent')">Абоненты</li>
    <li onclick="redirecting('/city')">Города</li>
    <li onclick="redirecting('/tarif')">Тарифы</li>
    <li onclick="redirecting('/talking')"  style="background-color: saddlebrown">Разговоры</li>
</ul>
<div class = "filtr" style="left: 230px">
    <input class ="inputFiltr" style="left: 175px" placeholder = "Телефон" id ="phoneFiltr">
    <input class ="inputFiltr" style="left: 20px; top:8px" placeholder = "Город" id ="cityFiltr">
    <input class ="inputFiltr" style="left: 30px" placeholder = "Количество минут"  id ="minFiltr">
    <input class ="inputFiltr" style="left: -125px; top:8px" type="date" placeholder = "Дата" id ="dateFiltr">
    <input class ="inputFiltr" style="left: -115px" placeholder = "Время" id ="timeFiltr">
    <input class ="inputFiltr" style="left: 505px; top:-30px" placeholder = "Стоимость" id ="costFiltr">
    <button class = "buttonOkFiltr" style="left: 515px; top: -47px" onclick="filterTalking(document.getElementById('phoneFiltr').value,
                document.getElementById('cityFiltr').value,
                document.getElementById('minFiltr').value,
                document.getElementById('dateFiltr').value,
                document.getElementById('timeFiltr').value,
                document.getElementById('costFiltr').value)">ОК</button>
    <button class = "buttonOkFiltr" style="left: 530px; top:-50px; width: 150px" onclick="filterTalkingAll()">Показать все</button>
</div>
<ul id="buttons" style="top: -520px">
    <li onclick = "showForm('formTalking', false)">Добавить</li>
    <li onclick = "showForm('formTalking', true)">Изменить</li>
    <li  onclick = "deleteTalking()">Удалить</li>
</ul>
<div class="baseForm" id = "formTalking" style="display: none; width: 350px">
    <div class ="titleForm"  id = "titleFormTalking">Добавление разговора</div>
    <div class ="close" onclick = "showForm('formTalking', false)" title = "закрыть"></div>
    <div class = "form" style="height: 250px">
        <div class ="field" style="top: 6px">Телефон:</div>
        <select class ="input" id ="phoneSelect" style="width: 150px; height: 25px; left: 180px; top: 30px"></select>
        <div class ="field"  style="top: 10px">Город:</div>
        <select class ="input"  id ="citySelect" style="width: 150px; height: 25px; left: 180px; top: 60px"></select>
        <div class ="field" style="width: 180px; top: 14px">Количество минут:</div>
        <input class ="input" id ="min" style="width: 150px; left: 180px; top: 90px">
        <div class ="field"  style="top: 18px">Дата:</div>
        <input class ="input" type="date" value = "2018-03-17" id ="dateTalking" style="width: 150px; left: 180px; top: 116px">
        <div class ="field"  style="top: 22px">Время:</div>
        <input class ="input" type="time" value = "00:00" id ="timeTalking" style="width: 150px; left: 180px; top: 146px">
        <div class ="field"  style="top: 26px">Цена:</div>
        <input class ="input" id ="cost" style="width: 150px; left: 180px; top: 176px">
        <button class = "buttonOk" style="left: 140px; top: 210px" onclick="addTalking(document.getElementById('phoneSelect').options[document.getElementById('phoneSelect').selectedIndex].text,
        document.getElementById('citySelect').options[document.getElementById('citySelect').selectedIndex].text,
        document.getElementById('min').value,
        document.getElementById('dateTalking').value,
        document.getElementById('timeTalking').value,
        document.getElementById('cost').value)">ОК</button>
    </div>
</div>
</body>
</html>
