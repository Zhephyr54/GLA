package servlets;

import db.dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.CreateAccountForm;



public class CreateAccount extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/Register.jsp";
    public static final String URL_REDIRECTION = "connexion";

    
    @EJB
    UserDAO ud;
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Préparation de l'objet formulaire */
        CreateAccountForm form = new CreateAccountForm();
        
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        User utilisateur = form.inscrireUtilisateur( request,ud.findByEmail(request.getParameter("email")) );
        if(form.isOk()){
            ud.create(utilisateur);
                /* Redirection vers la page de Connexion */
            response.sendRedirect( URL_REDIRECTION );
        }

		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
	
        if(!form.isOk())
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}