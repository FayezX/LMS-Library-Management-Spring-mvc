package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>{

	public void addBook(Book book) throws SQLException{
		template.update("insert into tbl_book(title) values (?)", new Object[] {book.getTitle()});
	}
/*
	public Integer addBookWithID(Book book,String[] ids,String[] genreId) throws SQLException{

		Integer newBookId = saveWithID("insert into tbl_book(title) values (?)", new Object[] {book.getTitle()});
		System.out.println(newBookId);

		for(int i = 0; i < ids.length; i++){
			insertForBook("insert into tbl_book_authors(bookId,authorId) values(?,?)",newBookId,ids[i]);
		}
		for(int i = 0; i < genreId.length;i++){
			insertForBook("insert into tbl_book_genres(bookId,genre_id) values(?,?)",newBookId,genreId[i]);
		}

		return 0; 
	}
	*/

	public void updateBook(Book book) throws SQLException{
		template.update("update tbl_book set title=? where bookId=?", new Object[] {book.getTitle(), book.getBookId()});
	}

	public void deleteBook(Book book) throws SQLException{
		template.update("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
	}
	
	
	public Integer getBookCount() throws SQLException{
		return template.queryForObject("select count(*) as Count from tbl_book",Integer.class);
	}
	

	public List<Book> readAllBooks() throws SQLException{
		return template.query("select * from tbl_book", this);
	}

	public List<Book> readAllBooksByTitle(String searchString) throws SQLException{
		searchString = "%"+searchString+"%";
		return template.query("select * from tbl_book where title like ?", new Object[]{searchString}, this);
	}

	public List<Book> readAllBooksByName(Integer pageNo, String searchString) throws SQLException{
		searchString = "%"+searchString+"%";
		setPageNo(pageNo);
		return template.query("select * from tbl_book where title like ?",new Object[]{searchString}, this);
	}

	public List<Book> readAllBooksPagination(Integer pageNo) throws SQLException{
		setPageNo(pageNo);
		String query = "";
		if (getPageNo() > 0) {
			System.out.println(">0");
			int count = 0;
			int index = (getPageNo() - 1) * 10;
			query = " Limit " + index + " , " + getPageSize();
		}
		return template.query("select * from tbl_book" + query, this);
	}

	public Book readBooksByPK(Integer bookId) throws SQLException{
		List<Book> books = template.query("select * from tbl_book where bookId = ?",new Object[]{bookId}, this);
		if(books!=null){
			return books.get(0);
		}
		return null;
	}
	
	public List<Book> readAllBooksByAuthorId(Integer authorId) throws SQLException {
		return template.query("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId = ?)", new Object[]{authorId}, this);
	}
	
	public List<Book> readAllBooksByBranchId(Integer branchId) throws SQLException {
		return template.query("select * from tbl_book where bookId in (select bookId from tbl_book_copies where branchId = ?)", new Object[]{branchId}, this);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>(); 
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			//do the same for Genres and Publishers
			books.add(b);
		}
		return books;
	}

}
