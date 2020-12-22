/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import DAO.BankAccountDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import Models.BankTransaction;
import DAO.BankTransactionDAO;
import Models.BankAccount;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WIN
 */
@WebServlet(name = "BankTransactionController", urlPatterns = {"/BankTransactionController"})
public class BankTransactionController extends HttpServlet
{
    RequestDispatcher dispatcher = null;

    BankTransactionDAO bankTransactionDAOImpl = null;
    BankTransaction bankTransaction;
    
    public BankTransactionController()
    {
        this.bankTransaction = new BankTransaction();
        this.bankTransactionDAOImpl = new BankTransactionDAO();
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
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        
        HttpSession session = request.getSession(false);
        if(session.getAttribute("customer_id") != null)
        {
            Integer customerId = Integer.parseInt(session.getAttribute("customer_id").toString());
            BankAccount bankAccount = new BankAccountController().getSingleBankAccount(customerId);
            this.bankTransaction = this.bankTransactionDAOImpl.get(id);
            
            String curDay = java.time.LocalDate.now().toString().split("-")[2];
            String date = this.bankTransaction.getCreationDate().split(" ")[0];
            String day = date.split("-")[2];
    
            if(this.bankTransaction.getFromAccount() == bankAccount.getBankAccountId() && 
               curDay.equals(day) && 
               !bankTransaction.getToMe())
            {
                BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
                bankAccountDAOImpl.updateCurrentBalance(this.bankTransaction.getToAccount(), -amount);
                bankAccountDAOImpl.updateCurrentBalance(this.bankTransaction.getFromAccount(), amount);
                
                this.bankTransactionDAOImpl.delete(id);
                
                request.setAttribute("MSG", "Done");
                dispatcher = request.getRequestDispatcher("/transactions.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        
        
        request.setAttribute("MSG", "Error");
        dispatcher = request.getRequestDispatcher("/transactions.jsp");
        dispatcher.forward(request, response);
    }
    
    public List<BankTransaction> getAllBankTransactions(Integer _i)
    {
        List<BankTransaction> list = this.bankTransactionDAOImpl.getById(_i);
        
        return list;
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
        response.setContentType("text/html;charset=UTF-8");
        
        Integer recipient = Integer.parseInt(request.getParameter("recipient"));
        Integer amount = Integer.parseInt(request.getParameter("amount"));

        HttpSession session = request.getSession(false);
        if(session.getAttribute("customer_id") != null)
        {
            Integer customerId = Integer.parseInt(session.getAttribute("customer_id").toString());
            BankAccount bankAccount = new BankAccountController().getSingleBankAccount(customerId);
            
            if(amount <= bankAccount.getCurrentBalance() && recipient != bankAccount.getBankAccountId())
            {
                BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
                
                bankAccount.setCurrentBalance(bankAccount.getCurrentBalance() - amount);
                
                bankAccountDAOImpl.update(bankAccount);
                bankAccountDAOImpl.updateCurrentBalance(recipient, amount);

                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                
                this.bankTransaction = new BankTransaction();
                this.bankTransaction.setAmount(amount);
                this.bankTransaction.setFromAccount(bankAccount.getBankAccountId());
                this.bankTransaction.setToAccount(recipient);
                this.bankTransaction.setCreationDate(formatter.format(date));
                if(this.bankTransactionDAOImpl.insert(bankTransaction) == 1)
                {
                    request.setAttribute("MSG", "Done");
                    dispatcher = request.getRequestDispatcher("/transactions.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                
                bankAccount.setCurrentBalance(bankAccount.getCurrentBalance() + amount);
                
                bankAccountDAOImpl.update(bankAccount);
                bankAccountDAOImpl.updateCurrentBalance(recipient, -amount);
            }
            
        }
        
        
        request.setAttribute("MSG", "Error");
        dispatcher = request.getRequestDispatcher("/transactions.jsp");
        dispatcher.forward(request, response);
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
