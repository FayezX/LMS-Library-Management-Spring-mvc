<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>

<% 
	/*
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	System.out.println(branchId);
	LibrarianService service = new LibrarianService();
	LibraryBranch lib = service.getBranchsByPk(branchId);
	*/
%>

<div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editing Branch</h4>
</div>

<form action="changeBranch" method="post">
      <div class="modal-body">
      <h2>Enter New Branch Info</h2>
		<input type ="text" name = "name" placeholder="Enter Name"><br />
		<input type ="text" name = "address" placeholder="Enter Address"><br />
		<input type = "hidden" name = "branchId" value="${libraryBranch.branchId}">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="Submit" class="btn btn-primary">Submit</button>
   </div>
</form>
   
  