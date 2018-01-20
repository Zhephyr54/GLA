/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.dao.CategoryDAO;
import db.dao.ItemDAO;
import db.dao.SubcategoryDAO;
import entity.Category;
import entity.Item;
import entity.Subcategory;
import entity.User;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlets.SignIn.ATT_SESSION_USER;

/**
 *
 * @author karim
 */
@WebServlet(name = "Search", urlPatterns = {"/recherche"})
public class Search extends HttpServlet {

    public static final String VUE = "/Search.jsp";
    public static final String URL_REDIRECTION = "/SearchResult.jsp";
    private List<Category> category;
    private List<Subcategory> subcategory;
    private List<Item> items;

    @EJB
    SubcategoryDAO s;

    @EJB
    CategoryDAO c;
    
    @EJB
    ItemDAO item;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.subcategory = s.findAll();
        this.category = c.findAll();
        
        request.setAttribute("subcategory", subcategory);
        request.setAttribute("category", category);
        
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String idCategory = request.getParameter("cat");
        String idSubCategory = request.getParameter("sub");
        
        //Recherche par titre, category, sous-category
        items  = item.findNotOver(title, Integer.parseInt(idCategory), Integer.parseInt(idSubCategory));
        //Recherche par titre
        //items  = item.findNotOverByTitle(title);
        request.setAttribute("items", items);
        this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward(request, response);
        
    }

}
