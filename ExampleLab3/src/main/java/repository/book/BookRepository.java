package repository;

import model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findBookById(Long id);

    boolean save(Book book);

    void removeAll();
}
