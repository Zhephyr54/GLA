package formValidator;

import entity.Address;
import entity.User;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class CreateAccountForm {

    private final String CHAMP_EMAIL = "email";
    private final String CHAMP_PASS = "motdepasse";
    private final String CHAMP_CONF = "confirmation";
    private final String CHAMP_NOM = "lastname";
    private final String CHAMP_PRENOM = "firstname";
    private final String CHAMP_ADR = "adr";
    private boolean ok, emailexist;
    private Address address;

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public User inscrireUtilisateur(HttpServletRequest request, boolean emailexist) {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String adr = getValeurChamp(request, CHAMP_ADR);
        this.emailexist = emailexist;

        User utilisateur = new User();

        validationEmail(email);
        utilisateur.setEmail(email);

        validationMotsDePasse(motDePasse, confirmation);
        utilisateur.setPassword(motDePasse);

        validationNom(nom);
        utilisateur.setLastname(nom);

        validationPrenom(prenom);
        utilisateur.setFirstname(prenom);

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'inscription.";
            if (!adr.isEmpty()) {
                this.address = new Address(adr);
                utilisateur.addAdr(address);
            }
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

    private void validationEmail(String email) {
        if (emailexist) {
            setErreur(CHAMP_EMAIL, "Cette email est deja utilise.");
        }
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                setErreur(CHAMP_EMAIL, "Merci de saisir une adresse mail valide.");
            }
        } else {
            setErreur(CHAMP_EMAIL, "Merci de saisir une adresse mail.");
        }
    }

    private void validationMotsDePasse(String motDePasse, String confirmation) {
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                setErreur(CHAMP_PASS, "Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
                setErreur(CHAMP_CONF, null);
            } else if (motDePasse.length() < 6) {
                setErreur(CHAMP_PASS, "Les mots de passe doivent contenir au moins 6 caractères.");
                setErreur(CHAMP_CONF, null);
            }
        } else {
            setErreur(CHAMP_PASS, "Merci de saisir et confirmer votre mot de passe.");
            setErreur(CHAMP_CONF, null);
        }
    }

    private void validationNom(String nom) {
        if (nom != null) {
            if (nom.length() < 3) {
                setErreur(CHAMP_NOM, "Le nom d'utilisateur doit contenir au moins 3 caractères.");
            }
        } else {
            setErreur(CHAMP_NOM, "Merci de saisir votre nom.");
        }

    }

    private void validationPrenom(String nom) {
        if (nom != null) {
            if (nom.length() < 3) {
                setErreur(CHAMP_PRENOM, "Le prenom d'utilisateur doit contenir au moins 3 caractères.");
            }
        } else {
            setErreur(CHAMP_PRENOM, "Merci de saisir votre prenom.");

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
            return valeur.trim();
        }
    }
}
