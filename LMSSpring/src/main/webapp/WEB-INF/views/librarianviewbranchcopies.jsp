<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.gcit.lms.entity.Book"%>

<%
/*
LibrarianService LibrarianService = new LibrarianService();

Integer bookId = Integer.parseInt(request.getParameter("bookId"));
Integer branchId = Integer.parseInt(request.getParameter("branchId"));

Integer numberOfCopies = LibrarianService.getCopiesForBranch(bookId,branchId);

AdminService adminService = new AdminService();
List<Book> books = adminService.getAllBooks();
*/
%>
<div class="modal-header">
	<button type="button" class="close" onclick="javascript:window.location.reload()" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Editing Copy</h4>
</div>


<div class = "modal-body">
<h2>CURRENT COPY OF BOOKS: <mark>${copies}</mark></h2>
<br />
	<br />	
	<br />
	<form action="changeCopy" method="post">
		Enter New Copy Number: <input type="text" name="newCopy" placeholder="Enter a number" ><br />
		<input type="hidden" value="${bookId}" name="bookId"><br />
		<input type="hidden" value="${branchId}" name="branchId" />
		<button type="submit" class="btn btn-primary">SUBMIT</button>
	</form>	
</div>


<div class="modal fade bs-example-modal-lg" tabindex="-1" id=e role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    
    </div>
  </div>
</div>

<script>
    window.onunload = refreshParent;
    function refreshParent() {
        window.opener.location.reload();
    }
</script>










