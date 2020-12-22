<%
    String pageTitle = "Dashboard";
    
    Controllers.CustomerController customerController = new Controllers.CustomerController();
    Models.Customer customer = customerController.getSingleCustomer(request, response);
    
    if(customer == null)
    {
        response.sendRedirect("login.html");
        return;
    }
    
    Controllers.BankAccountController bankAccountController = new Controllers.BankAccountController();
    Models.BankAccount bankAccount = bankAccountController.getSingleBankAccount(customer.getCustomerId());
    
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
                <!-- Profile Completeness -->
                <%@ include file="parts/logged_in/sections/profile_completeness.jsp" %>
                <!-- Profile Completeness End -->
                
                <!-- View all Link -->
                <%@ include file="parts/logged_in/sections/view_all_link.jsp" %>
                <!-- View all Link End -->
            </div>
            <!-- Middle Panel End -->
        </div>
    </div>
</div>
<!-- Content end -->

<!-- Footer -->
<%@ include file="parts/logged_in/footer.jsp" %>
<!-- Footer end -->