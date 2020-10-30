package repository;

import model.Book;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator {

    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache) {
        super(bookRepository);
        this.cache=cache;
    }

    @Override
    public List<Book> findAll() {

        if(cache.hasResult()){
            return cache.load();
        }

        List<Book> books=decoratedBookRepository.findAll();
        cache.save(books);

        return books;
    }

    @Override
    public Book findBookById(Long id) {
        return decoratedBookRepository.findBookById(id);
    }

    @Override
    public boolean save(Book book) {
        cache.invalidateCache();
        return decoratedBookRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedBookRepository.removeAll();
    }
}
