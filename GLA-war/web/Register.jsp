<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
    </head>
    <body>
                            <%@include file="Header.xhtml" %>

        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>

                <label for="email">Adresse email(*)</label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe(*)</label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe(*)</label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />

                <label for="lastname">Nom d'utilisateur(*)</label>
                <input type="text" id="lastname" name="lastname" value="<c:out value="${utilisateur.lastname}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['lastname']}</span>
                <br />
                
                <label for="firstname">Prénom d'utilisateur(*)</label>
                <input type="text" id="nom" name="firstname" value="<c:out value="${utilisateur.firstname}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['firstname']}</span>
                <br />
                
                <label for="adr">Adresse</label>
                <input type="text" id="adr" name="adr" size="20" maxlength="20" />
                <br />
                
                <input type="submit" class="btn btn-primary" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
                    <%@include file="Footer.xhtml" %>
    </body>
</html>