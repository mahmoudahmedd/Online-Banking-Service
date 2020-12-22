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

import DAO.CustomerDAOImpl;
import Models.Customer;
import Utils.MD5;

/**
 *
 * @author WIN
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet 
{
    CustomerDAOImpl customerDAOImpl = null;
    Customer customer;
    
    public CustomerController()
    {
        this.customer = new Customer();
        this.customerDAOImpl = new CustomerDAOImpl();
    }
    
    private void login(HttpServletRequest _request, HttpServletResponse _response, String _email, String _password, String _remember)
            throws ServletException, IOException
    {
        String attribute = "";
        
        if(_email.chars().allMatch(Character::isDigit))
            attribute = "customer_id";
        else
            attribute = "email";
        
        _password = MD5.getMd5(_password);
        this.customer = this.customerDAOImpl.getByAttributeAndPassword(attribute, _email, _password);
        
        if(_email.length() == 0 || _password.length() == 0 || this.customer == null)
        {
            _response.sendRedirect("login.html");
        }
        else
        {
            HttpSession session = _request.getSession();  
            session.setAttribute("customer_id", this.customer.getCustomerId());

            if(_remember != null)
            {
                // Creating two cookies
                Cookie emailCookie = new Cookie("customer_id", this.customer.getCustomerId().toString());
                Cookie passwordCookie = new Cookie("password", this.customer.getPassword());

                // Adding the cookies to response header
                _response.addCookie(emailCookie);
                _response.addCookie(passwordCookie);
            }

            _response.sendRedirect("dashboard.jsp");
        }
    }
    
    
    public Customer getSingleCustomer(HttpServletRequest _request, HttpServletResponse _response)
    {
        Customer customer = null;
        try
        {
            HttpSession session = _request.getSession(false);
            Cookie[] cookies = _request.getCookies();

            if(session.getAttribute("customer_id") == null)
            {
                if(cookies != null && cookies.length != 0)
                {
                    String customerId = null;
                    String password = null;
                    for(Cookie cookie : cookies)
                    {
                        if(cookie.getName().equals("customer_id"))
                            customerId = cookie.getValue();

                        if(cookie.getName().equals("password"))
                            password = cookie.getValue();
                    }

                    if(customerId == null || password == null)
                    {
                        return customer;
                    }
                    
                    customer = this.customerDAOImpl.getByAttributeAndPassword("customer_id", customerId, password);
                    return customer;
                }
                return customer;
            }

            Integer customerId = Integer.parseInt(session.getAttribute("customer_id").toString());
            customer = this.customerDAOImpl.get(customerId);
        }
        catch(Exception e)
        {
            return customer;
        }
        
        return customer;
    }
    
    private void signup(HttpServletRequest _request, 
            HttpServletResponse _response,
            String _name, 
            String _phoneNumber,
            String _email, 
            String _password, 
            String _address)
            throws ServletException, IOException
    {
        _password = MD5.getMd5(_password);
        
        this.customer = new Customer();
        this.customer.setName(_name);
        this.customer.setPhoneNumber(_phoneNumber);
        this.customer.setEmail(_email);
        this.customer.setPassword(_password);
        this.customer.setAddress(_address);
        
        Customer temp = this.customerDAOImpl.getByEmail(_email);
        
        if(temp == null)
        {
            int flag = this.customerDAOImpl.insert(this.customer);
            temp = this.customerDAOImpl.getByEmail(_email);
            
            if(flag == 1)
            {
                HttpSession session = _request.getSession();  
                session.setAttribute("customer_id", temp.getCustomerId());

                // Creating two cookies
                Cookie emailCookie = new Cookie("customer_id", temp.getCustomerId().toString());
                Cookie passwordCookie = new Cookie("password", temp.getPassword());

                // Adding the cookies to response header
                _response.addCookie(emailCookie);
                _response.addCookie(passwordCookie);

                _response.sendRedirect("dashboard.jsp");
            }
            else
            {
                _response.sendRedirect("signup.html");
            }
        }
        else
        {
            _response.sendRedirect("signup.html");
        }
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
        String type = request.getParameter("type");
        if(type.equals("login"))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            this.login(request, response, email, password, remember);
        }
        else
        {
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phone_number");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            this.signup(request, response, name, phoneNumber, email, password, address);
        }
        
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
    }
    // </editor-fold>

}
