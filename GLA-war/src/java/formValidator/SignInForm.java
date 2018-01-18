package formValidator;

import entity.User;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class SignInForm {

    private final String CHAMP_EMAIL = "email";
    private final String CHAMP_PASS = "motdepasse";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private User u;

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public User connecterUtilisateur(HttpServletRequest request,User u) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        this.u = u;

        User utilisateur = new User();

        /* Validation du champ email. */
        validationEmail(email);
        utilisateur.setEmail(email);

        /* Validation du champ mot de passe. */
        validationMotDePasse(motDePasse);
        utilisateur.setPassword(motDePasse);

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
          return u;
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail(String email) {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                setErreur(CHAMP_EMAIL, "Merci de saisir une adresse mail valide.");
            }
        } else {
            setErreur(CHAMP_EMAIL, "Merci de saisir une adresse mail.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse(String motDePasse) {
        if (u == null) {
            setErreur(CHAMP_PASS, "Les infos sont erronées.");
        }
        if (motDePasse != null) {
            if (motDePasse.length() < 6) {
                setErreur(CHAMP_PASS, "Le mot de passe doit contenir au moins 6 caractères.");

            }
        } else {
            setErreur(CHAMP_PASS, "Merci de saisir votre mot de passe.");

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
    private String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
