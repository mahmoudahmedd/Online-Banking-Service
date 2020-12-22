/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author WIN
 */
public class BankTransaction
{
    private Integer bankTransactionId;
    private double amount;
    private Integer fromAccount;
    private Integer toAccount;
    private String creationDate;
    private boolean toMe;

    

    public void setBankTransactionId(Integer _bankTransactionId)
    {
        this.bankTransactionId = _bankTransactionId;
    }

    public void setAmount(double _amount)
    {
        this.amount = _amount;
    }

    public void setFromAccount(Integer _fromAccount)
    {
        this.fromAccount = _fromAccount;
    }

    public void setToAccount(Integer _toAccount)
    {
        this.toAccount = _toAccount;
    }

    public void setCreationDate(String _creationDate)
    {
        this.creationDate = _creationDate;
    }
    
    public void setToMe(boolean _toMe)
    {
        this.toMe = _toMe;
    }
    
    public Integer getBankTransactionId()
    {
        return this.bankTransactionId;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public Integer getFromAccount()
    {
        return this.fromAccount;
    }

    public Integer getToAccount()
    {
        return this.toAccount;
    }

    public String getCreationDate()
    {
        return this.creationDate;
    }
    
    public boolean getToMe()
    {
        return this.toMe;
    }


    @Override
    public String toString()
    {
        return "BankTransaction{" 
                + "bankTransactionId=" + this.bankTransactionId 
                + ", amount=" + this.amount 
                + ", fromAccount=" + this.fromAccount 
                + ", toAccount=" + this.toAccount 
                + ", creationDate=" + this.creationDate + '}';
    }
    
    
    
    
    
}
