/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
import Utils.DBConnection;

public class CustomerDAOImpl {

    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public List<Customer> get()
    {
        List<Customer> list = null;
        Customer customer = null;

        try
        {
            list = new ArrayList<Customer>();
            
            String sql = "SELECT * FROM customers";
            connection = DBConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) 
            {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                list.add(customer);
            }
        }
        catch(SQLException _e) 
        {
            _e.printStackTrace();
        }
        return list;
    }

    
    public Customer get(Integer _customerId)
    {
        Customer customer = null;
        
        try
        {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
           
         
            statement.setInt(1, _customerId);
            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                customer = new Customer();
                customer.setCustomerId(result.getInt("customer_id"));
                customer.setEmail(result.getString("email"));
                customer.setPassword(result.getString("password"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setPhoneNumber(result.getString("phone_number"));
                
                System.out.println(customer);
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return customer;
    }
    
    public Customer getByEmail(String _email)
    {
        Customer customer = null;
        
        try
        {
            String sql = "SELECT * FROM customers WHERE email = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, _email);

            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                customer = new Customer();
                customer.setCustomerId(result.getInt("customer_id"));
                customer.setEmail(result.getString("email"));
                customer.setPassword(result.getString("password"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setPhoneNumber(result.getString("phone_number"));
                
                System.out.println(customer);
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return customer;
    }
    
    public Customer getByAttributeAndPassword(String _attribute, String _value, String _password)
    {
        Customer customer = null;
        
        try
        {
            String sql = "SELECT * FROM customers WHERE " + _attribute + " = ? and password = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
           
         
            statement.setString(1, _value);
            statement.setString(2, _password);

            ResultSet result = statement.executeQuery();

            

            if (result.next())
            {
                customer = new Customer();
                customer.setCustomerId(result.getInt("customer_id"));
                customer.setEmail(result.getString("email"));
                customer.setPassword(result.getString("password"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setPhoneNumber(result.getString("phone_number"));
                
                System.out.println(customer);
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return customer;
    }

    public int insert(Customer _customer)
    {
        int flag = 0;
        try
        {
            String sql = "INSERT INTO customers(phone_number, email, password, name, address) VALUES(?,?,?,?,?) ";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, _customer.getPhoneNumber());
            statement.setString(2, _customer.getEmail());
            statement.setString(3, _customer.getPassword());
            statement.setString(4, _customer.getName());
            statement.setString(5, _customer.getAddress());
            

            flag = statement.executeUpdate();
        }
        catch(SQLException _ex) 
        {
            _ex.printStackTrace();
        }
        
        return flag;
    }

    public boolean delete(int _id)
    {
        boolean flag = false;
        try
        {
            String sql = "DELETE FROM customers where customer_id=" + _id;
            
            connection = DBConnection.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            
            flag = true;
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return flag;
    }

    public boolean update(Customer _customer)
    {
        boolean flag = false;
        try
        {
            String sql = "UPDATE customers SET name = '" + _customer.getName() + "', "
                    + "address = '" + _customer.getAddress() + "', phone_number = '" + _customer.getPhoneNumber() + "' where customer_id=" + _customer.getCustomerId();
            
            connection = DBConnection.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            
            flag = true;
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return flag;
    }

}
