package repository;

import model.Book;
import model.builder.BookBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryMock implements BookRepository{

    private List<Book> books;


    public BookRepositoryMock(){
        books=new ArrayList<>();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findBookById(Long id) {

        List<Book> filteredBooks=books.parallelStream()
                .filter(it->it.getId().equals(id))
                .collect(Collectors.toList());



        return (filteredBooks.size()>0)?filteredBooks.get(0):null;
    }

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }
}
