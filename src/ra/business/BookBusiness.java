package ra.business;

import ra.entity.Book;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class BookBusiness {
    public static boolean createBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_book(?,?,?,?,?,?,?,?)}");
            callSt.setString(1, book.getBookName());
            callSt.setString(2, book.getTitle());
            callSt.setString(3, book.getAuthor());
            callSt.setInt(4, book.getTotalPages());
            callSt.setString(5, book.getContent());
            callSt.setString(6, book.getPublisher());
            callSt.setDouble(7, book.getPrice());
            callSt.setInt(8, book.getTypeId());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Book findBookByName(String bookName) {
        Connection conn = null;
        CallableStatement callSt = null;
        Book book = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_book_by_name(?)}");
            callSt.setString(1, bookName);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setTitle(rs.getString("book_title"));
                book.setAuthor(rs.getString("book_author"));
                book.setTotalPages(rs.getInt("book_total_page"));
                book.setContent(rs.getString("book_content"));
                book.setPublisher(rs.getString("book_publisher"));
                book.setPrice(rs.getDouble("book_price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setDeleted(rs.getBoolean("book_is_deleted"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return book;
    }
}
