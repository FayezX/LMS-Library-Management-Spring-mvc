<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Genre"%>

<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
/*
AdminService adminService = new AdminService();
List<Book> books = new ArrayList<>();
Integer authorCount = adminService.getBooksCount();
Double pages = 0.0;
pages = Math.ceil(authorCount/10)+1;
*/

/*
if(authorCount%10 > 0){
	pages = authorCount/10+1;
}else{
	pages = authorCount/10;
}
*/

/*
if(request.getAttribute("books")!=null){
	books = (List<Book>)request.getAttribute("books");
	
}else{
	books = adminService.getAllBooks(1,null);
}
*/
%>

<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			//$(this).addClass("done");
			$('#booksTable').html(data);
			//alert(data);
			updatePages();
		});
	}

	function updatePages() {
		$.ajax({
			url : "updatePages",
			data : {			
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			//$(this).addClass("done");
			$('#paging').html(data);
			//alert(data);
		});
	}
</script>





<!DOCTYPE html>
<!-- saved from url=(0057)https://blackrockdigital.github.io/startbootstrap-agency/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>GCIT Library Management</title>

<!-- Bootstrap Core CSS -->
<link href="./template_files/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./template_files/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="./template_files/css" rel="stylesheet" type="text/css">
<link href="./template_files/css(1)" rel="stylesheet" type="text/css">
<link href="./template_files/css(2)" rel="stylesheet" type="text/css">
<link href="./template_files/css(3)" rel="stylesheet" type="text/css">

<!-- Theme CSS -->
<link href="./template_files/agency.min.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" integrity="sha384-0s5Pv64cNZJieYFkXYOTId2HMA2Lfb6q2nAcx2n0RTLUnCAoTTsS0nKEO27XyKcY" crossorigin="anonymous"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" integrity="sha384-ZoaMbDF+4LeFxg6WdScQ9nnR1QC2MIRxA1O9KWEXQwns1G8UNyIEZIQidzb0T1fo" crossorigin="anonymous"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top affix">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="http://localhost:8080/lms/">LMS Library Management</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden active">
                        <a href="http://localhost:8080/LMSWeb/"></a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="admin">Administrator</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="librarianmenu">Librarian</a>
                    </li>
                    <li class="">
                        <a class="page-scroll"  data-toggle ="modal" data-target="#borrowerCheck"  href="borrowercheck">Borrower</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" onclick="aboutUs()">About Library</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" onclick="contactUs()">Help</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    
    <div class="modal fade bs-example-modal-lg" tabindex="-1" id=borrowerCheck role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    
    </div>
  </div>
</div>


<script>
function aboutUs(){
	alert('The mission of the Library is to create and sustain an evolving information environment that advances learning, research, and innovation at GCIT. We are committed to excellence in services, strategies, and systems that promote discovery, preserve knowledge, and improve worldwide scholarly communication.')
}

function contactUs(){
	alert('Need technical support or help with your service? Please Call us at ###########');
}
</script>

	<!-- Portfolio Grid Section -->
	<section id="portfolio" class="bg-light-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Welcome Admin</h2>
					<h3 class="section-subheading text-muted">Choose from the
						following.</h3>
				</div>
			</div>
			<div class="row">


				<div class="input-group">
					<button type="submit" onclick="searchBooks()">Search</button>
					<input type="text" name="searchString" id="searchString"
						class="form-control" placeholder="Enter Name"
						aria-describedby="basic-addon1" oninput="searchBooks()">
				</div>


				<div id='paging'>
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li><a href="#" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span>
							</a></li>
							 <gcit:forEach var="i" begin="1" end="${pages}">
    							<li><a href="pagebookss?pageNo=${i}">${i}</a></li>
   							 </gcit:forEach>
							<li><a href="#" aria-label="Next"> <span
									aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>
				</div>

				<table class="table" id="booksTable" rules="all">
					<tr>
						<th></th>
						<th>Book Title</th>
						<th>Genre of book</th>
						<th>Authors of book</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<gcit:forEach var="a" items="${bookss}" varStatus="loop">
					<tr>
						<td>
							${loop.index+1}
						</td>
						<td>
							<gcit:out value="${a.title}"></gcit:out>
							<br />
							
						</td>
						<td>
							<gcit:forEach var="b" items="${a.genres}">
								<gcit:out value="${b.genreName}"></gcit:out>
								<br />
								<br />
								
							</gcit:forEach>
						</td>
						<td>
							<gcit:forEach var="c"  items="${a.authors}">
								<gcit:out value="${c.authorName}"></gcit:out>
								<br />
								<br />
							</gcit:forEach>
						</td>
						<td><button type="button" class="btn btn-primary btn-sm"
								data-toggle="modal" data-target="#editBookModal"
								href="editbook.jsp?bookId=${a.bookId}">Edit Book</button></td>
						<td><button type="button" class="btn btn-warning btn-sm"
								onclick="javascript:location.href='deleteBook?bookId=${a.bookId}'">Delete
								Book</button></td>
					</tr>
					</gcit:forEach>
				</table>
			</div>
		</div>
	</section>

	<div class="modal fade bs-example-modal-lg" tabindex="-1"
		id=editBookModal role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content"></div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="./template_files/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="./template_files/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="./template_files/jquery.easing.min.js"
		integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb"
		crossorigin="anonymous"></script>

	<!-- Contact Form JavaScript -->
	<script src="./template_files/jqBootstrapValidation.js"></script>
	<script src="./template_files/contact_me.js"></script>

	<!-- Theme JavaScript -->
	<script src="./template_files/agency.min.js"></script>

</body>
</html>
