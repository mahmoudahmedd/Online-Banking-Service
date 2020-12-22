<div class="transaction-list">
<%
String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
Iterator<Models.BankTransaction> iterator = bankTransaction.iterator();

while(iterator.hasNext())
{
    Models.BankTransaction bankTransactionDetails = iterator.next();
    String date = bankTransactionDetails.getCreationDate().split(" ")[0];
    String day = date.split("-")[2];
    String month = months[Integer.parseInt(date.split("-")[1]) - 1];
    
    String curDay = java.time.LocalDate.now().toString().split("-")[2];  
%>
    <div class="transaction-item px-4 py-3" data-toggle="modal" data-target="#transaction-detail">
        <div class="row align-items-center flex-row">
            <div class="col-2 col-sm-1 text-center"><span class="d-block text-4 font-weight-300"><%=day%></span> <span class="d-block text-1 font-weight-300 text-uppercase"><%=month%></span></div>
            
            <div class="col col-sm-7 d-none d-sm-block text-center text-3">
                <% if (curDay.equals(day) && !bankTransactionDetails.getToMe()) { %>
                <a href="BankTransactionController?id=<%=bankTransactionDetails.getBankTransactionId()%>&amount=<%=bankTransactionDetails.getAmount()%>"><span class="text-danger" data-toggle="tooltip" data-original-title="Cancelled"><i class="fas fa-times-circle"></i> Cancel</span></a>
                
                <% } else { %>
                <span class="text-success" data-toggle="tooltip" data-original-title="Completed"><i class="fas fa-check-circle"></i></span>
                <% } %>
                
            </div>
            
            <div class="col-3 col-sm-4 text-right text-4"><span class="text-nowrap"><% if (bankTransactionDetails.getToMe()) { %>+<% } else { %>-<% } %> $<%=bankTransactionDetails.getAmount()%></span> <span class="text-2 text-uppercase">(USD)</span></div>
        </div>
    </div>
<%
}
%>
</div>