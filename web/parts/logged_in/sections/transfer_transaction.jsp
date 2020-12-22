<div class="bg-light shadow-sm rounded py-4 mb-4">
    <div class="bg-light shadow-sm rounded p-3 p-sm-4 mb-4">
        <form id="form-send-money" method="post" action="${pageContext.request.contextPath}/BankTransactionController">
            <div class="form-group">
                <label for="emailID">Recipient</label>
                <input type="number" name="recipient" value="" class="form-control" data-bv-field="emailid" id="emailID" required placeholder="Enter ID" />
            </div>
            <div class="form-group">
                <label for="youSend">You Send</label>
                <div class="input-group">
                    <div class="input-group-prepend"><span class="input-group-text">$</span></div>
                    <input type="number" name="amount" class="form-control" data-bv-field="youSend" id="youSend" value="1000" placeholder="" />
                </div>
            </div>
            
            <button class="btn btn-primary btn-block">Continue</button>
            
        </form>
    </div>
</div>
