<%--
  Created by IntelliJ IDEA.
  User: matek3022
  Date: 16.02.2018
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles.css"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script.js"></script>
<html>
  <head>
    <title>Телефонные переговоры</title>
  </head>
  <body>
  <img class = "impPhone" style="left: 10%">
  <img class ="impPhone" style="right: 10%">
  <div class = "mainPage">
    <div class="titleMainPage">Телефонные переговоры</div>
    <div class = "page" onclick="redirecting('/abonent')">Абоненты</div>
    <div class = "page" onclick="redirecting('/city')">Города</div>
    <div class = "page" onclick="redirecting('/tarif')">Тарифы</div>
    <div class = "page" onclick="redirecting('/talking')">Разговоры</div>
  </div>
  </body>
</html>
