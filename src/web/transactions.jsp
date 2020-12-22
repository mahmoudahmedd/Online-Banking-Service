<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    String pageTitle = "Transactions";
    Controllers.CustomerController customerController = new Controllers.CustomerController();
    Models.Customer customer = customerController.getSingleCustomer(request, response);
    
    
    if(customer == null)
    {
        response.sendRedirect("login.html");
        return;
    }
    
    Controllers.BankAccountController bankAccountController = new Controllers.BankAccountController();
    Models.BankAccount bankAccount = bankAccountController.getSingleBankAccount(customer.getCustomerId());
    
    if(bankAccount == null)
    {
        response.sendRedirect("dashboard.jsp");
        return;
    }
    
    Controllers.BankTransactionController bankTransactionController = new Controllers.BankTransactionController();
    List<Models.BankTransaction> bankTransaction = bankTransactionController.getAllBankTransactions(bankAccount.getBankAccountId());
    
%>

<!-- Header -->
<%@ include file="parts/logged_in/header.jsp" %>
<!-- Header End -->

<!-- Content -->
<div id="content" class="py-4">
    <div class="container">
        <div class="row">
            
            <!-- Left Panel -->
            <aside class="col-lg-3">
                <!-- Profile Details -->
                <%@ include file="parts/logged_in/sections/profile_details.jsp" %>
                <!-- Profile Details End -->
                
                <!-- Available Balance -->
                <%@ include file="parts/logged_in/sections/balance.jsp" %>
                <!-- Available Balance End -->
            </aside>
            <!-- Left Panel End -->

            <!-- Middle Panel -->
            <div class="col-lg-9">
                <div class="bg-light shadow-sm rounded py-4 mb-4">
                    <center>
                        <span data-toggle="tooltip" data-original-title="Completed">${MSG}</span>
                    </center>
                </div>
                <h2 class="font-weight-400 mb-3">Transfer Transaction</h2>
                <!-- Transfer Transaction -->
                <%@ include file="parts/logged_in/sections/transfer_transaction.jsp" %>
                <!-- Transfer Transaction End -->
                    
                <h2 class="font-weight-400 mb-3">Transactions</h2>
                
                <!-- All Transactions -->
                <%@ include file="parts/logged_in/sections/transaction_list.jsp" %>
                <!-- All Transactions End -->
            </div>
            <!-- Middle End -->
        </div>
    </div>
</div>
<!-- Content end -->

  
<!-- Footer -->
<%@ include file="parts/logged_in/footer.jsp" %>
<!-- Footer end -->