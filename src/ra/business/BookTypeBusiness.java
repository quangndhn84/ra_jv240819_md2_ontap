package ra.business;

import ra.entity.BookStatitic;
import ra.entity.BookType;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BookTypeBusiness {
    public static List<BookType> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<BookType> listBookTypes = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_book_type()}");
            ResultSet rs = callSt.executeQuery();
            listBookTypes = new ArrayList<>();
            while (rs.next()) {
                BookType bookType = new BookType();
                bookType.setTypeId(rs.getInt("type_id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookType.setDescription(rs.getString("type_description"));
                bookType.setDeleted(rs.getBoolean("type_is_deleted"));
                listBookTypes.add(bookType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBookTypes;
    }

    public static boolean createBookType(BookType bookType) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_book_type(?,?,?)}");
            callSt.setString(1, bookType.getTypeName());
            callSt.setString(2, bookType.getDescription());
            callSt.setBoolean(3, false);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateBookType(BookType bookType) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_book_type(?,?,?)}");
            callSt.setInt(1, bookType.getTypeId());
            callSt.setString(2, bookType.getTypeName());
            callSt.setString(3, bookType.getDescription());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static BookType findById(int typeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        BookType bookType = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_book_type_by_id(?)}");
            callSt.setInt(1, typeId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                bookType = new BookType();
                bookType.setTypeId(rs.getInt("type_id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookType.setDescription(rs.getString("type_description"));
                bookType.setDeleted(rs.getBoolean("type_is_deleted"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookType;
    }

    public static boolean deleteBookType(int typeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_book_type(?)}");
            callSt.setInt(1, typeId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<BookStatitic> statiticBookByType() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<BookStatitic> listBookStatitic = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statitic_book_by_book_type()}");
            ResultSet rs = callSt.executeQuery();
            listBookStatitic = new ArrayList<>();
            while (rs.next()) {
                BookStatitic bookStatitic = new BookStatitic();
                bookStatitic.setTypeId(rs.getInt("type_id"));
                bookStatitic.setTypeName(rs.getString("type_name"));
                bookStatitic.setCntBook(rs.getInt("cntBook"));
                listBookStatitic.add(bookStatitic);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBookStatitic;
    }

    public static boolean isExistBookTypeName(int typeId, String typeName) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isExist = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_check_exist_type_name(?,?,?)}");
            callSt.setInt(1, typeId);
            callSt.setString(2, typeName);
            callSt.registerOutParameter(3, Types.BOOLEAN);
            callSt.execute();
            isExist = callSt.getBoolean(3);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return isExist;
    }


}
