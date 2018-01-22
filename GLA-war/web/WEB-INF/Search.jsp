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
        <style>
            .optionGroup {
                font-weight: bold;
                font-style: italic;
            }
            
            .optionChild {
                padding-left: 15px;
            }
        </style>
    </head>
    <body>
                <%@include file="/WEB-INF/header.xhtml" %>

        <form method="post" action="recherche">
            <fieldset>
                <legend>Rechercher un article</legend>

                <label for="title">Titre</label>
                <input type="text" id="title" name="title" size="20" maxlength="60"/>
                <br/>

                <label for="cat">Choisir une catégorie<span class="requis">*</span></label>
                <select name="cat" id="cat">
                    <c:forEach items="${category}" var="c">
                        <option class="optionGroup" value=${c.title}>${c.title}</option>
                        
                        <c:forEach items="${subcategory.get(c.id-1)}" var="s">
                            <option class="optionChild" value=${s.title}>${s.title}</option>
                        </c:forEach>
                        
                    </c:forEach>
                </select>
                <br/>
                

                <input type="submit"  class="btn btn-primary" value="Rechercher" />
                <br/>
            </fieldset>
        </form>
                <%@include file="/WEB-INF/footer.xhtml" %>

    </body>
</html>
