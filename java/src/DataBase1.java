import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase1 extends DataBaseConnect {
    private DataBaseConnect connect = new DataBaseConnect();

    //判断书籍是否存在
    public boolean Select(String bookName) throws SQLException, ClassNotFoundException {
        connect.getConnection();
        PreparedStatement sql;
        Connection connection = this.getConnection();
        sql = connection.prepareStatement("select *from library.db_book");
        ResultSet resultSet;
        resultSet = sql.executeQuery();
        int flag = 0;
        while (resultSet.next()) {
            String book = resultSet.getString("BookName");
            if (bookName.equals(book)) {
                flag = 1;
            }
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    //查找书籍剩余情况
    public int Select(String bookName, int flag) throws SQLException, ClassNotFoundException {
        connect.getConnection();
        PreparedStatement sql;
        Connection connection = this.getConnection();
        sql = connection.prepareStatement("select  *from library.db_book ");
        ResultSet resultSet;
        resultSet = sql.executeQuery();
        int number = 0;
        while (resultSet.next()) {
            String book = resultSet.getString("BookName");
            int bookNumber = resultSet.getInt("BookNumber");
            if (bookName.equals(book))
                number = bookNumber;
        }
        return number;
    }

    //更新图书馆书籍数目
    public void UpDate(String bookName, int flag) throws SQLException, ClassNotFoundException {
        connect.getConnection();
        PreparedStatement sql;
        Connection connection = this.getConnection();
        sql = connection.prepareStatement("update library.db_book set BookNumber=? where BookName=?");
        String number;
        number = String.valueOf(Select(bookName, flag));
        if (flag == 1)
            sql.setInt(1, Integer.parseInt(number) - 1);
        else if (flag == 2)
            sql.setInt(1, Integer.parseInt(number) + 1);
        sql.setString(2, bookName);
        sql.executeUpdate();
    }

    //记得更新借书时间
    public void addBorrowName(String bookName, String borrowName, String borrowTime, String returnTime) throws SQLException, ClassNotFoundException {
        Connection connection = connect.getConnection();
        PreparedStatement sql;
        sql = connection.prepareStatement("insert into library.db_borrow(BookName, BorrowName,BorrowTime,ReturnTime)values (?,?,?,?)");
        sql.setString(1, bookName);
        sql.setString(2, borrowName);
        sql.setString(3, borrowTime);
        sql.setString(4, returnTime);
        sql.executeUpdate();
    }

    //还书记录
    public void returnBook(String borrowName) throws SQLException, ClassNotFoundException {
        Connection connection = connect.getConnection();
        PreparedStatement sql;
        sql = connection.prepareStatement("delete from library.db_borrow where BorrowName=?");
        sql.setString(1, borrowName);
        sql.executeUpdate();
    }

}