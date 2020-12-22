/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;



import DAO.BankAccountDAOImpl;
import Models.BankAccount;
import Utils.MD5;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author WIN
 */
@WebServlet(name = "BankAccountController", urlPatterns = {"/BankAccountController"})
public class BankAccountController extends HttpServlet
{
    BankAccount bankAccount;
    BankAccountDAOImpl bankAccountDAOImpl = null;

    public BankAccountController()
    {
        this.bankAccount = new BankAccount();
        this.bankAccountDAOImpl = new BankAccountDAOImpl();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        String sessionCustomerId = session.getAttribute("customer_id").toString();
        
        String customerId = request.getParameter("customer_id");
        Integer intCustomerId = Integer.parseInt(customerId);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        
        this.bankAccount = bankAccountDAOImpl.get(intCustomerId);
        
        if(this.bankAccount == null && sessionCustomerId.equals(customerId))
        {
            this.bankAccount = new BankAccount();
            this.bankAccount.setCustomerId(intCustomerId);
            this.bankAccount.setCurrentBalance(1000);
            this.bankAccount.setCreationDate(formatter.format(date));

            this.bankAccountDAOImpl.insert(this.bankAccount);
        }
        
        
        response.sendRedirect("dashboard.jsp");
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
            throws ServletException, IOException
    {
           
    }
    
    public BankAccount getSingleBankAccount(Integer _customerId)
    {
        this.bankAccount = bankAccountDAOImpl.get(_customerId);

        return this.bankAccount;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
