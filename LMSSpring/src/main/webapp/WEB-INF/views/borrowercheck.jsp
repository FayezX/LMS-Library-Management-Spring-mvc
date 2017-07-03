<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">FOR AUTHENTICATION</h4>
</div>

<form action="checkBorrowerId" method="post">
      <div class="modal-body">
      <h2>Please Enter Borrower ID</h2>
		<input type ="text" name = "borrowerId" placeholder="Enter"><br />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="Submit" class="btn btn-primary">Submit</button>
   </div>
</form>
   
  