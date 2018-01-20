<%-- 
    Document   : CreateBidding
    Created on : 18 janv. 2018, 17:04:14
    Author     : yasar
--%>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Creer une enchere</title>
        <link type="text/css" rel="stylesheet" href="resources/css/form.css" />
    </head>
    <body>
        <%@include file="Header.xhtml" %>

        <form method="post" action="enchere">
            <fieldset>
                <legend>Creer une enchere</legend>
                <p>Deposé votre article.</p>

                <label for="title">Titre <span class="requis">*</span></label>
                <input type="text" id="title" name="title" size="20" maxlength="60" required/>
                <br/>

                <label for="desc">Description <span class="requis">*</span></label>
                <input type="text" id="desc" name="desc" size="20" minlength="10" required/>
                <br/>

                <label for="price">Prix de départ <span class="requis">*</span></label>
                <input type="number" min="0" step="0.01" id="price" name="price"  size="20" maxlength="60" required> €
                <br/>

                <label for="time">Choisir la durée de l'enchère<span class="requis">*</span></label>
                <select name="time" id="time">
                    <option value=1>1 jour</option>
                    <option value=2>2 jours</option>
                    <option value=3>3 jours</option>
                    <option value=4>4 jours</option>
                    <option value=5>5 jours</option>
                    <option value=6>6 jours</option>
                    <option value=7>1 semaine</option>
                </select>
                <br/>

                
            <label for="SubCategory">Choisir la sous categorie<span class="requis">*</span></label>
                <select name="sub" id="sub">
                    <c:forEach items="${subcategory}" var="c">
                        <option value=${c.id}>${c.title}</option>
                    </c:forEach>
                </select>
                <br/>

                <input type="submit" value="Valider" />
                <br/>
            </fieldset>
        </form>
                <%@include file="Footer.xhtml" %>
    </body>
</html>