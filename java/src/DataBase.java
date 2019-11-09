import java.sql.*;

class DataBaseConnect {
    private static final String USER = "root";
    private static final String PASS = "123456";
    private Connection connection;
    private PreparedStatement sql;
    private ResultSet resultSet;

    Connection getConnection() throws SQLException, ClassNotFoundException {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        Class.forName(JDBC_DRIVER);
        String DB_URl = "jdbc:mysql://root@localhost:3306/library?characterEncoding=utf8";
        connection = DriverManager.getConnection(DB_URl, USER, PASS);
        System.out.println("数据库链接成功");
        return connection;
    }

    public void CloseConnection(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    public boolean Select(String user, String pass) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("select *from library.db_账户 ");
        int flag = 0;
        try {
            resultSet = sql.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String passWord = resultSet.getString("password");
                if (user.equals(username) && pass.equals(passWord))
                    flag = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    public boolean Insert(String user, String password) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
//     sql=connection.prepareStatement("insert into library.db_book(ID,username, password) values(?,?,?)");
        boolean result = this.Select(user, password);
        if (!result) {
            sql = connection.prepareStatement("insert into library.db_账户(username, password) values(?,?)");
//          sql.setInt(1,0);
            sql.setString(1, user);
            sql.setString(2, password);
            sql.executeUpdate();
            return true;
        } else {
            return false;
        }
    }

    public void Delete(String user, String password) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        boolean result = this.Select(user, password);
        if (!result) {
            sql = connection.prepareStatement("delete from library.db_账户 where username=?");
            sql.setString(1, user);
//           sql.setString(2,password);
            sql.executeUpdate();

        }
    }

    //更新数据库用户信息
    public void UpData(String user, String passWord) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("update library.db_账户 set username=? where password=?");
        sql.setString(1, user);
        sql.setString(2, passWord);
        sql.executeUpdate();
    }

    public String AddBook(String bookName, String bookAuthor, String bookTime, int number) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("insert into library.db_book(BookName, BookAuthor, BookTime, BookNumber) values(?,?,?,?)");
        sql.setString(1, bookName);
        sql.setString(2, bookAuthor);
        sql.setString(3, bookTime);
        sql.setInt(4, number);
        sql.executeUpdate();
        return "添加书籍成功";
    }

    public boolean SelectBook(String bookName) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("select *from library.db_book ");
        resultSet = sql.executeQuery();
        int flag = 0;
        while (resultSet.next()) {
            String book = resultSet.getString("BookName");
            if (bookName.equals(book))
                flag = 1;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    public void DeleteBook(String bookName) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("delete from library.db_book where BookName=?");
        sql.setString(1, bookName);
//           sql.setString(2,password);
        sql.executeUpdate();

    }
}
