<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<% 
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	System.out.println(bookId);
	AdminService service = new AdminService();
	Book book = service.getBooksByPk(bookId);
%>

<div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editing Book</h4>
</div>

<form action="editBooks" method="post">
      <div class="modal-body">
      <h2>Enter New Name for Book</h2>
		<input type ="text" name = "title" placeholder="Enter"><br />
		<input type = "hidden" name = "bookId" value="<%=book.getBookId()%>">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="Submit" class="btn btn-primary">Submit</button>
   </div>
</form>
   
  