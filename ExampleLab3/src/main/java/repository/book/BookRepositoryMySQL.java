package repository;

import model.Book;
import model.builder.BookBuilder;
import utils.JDBConnectionWrapper;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRepositoryMySQL implements BookRepository{

    private final JDBConnectionWrapper connectionWraper;

    public BookRepositoryMySQL(JDBConnectionWrapper connectionWrapper){
        this.connectionWraper=connectionWrapper;
    }

    @Override
    public List<Book> findAll() {
        Connection connection=connectionWraper.getConnection();
        List<Book> books=new ArrayList<>();

        try{
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM book";

            ResultSet resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                books.add(getBookFromResultSet(resultSet));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    @Override
    public Book findBookById(Long id) {
        return null;
    }

    @Override
    public boolean save(Book book) {

        Connection connection=connectionWraper.getConnection();

        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO book VALUES (null, ?, ?, ?)");
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setDate(3, new java.sql.Date(book.getPublishedDate().getTime()));

            preparedStatement.executeUpdate();
            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void removeAll() {

    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException{
        return new BookBuilder()
                .setTitle(resultSet.getString("title"))
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setPublishedDate(new Date(resultSet.getDate("publishedDate").getTime()))
                .build();
    }
}
