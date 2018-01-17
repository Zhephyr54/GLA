package login;

import entity.User;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class SignInForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "lastname";
    private static final String CHAMP_PRENOM = "firstname";
    private boolean ok;

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public User inscrireUtilisateur(HttpServletRequest request) {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);

        User utilisateur = new User();

        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);

        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        utilisateur.setPassword(motDePasse);

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setLastname(nom);

        try {
            validationPrenom(prenom);
        } catch (Exception e) {
            setErreur(CHAMP_PRENOM, e.getMessage());
        }
        utilisateur.setFirstname(prenom);

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'inscription.";
            ok = true;
        } else {
            resultat = "Échec de l'inscription.";
            ok = false;
        }

        return utilisateur;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.length() < 6) {
                throw new Exception("Les mots de passe doivent contenir au moins 6 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    private void validationNom(String nom) throws Exception {
        if (nom != null) {
            if (nom.length() < 3) {
                throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre nom.");
        }

    }

    private void validationPrenom(String nom) throws Exception {
       if (nom != null) {
            if (nom.length() < 3) {
                throw new Exception("Le prenom d'utilisateur doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre prenom.");
        }
    }

    /*
 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
 * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
