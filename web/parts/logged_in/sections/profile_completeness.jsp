<div class="bg-light shadow-sm rounded p-4 mb-4">
   <h3 class="text-5 font-weight-400 d-flex align-items-center mb-3">Profile Completeness <span class="bg-light-4 text-success rounded px-2 py-1 font-weight-400 text-2 ml-2"><% if (bankAccount != null) { %>100<% } else { %>50<% } %>%</span></h3>
   <div class="row profile-completeness">
      <div class="col-sm-6 col-md-6 mb-4 mb-md-0">
         <div class="border rounded p-3 text-center">
            <span class="d-block text-10 text-light mt-2 mb-3"><i class="fas fa-user"></i></span> <span class="text-5 d-block text-success mt-4 mb-3"><i class="fas fa-check-circle"></i></span>
            <p class="mb-0">Customer Account Created</p>
         </div>
      </div>
      <div class="col-sm-6 col-md-6">
         <div class="border rounded p-3 text-center">
            <span class="d-block text-10 text-light mt-2 mb-3"><i class="fas fa-university"></i></span> <span class="text-5 d-block <% if (bankAccount != null) { %>text-success<% } else { %>text-light<% } %> mt-4 mb-3"><% if (bankAccount != null) { %><i class="fas fa-check-circle "></i><% } else { %><i class="far fa-circle "></i><% } %></span>
            <% if (bankAccount != null) { %>
            <p class="mb-0">Bank Account Created</p>
            <% } else { %>
            <p class="mb-0"><a class="btn-link stretched-link" href="BankAccountController?customer_id=<% out.print(customer.getCustomerId()); %>">Add Bank Account</a></p>
            <% } %>
         </div>
      </div>
   </div>
</div>