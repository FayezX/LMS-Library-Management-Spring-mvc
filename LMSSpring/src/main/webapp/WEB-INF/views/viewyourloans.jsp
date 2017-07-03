<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
        <h4 class="modal-title" >Viewing Loans.....</h4>
     </div>

	  
      <div class="modal-body">
      <h2>loan Details</h2>
      </div>
      
		<table class="table" rules="all">
   		<tr>
			<th>Date Out</th>
			<th>Due Date</th>
			<th>Date In</th>
		</tr>
    <gcit:forEach var="a" items="${loans}">
   	 <tr>
   	 <td>
    	<fmt:formatDate value="${a.dateOut}" pattern="yyyy-MM-dd" var="newdatevar" />
        <gcit:out value="${newdatevar}" />
    
     </td>
    
     <td>
    	<fmt:formatDate value="${a.dueDate}" pattern="yyyy-MM-dd" var="due" />
        <gcit:out value="${due}" />
     </td>
     <td>
    	<fmt:formatDate value="${a.dateIn}" pattern="yyyy-MM-dd" var="in" />
        <gcit:out value="${in}" />
     </td>
     </tr>
   	</gcit:forEach>
  </table>
      
      <div class="modal-footer">
      <br />
      <br />
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
   </div>
   
  