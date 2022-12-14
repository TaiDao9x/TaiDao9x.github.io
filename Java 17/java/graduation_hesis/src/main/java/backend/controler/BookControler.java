package backend.controler;

import backend.model.Book;
import backend.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookControler {
    BookService bookService = new BookService();

    public ArrayList<Book> showBookByCategory(String category) {
        return bookService.showBookByCategory(category);
    }

    public ArrayList<Book> showBookByPulisherCompany(String pulisherCompany) {
        return bookService.showBookByPulisherCompany(pulisherCompany);
    }

    public ArrayList<Book> findBookByName(String title) {
        return bookService.findBookByName(title);
    }

    public ArrayList<Book> findBookByAuthor(String author) {
        return bookService.findBookByAuthor(author);

    }

    public Book findBookById(int id) {
        return bookService.findBookById(id);
    }

    public boolean checkIdExist(int id) {
        return bookService.checkIdExist(id);
    }

    public int getIdBook() {
        return bookService.getIdBook();
    }

    public void addBook(Book newBook) {
        bookService.addBook(newBook);
    }

    public ArrayList<Book> showBook() {
        return bookService.showBook();
    }

    public void upDatePrice(int id, int newPrice) {
        bookService.upDatePrice(id, newPrice);
    }

    public void updateQuantity(int id, int newQuantity) {
        bookService.updateQuantity(id, newQuantity);
    }

    public void deleteBook(int id) {
        bookService.deleteBook(id);
    }

    public ArrayList<Book> showBookLessx(int checkQuantity) {
        return bookService.showBookLessx(checkQuantity);
    }

    public void changeAfterOrder(Map<Integer, Integer> reduceCount) {
        bookService.changeAfterOrder(reduceCount);
    }

    public List<Book> showAllBook() {
       return bookService.showAllBook();
    }
}
