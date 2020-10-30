package service;

import model.Book;
import repository.book.BookRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {

        Book book=findBookById(id);

        Date publishedDate=book.getPublishedDate();

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(publishedDate);
        int yearOfPublishing=calendar.get(Calendar.YEAR);
        calendar.setTime(new Date());
        int yearToday=calendar.get(Calendar.YEAR);

        return yearToday-yearOfPublishing;
    }
}
