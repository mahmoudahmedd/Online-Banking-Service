package Models;


public class BankAccount 
{
    private Integer bankAccountId;
    private Integer customerId;
    private double currentBalance;
    private String creationDate;

    public void setBankAccountId(Integer _bankAccountId)
    {
        this.bankAccountId = _bankAccountId;
    }

    public void setCustomerId(Integer _customerId)
    {
        this.customerId = _customerId;
    }

    public void setCurrentBalance(double _currentBalance)
    {
        this.currentBalance = _currentBalance;
    }

    public void setCreationDate(String _creationDate)
    {
        this.creationDate = _creationDate;
    }

    public Integer getBankAccountId()
    {
        return this.bankAccountId;
    }

    public Integer getCustomerId()
    {
        return this.customerId;
    }

    public double getCurrentBalance()
    {
        return this.currentBalance;
    }

    public String getCreationDate()
    {
        return this.creationDate;
    }

    @Override
    public String toString()
    {
        return "BankAccount{" 
                + "bankAccountId=" + this.bankAccountId 
                + ", customerId=" + this.customerId 
                + ", currentBalance=" + this.currentBalance 
                + ", creationDate=" + this.creationDate + '}';
    }
}
