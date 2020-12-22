<% if (bankAccount != null) { %>
  <div class="bg-light shadow-sm rounded text-center p-3 mb-4">
    <div class="text-17 text-light my-3"><i class="fas fa-wallet"></i></div>
    <h3 class="text-9 font-weight-400">$<% out.print(bankAccount.getCurrentBalance()); %></h3>
    <p class="mb-2 text-muted opacity-8">Available Balance</p>
    <hr class="mx-n3">
    <div><center><a href="transactions.jsp" class="btn-link mr-auto">Send</a></center></div>
  </div>
<% } %>
