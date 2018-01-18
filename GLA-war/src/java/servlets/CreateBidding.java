/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.dao.ItemDAO;
import db.dao.SubcategoryDAO;
import entity.Item;
import entity.Subcategory;
import entity.User;
import java.io.IOException;
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
 * @author yasar
 */
@WebServlet(name = "CreateBidding", urlPatterns = {"/enchere"})
public class CreateBidding extends HttpServlet {
        public static final String VUE = "/CreateBidding.jsp";
        private List<Subcategory> subcategory;

        @EJB
        SubcategoryDAO s;
        
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             this.subcategory = s.findAll();
             request.setAttribute("subcategory", subcategory);

      
                this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String title = request.getParameter("title");
       String desc = request.getParameter("desc");
       String price = request.getParameter("price");
       String time = request.getParameter("time");
       Item i = new Item(title,desc,Double.parseDouble(price),LocalDateTime.now().plusDays(Integer.parseInt(time)));
       HttpSession session = request.getSession(); 
       User u = (User)session.getAttribute(ATT_SESSION_USER);
       i.setUser(u);
       item.create(i);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
