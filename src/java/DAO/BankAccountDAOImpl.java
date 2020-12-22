package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.BankAccount;
import Utils.DBConnection;

public class BankAccountDAOImpl 
{
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public List<BankAccount> get()
    {
        List<BankAccount> list = null;
        BankAccount bankAccount = null;

        try
        {
            list = new ArrayList<BankAccount>();
            
            String sql = "SELECT * FROM bank_accounts";
            connection = DBConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) 
            {
                bankAccount = new BankAccount();
                bankAccount.setBankAccountId(resultSet.getInt("bank_account_id"));
                bankAccount.setCustomerId(resultSet.getInt("customer_id"));
                bankAccount.setCurrentBalance(resultSet.getDouble("current_balance"));
                bankAccount.setCreationDate(resultSet.getString("creation_date"));
                list.add(bankAccount);
            }
        }
        catch(SQLException _e) 
        {
            _e.printStackTrace();
        }
        return list;
    }

    public BankAccount get(Integer _bankAccountId)
    {
        BankAccount bankAccount = null;
        
        try
        {
            String sql = "SELECT * FROM bank_accounts WHERE customer_id = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
           
         
            statement.setInt(1, _bankAccountId);
            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                bankAccount = new BankAccount();
                bankAccount.setBankAccountId(result.getInt("bank_account_id"));
                bankAccount.setCustomerId(result.getInt("customer_id"));
                bankAccount.setCurrentBalance(result.getDouble("current_balance"));
                bankAccount.setCreationDate(result.getString("creation_date"));
                
                System.out.println(bankAccount);
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return bankAccount;
    }
    
    public BankAccount getByAttributeAndPassword(String _attribute, String _value, String _password)
    {
        BankAccount bankAccount = null;
        
        try
        {
            String sql = "SELECT * FROM bank_accounts WHERE " + _attribute + " = ? and password = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, _value);
            statement.setString(2, _password);

            ResultSet result = statement.executeQuery();

            

            if (result.next())
            {
                bankAccount = new BankAccount();
                bankAccount.setBankAccountId(result.getInt("bank_account_id"));
                bankAccount.setCustomerId(result.getInt("customer_id"));
                bankAccount.setCurrentBalance(result.getDouble("current_balance"));
                bankAccount.setCreationDate(result.getString("creation_date"));
                
                System.out.println(bankAccount);
                //bankAccount.setPassword(resultSet.getString("password"));
                //bankAccount.setCurrentBalance(resultSet.getDouble("current_balance"));
            }

            
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return bankAccount;
    }

    public boolean insert(BankAccount _bankAccount)
    {
        boolean flag = false;
        try
        {
            String sql = "INSERT INTO bank_accounts(customer_id, current_balance, creation_date) VALUES"
                    + "('" + _bankAccount.getCustomerId() 
                    + "', '" + _bankAccount.getCurrentBalance() + "', '"
                    + _bankAccount.getCreationDate() + "')";
            
            connection = DBConnection.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            
            flag = true;
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
            String sql = "DELETE FROM bank_accounts where bank_account_id=" + _id;
            
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

    public boolean update(BankAccount _bankAccount)
    {
        boolean flag = false;
        try
        {
            String sql = "UPDATE bank_accounts SET customer_id = '" + _bankAccount.getCustomerId() 
                    + "', current_balance = '" + _bankAccount.getCurrentBalance() 
                    + "', creation_date = '" + _bankAccount.getCreationDate() 
                    + "' where bank_account_id=" + _bankAccount.getBankAccountId();
            
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
    
    public boolean updateCurrentBalance(Integer _id, double _balance)
    {
        boolean flag = false;
        try
        {
            String sql = "UPDATE bank_accounts SET current_balance = current_balance + '" + _balance
                    
                    + "' where bank_account_id=" + _id;
            
            connection = DBConnection.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println(sql);
            flag = true;
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return flag;
    }

}
