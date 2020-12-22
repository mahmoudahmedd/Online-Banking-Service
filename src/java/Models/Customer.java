package Models;

public class Customer
{
    private Integer customerId;
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;

    public void setCustomerId(Integer _customerId)
    {
        this.customerId = _customerId;
    }
    
    public void setEmail(String _email)
    {
        this.email = _email;
    }

    public void setPassword(String _password)
    {
        this.password = _password;
    }

    public void setName(String _name)
    {
        this.name = _name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNumber(String _phoneNumber)
    {
        this.phoneNumber = _phoneNumber;
    }

    public Integer getCustomerId()
    {
        return this.customerId;
    }
    
    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "customerId=" + this.customerId 
                + ", email=" + this.email 
                + ", password=" + this.password 
                + ", name=" + this.name 
                + ", address=" + this.address 
                + ", phoneNumber=" + this.phoneNumber + '}';
    }

    

}
