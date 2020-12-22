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

import Models.BankTransaction;
import Utils.DBConnection;

public class BankTransactionDAO 
{
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public List<BankTransaction> getById(Integer _id)
    {
        List<BankTransaction> list = null;
        BankTransaction bankTransaction = null;

        try
        {
            list = new ArrayList<BankTransaction>();
            
            String sql = "SELECT * " +
                        "FROM bank_transactions t " +
                        "JOIN bank_accounts t1 ON t1.bank_account_id = t.from_account " +
                        "JOIN bank_accounts t2 ON t2.bank_account_id = t.to_account WHERE t.from_account = " + _id + 
                        " or t.to_account = " + _id + " ORDER BY t.creation_date DESC";
            connection = DBConnection.openConnection();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next())
            {
                bankTransaction = new BankTransaction();
                bankTransaction.setBankTransactionId(resultSet.getInt("bank_transaction_id"));
                bankTransaction.setAmount(resultSet.getDouble("amount"));
                bankTransaction.setFromAccount(resultSet.getInt("from_account"));
                bankTransaction.setToAccount(resultSet.getInt("to_account"));
                bankTransaction.setCreationDate(resultSet.getString("creation_date"));

                if(resultSet.getInt("to_account") == _id)
                {
                    bankTransaction.setToMe(true);
                }
                
                list.add(bankTransaction);
            }
        }
        catch(SQLException _e) 
        {
            _e.printStackTrace();
        }
        return list;
    }

    public BankTransaction get(Integer _bankTransactionId)
    {
        BankTransaction bankTransaction = null;
        
        try
        {
            String sql = "SELECT * FROM bank_transactions WHERE bank_transaction_id = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
           
         
            statement.setInt(1, _bankTransactionId);
            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                bankTransaction = new BankTransaction();
                bankTransaction.setBankTransactionId(result.getInt("bank_transaction_id"));
                bankTransaction.setAmount(result.getDouble("amount"));
                bankTransaction.setFromAccount(result.getInt("from_account"));
                bankTransaction.setToAccount(result.getInt("to_account"));
                bankTransaction.setCreationDate(result.getString("creation_date"));
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return bankTransaction;
    }
    
    public BankTransaction getByAttribute(String _attribute, String _value)
    {
        BankTransaction bankTransaction = null;
        
        try
        {
            String sql = "SELECT * FROM bank_transactions WHERE " + _attribute + " = ?";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, _value);

            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                bankTransaction = new BankTransaction();
                bankTransaction.setBankTransactionId(resultSet.getInt("bank_transaction_id"));
                bankTransaction.setAmount(resultSet.getDouble("amount"));
                bankTransaction.setFromAccount(resultSet.getInt("from_account"));
                bankTransaction.setToAccount(resultSet.getInt("to_account"));
                bankTransaction.setCreationDate(resultSet.getString("creation_date"));
            }
        }
        catch(SQLException _e)
        {
            _e.printStackTrace();
        }
        
        return bankTransaction;
    }

    public int insert(BankTransaction _bankTransaction)
    {
        int flag = 0;
        
        try
        {
            String sql = "INSERT INTO bank_transactions(amount, from_account, to_account, creation_date) VALUES(?,?,?,?) ";
            connection = DBConnection.openConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, _bankTransaction.getAmount());
            statement.setInt(2, _bankTransaction.getFromAccount());
            statement.setInt(3, _bankTransaction.getToAccount());
            statement.setString(4, _bankTransaction.getCreationDate());
            

            flag = statement.executeUpdate();
        }
        catch(SQLException _ex) 
        {
            _ex.printStackTrace();
        }
        
        return flag;
    }

    public boolean delete(Integer _id)
    {
        boolean flag = false;
        try
        {
            String sql = "DELETE FROM bank_transactions where bank_transaction_id=" + _id;
            
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
