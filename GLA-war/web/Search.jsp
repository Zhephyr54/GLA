<%-- 
    Document   : Search
    Created on : Jan 19, 2018, 8:46:31 AM
    Author     : karim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
        <link type="text/css" rel="stylesheet" href="resources/css/form.css" />
    </head>
    <body>
                <%@include file="Header.xhtml" %>

        <form method="post" action="recherche">
            <fieldset>
                <legend>Rechercher un article</legend>

                <label for="title">Titre</label>
                <input type="text" id="title" name="title" size="20" maxlength="60" required/>
                <br/>

                <label for="Category">Choisir une catégorie<span class="requis">*</span></label>
                <select name="cat" id="cat">
                    <c:forEach items="${category}" var="c">
                        <option value=${c.id}>${c.title}</option>
                    </c:forEach>
                </select>
                <br/>
                
                <label for="SubCategory">Choisir une sous catégorie<span class="requis">*</span></label>
                <select name="sub" id="sub">
                    <c:forEach items="${subcategory}" var="s">
                        <option value=${s.id}>${s.title}</option>
                    </c:forEach>
                </select>
                <br/>

                <input type="submit" value="Rechercher" />
                <br/>
            </fieldset>
        </form>
                <%@include file="Footer.xhtml" %>

    </body>
</html>
