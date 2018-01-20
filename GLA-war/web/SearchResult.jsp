<%-- 
    Document   : SearchResult
    Created on : Jan 19, 2018, 9:30:32 AM
    Author     : karim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        <h1>Resultats de la recherche</h1>
        <c:forEach items="${items}" var="c">
            <hr>
            <a href="faces/item.xhtml?id=${c.id}">${c.title}</a>
            <div>Description : ${c.description}</div>
            <div>Subcategory : ${c.subcategory.title}</div>
            <hr>
        </c:forEach>
    </body>
</html>
