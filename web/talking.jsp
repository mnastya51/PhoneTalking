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
    <title>Разговоры</title>
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
    <li>Изменить</li>
    <li>Удалить</li>
</ul>
<table id = "tableTalking">
    <tr>
        <th>Телефон</th>
        <th>Город</th>
        <th>Количество минут</th>
        <th>Дата</th>
        <th>Время</th>
        <th>Цена</th>
    </tr>
</table>
</body>
</html>
