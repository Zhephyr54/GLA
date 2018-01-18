package servlets;

import db.dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import formValidator.SignInForm;

public class SignIn extends HttpServlet {

    @EJB
    UserDAO ud;

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE = "/SignIn.jsp";
    public static final String URL_REDIRECTION = "/GLA-war";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        SignInForm form = new SignInForm();

        /* Traitement de la requête et récupération du bean en résultant */
        User utilisateur = form.connecterUtilisateur(request,ud.testCo(request.getParameter("email"),request.getParameter("motdepasse")));

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if (form.getErreurs().isEmpty()) {
            session.setAttribute(ATT_SESSION_USER, utilisateur);
            /* Redirection vers la page de Connexion */
            response.sendRedirect(URL_REDIRECTION);

        } else {
            session.setAttribute(ATT_SESSION_USER, null);
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur);

        if (!form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}
