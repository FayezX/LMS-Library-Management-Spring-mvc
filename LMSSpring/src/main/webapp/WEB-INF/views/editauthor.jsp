<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<% 
/*
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	System.out.println(authorId);
	AdminService service = new AdminService();
	Author author = service.getAuthorsByPk(authorId);
	*/
%>

<div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editing Author</h4>
</div>

<form action="newAuthorEdited" method="post">
      <div class="modal-body">
      <h2>Enter New Name for Author</h2>
		<input type ="text" name = "authorName" placeholder="Enter"><br />
		<input type = "hidden" name = "authorId" value="${author.authorId}">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="Submit" class="btn btn-primary">Submit</button>
   </div>
</form>
   
  