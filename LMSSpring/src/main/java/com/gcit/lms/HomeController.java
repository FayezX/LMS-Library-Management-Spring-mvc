package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	BorrowerService borrowerService;
	
	@Autowired
	LibrarianService librarianService;
	
	@Autowired
	Author author;
	
	@Autowired
	Book book;
	
	@Autowired
	LibraryBranch libraryBranch;
	
	@Autowired
	BookLoans bookLoans;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		/*
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		Integer n = 2;
		model.addAttribute("integerTest",n);
		*/
		return "template";
	}
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String home() {
		return "admin";
	}
	
	@RequestMapping(value = "bookorauthor", method = RequestMethod.GET)
	public String bookOrAuthor() {
		return "bookorauthor";
	}
	
	@RequestMapping(value = "author", method = RequestMethod.GET)
	public String author() {
		return "author";
	}
	
	@RequestMapping(value = "viewauthor", method = RequestMethod.GET)
	public String viewauthor(Model model) throws SQLException {
		model.addAttribute("authors", adminService.getAllAuthors(1, null));
		Integer authCount = adminService.getAuthorsCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		return "viewauthor";
	}
	
	@RequestMapping(value = "pageAuthors", method = RequestMethod.GET)
	public String pageAuthors(@RequestParam Map<String,String> pageNo, Model model) throws SQLException {		
		Integer authCount = adminService.getAuthorsCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		
		if (pageNo.get("pageNo") != null && !pageNo.get("pageNo").isEmpty()) {
			Integer pageNum = Integer.parseInt(pageNo.get("pageNo"));
			model.addAttribute("authors", adminService.getAllAuthors(pageNum, null));
		return "viewauthor";
	}
		return null;
}
	
	@RequestMapping(value = "addauthor", method = RequestMethod.GET)
	public String addauthor(Model model) throws SQLException {
		model.addAttribute("books", adminService.getAllBooks());
		return "addauthor";
	}
	
	@RequestMapping(value = "addnewauthor", method = RequestMethod.POST)
	public String addnewauthor(@RequestParam("authorName") String name,@RequestParam("bookId") String[] bookId, Model model) throws SQLException {
		System.out.println("Addming author");
		author.setAuthorName(name);
		Integer authorId = adminService.saveAuthor(author);
		System.out.println(authorId);
		
		if(bookId != null){
			for(String b : bookId){
				adminService.addBookAndAuthor(Integer.parseInt(b),authorId);
			}
		}
		
		model.addAttribute("authors", adminService.getAllAuthors(1, null));
		Integer authCount = adminService.getAuthorsCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		return "viewauthor";
	}
	
	@RequestMapping(value = "librarianmenu", method = RequestMethod.GET)
	public String librarianmenu() {
		return "librarianmenu";
	}
	
	@RequestMapping(value = "librarianviewbranch", method = RequestMethod.GET)
	public String librarianviewbranch(Model model) throws SQLException {
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();
		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
		model.addAttribute("pages", pages);
		return "librarianviewbranch";
	}
	
	@RequestMapping(value = "borrowercheck", method = RequestMethod.GET)
	public String borrowercheck(Model model) throws SQLException {
		return "borrowercheck";
	}
	
	@RequestMapping(value = "checkBorrowerId", method = RequestMethod.POST)
	public String checkBorrowerId(@RequestParam Map<String,String> borId,Model model) throws SQLException {
		String borrowerId = borId.get("borrowerId");
		System.out.println("HERE");
			int check = borrowerService.checkBorrowerId(borrowerId);
			System.out.println(check);
			if (check == 1) {
				model.addAttribute("cardNo",borrowerId);
				Integer authorCount = adminService.getBranchCount();
				List<LibraryBranch> q = adminService.getAllBranch(1, null);
				
				System.out.println("home controller");
				ArrayList<Integer> numberOfCopies = librarianService.getArrayCopiesForBranch(q);
				
				int pages = 0;
				if(authorCount%10 > 0){
					pages = authorCount/10+1;
				}else{
					pages = authorCount/10;
				}
				System.out.println("//////////////////");

				for(Integer i : numberOfCopies){
					System.out.println(i);
				}
				/*
				Integer arraySize = numberOfCopies.size();
				Integer[] array = null;
				//Integer index = 0;
				for(int i = 0; i < arraySize; i++){
					array[i] = i;
				}
				*/
				model.addAttribute("pages", pages);
				model.addAttribute("branches",adminService.getAllBranch(1, null));
				model.addAttribute("copies",numberOfCopies);
				model.addAttribute("cardNo", borrowerId);
				//model.addAttribute("array", array);
				
				System.out.println("before transfer");
				return "borrowermenu";
			} else {
				return "template";
			}
	}
	
	@RequestMapping(value = "librarianviewbranchcopies", method = RequestMethod.GET)
	public String librarianviewbranchcopies(@RequestParam Map<String,String> ids,Model model) throws SQLException {
		String bookId = ids.get("bookId");
		String branchId = ids.get("branchId");
		System.out.println(bookId);
		System.out.println(branchId);
		book.setBookId(Integer.parseInt(bookId));
		libraryBranch.setBranchId(Integer.parseInt(branchId));
		
		System.out.println("home controller");
		Integer numberOfCopies = librarianService.getCopiesForBranch(book, libraryBranch);
		if(numberOfCopies == null){
			numberOfCopies=0;
		}
		System.out.println(numberOfCopies);
		model.addAttribute("copies", numberOfCopies);
		model.addAttribute("branchId", branchId);
		model.addAttribute("bookId", bookId);
		
		return "librarianviewbranchcopies";
	}
	
	@RequestMapping(value = "deleteAuthor", method = RequestMethod.GET)
	public String deleteAuthor(@RequestParam Map<String,String> id,Model model) throws SQLException {
		String authorId = id.get("authorId");
		author.setAuthorId(Integer.parseInt(authorId));
		adminService.DeleteAuthor(author);
		
		model.addAttribute("authors", adminService.getAllAuthors(1, null));
		Integer authCount = adminService.getAuthorsCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		return "viewauthor";
	}
	
	@RequestMapping(value = "editauthor", method = RequestMethod.GET)
	public String editauthor(@RequestParam Map<String,String> ids,Model model) throws SQLException {
		System.out.println("Edit Author");
		Integer authorId = Integer.parseInt(ids.get("authorId"));
		model.addAttribute("author",adminService.getAuthorsByPk(authorId));
		return "editauthor";
	}
	
	@RequestMapping(value = "newAuthorEdited", method = RequestMethod.POST)
	public String newAuthorAdded(@RequestParam Map<String,String> info,Model model) throws SQLException {
		author.setAuthorName(info.get("authorName"));
		author.setAuthorId(Integer.parseInt(info.get("authorId")));
		adminService.saveAuthor(author);
		
		model.addAttribute("authors", adminService.getAllAuthors(1, null));
		Integer authCount = adminService.getAuthorsCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		return "viewauthor";
	}
	
	@RequestMapping(value = "editbranch", method = RequestMethod.GET)
	public String editbranch(@RequestParam("branchId") Integer branchId,Model model) throws SQLException {
		model.addAttribute("libraryBranch",librarianService.getBranchsByPk(branchId));
		return "editbranch";
	}
	
	@RequestMapping(value = "changeBranch", method = RequestMethod.POST)
	public String changeBranch(@RequestParam Map<String,String> info,Model model) throws SQLException {
		libraryBranch.setBranchId(Integer.parseInt(info.get("branchId")));
		if(!info.get("name").isEmpty()){
			libraryBranch.setBranchName(info.get("name"));
			System.out.println("1");
		}
		if(!info.get("address").isEmpty()){
			libraryBranch.setBranchAddress(info.get("address"));
			System.out.println("2");
		}
		librarianService.saveBranch(libraryBranch);
		
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();

		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
		
		model.addAttribute("pages", pages);
		
		return "librarianviewbranch";
	}
	
	@RequestMapping(value = "changeCopy", method = RequestMethod.POST)
	public String changeCopy(@RequestParam Map<String,String> info,Model model) throws SQLException {
		String newCopy = info.get("newCopy");
		if(newCopy.isEmpty() || newCopy == null){
			System.out.println("Empty");
			model.addAttribute("branches",adminService.getAllBranch(1, null));
			Integer authorCount = adminService.getBranchCount();
			int pages = 0;
			if(authorCount%10 > 0){
				pages = authorCount/10+1;
			}else{
				pages = authorCount/10;
			}
			model.addAttribute("pages", pages);
			return "librarianviewbranch";
		}
		
		librarianService.updateCopies(newCopy,info.get("bookId"),info.get("branchId"));
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();
		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
		model.addAttribute("pages", pages);
		return "librarianviewbranch";
	}
	
	@RequestMapping(value = "book", method = RequestMethod.GET)
	public String book() throws SQLException {
		return "book";
	}
	
	@RequestMapping(value = "addbook", method = RequestMethod.GET)
	public String addbook(Model model) throws SQLException {
		model.addAttribute("authors", adminService.searchAuthors(0));
		model.addAttribute("genres", adminService.getAllGenre());
		return "addbook";
	}
	
	@RequestMapping(value = "addNewbook", method = RequestMethod.GET)
	public String addNewbook(Model model) throws SQLException {
		model.addAttribute("authors", adminService.searchAuthors(0));
		model.addAttribute("genres", adminService.getAllGenre());
		return "addbook";
	}
	
	@RequestMapping(value = "viewbooks", method = RequestMethod.GET)
	public String viewbook(Model model) throws SQLException {
		model.addAttribute("bookss", adminService.getAllBooks(1, null));
		Integer authCount = adminService.getBooksCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);		
		return "viewbooks";
	}
	
	@RequestMapping(value = "adminPublisher", method = RequestMethod.GET)
	public String adminPublisher(Model model) throws SQLException {
		return "adminPublisher";
	}
	
	@RequestMapping(value = "adminbranches", method = RequestMethod.GET)
	public String adminbranches(Model model) throws SQLException {
		return "adminbranches";
	}
	
	@RequestMapping(value = "adminBorrower", method = RequestMethod.GET)
	public String adminBorrower(Model model) throws SQLException {
		return "adminBorrower";
	}
	
	@RequestMapping(value = "adminViewBranch", method = RequestMethod.GET)
	public String adminViewBranch(Model model) throws SQLException {
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();
		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
			model.addAttribute("pages", pages);
		
		return "adminViewBranch";
	}
	
	@RequestMapping(value = "editadminbranch", method = RequestMethod.GET)
	public String editadminbranch(@RequestParam("branchId") Integer branchId,Model model) throws SQLException {
		model.addAttribute("libraryBranch",librarianService.getBranchsByPk(branchId));
		return "editadminbranch";
	}
	
	@RequestMapping(value = "changeAdminBranch", method = RequestMethod.POST)
	public String changeAdminBranch(@RequestParam Map<String,String> info,Model model) throws SQLException {
		libraryBranch.setBranchId(Integer.parseInt(info.get("branchId")));
		if(!info.get("name").isEmpty()){
			libraryBranch.setBranchName(info.get("name"));
			System.out.println("1");
		}
		if(!info.get("address").isEmpty()){
			libraryBranch.setBranchAddress(info.get("address"));
			System.out.println("2");
		}
		librarianService.saveBranch(libraryBranch);
		
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();

		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
		
		model.addAttribute("pages", pages);
		return "adminViewBranch";
	}
	
	@RequestMapping(value = "deleteBranch", method = RequestMethod.GET)
	public String deleteBranch(@RequestParam Map<String,String> id,Model model) throws SQLException {
		String branchId = id.get("branchId");
		libraryBranch.setBranchId(Integer.parseInt(branchId));
		adminService.deleteBranch(libraryBranch);
		
		model.addAttribute("branches", adminService.getAllBranch(1, null));
		Integer authCount = adminService.getBranchCount();
		int pages = 0;
		if (authCount % 10 > 0) {
			pages = authCount / 10 + 1;
		} else {
			pages = authCount / 10;
		}
		model.addAttribute("pages", pages);
		return "adminViewBranch";
	}
	
	@RequestMapping(value = "adminOverride", method = RequestMethod.GET)
	public String adminOverride(@RequestParam("branchId") Integer branchId,Model model) throws SQLException {
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		Integer authorCount = adminService.getBranchCount();
		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
			model.addAttribute("pages", pages);
		return "adminOverride";
	}
	
	@RequestMapping(value = "checkoutreturn", method = RequestMethod.GET)
	public String checkoutreturn(@RequestParam Map<String,String> info,Model model) throws SQLException {
		System.out.println("checkoutreturn");
		String bookId = info.get("bookId");
		String branchId = info.get("branchId");
		String move = info.get("move");
		String numberOfCopies = info.get("numcopies");
		String borrowerId = info.get("borrowerId");
		System.out.println(bookId);
		System.out.println(branchId);
		System.out.println(move);
		System.out.println(numberOfCopies);
		
		borrowerService.updatecopy(bookId, branchId, move,numberOfCopies);
		//////////////////
		Integer authorCount = adminService.getBranchCount();
		List<LibraryBranch> q = adminService.getAllBranch(1, null);
		
		System.out.println("home controller");
		ArrayList<Integer> numberOfCops = librarianService.getArrayCopiesForBranch(q);
		
		int pages = 0;
		if(authorCount%10 > 0){
			pages = authorCount/10+1;
		}else{
			pages = authorCount/10;
		}
		System.out.println("//////////////////");

		for(Integer i : numberOfCops){
			System.out.println(i);
		}
		model.addAttribute("pages", pages);
		model.addAttribute("branches",adminService.getAllBranch(1, null));
		model.addAttribute("copies",numberOfCops);
		model.addAttribute("cardNo",borrowerId);
		System.out.println("before transfer");
		
		return "borrowermenu";
	}
	
	@RequestMapping(value = "viewyourloans", method = RequestMethod.GET)
	public String viewyourloans(@RequestParam("cardNo") String cardNo,Model model) throws SQLException {
		
		System.out.println("viewyour loans reached");
		List<BookLoans> bookLoans = borrowerService.getYourLoans(cardNo);
		System.out.println(bookLoans.size());
		for(BookLoans bl : bookLoans){
			System.out.println("Due date is : " + bl.getDueDate().toString());
		}
		model.addAttribute("loans",bookLoans);
		return "viewyourloans";
	}
}
