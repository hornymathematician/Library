import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDateBase extends DataBaseConnect {
    DataBaseConnect dataBaseConnect = new DataBaseConnect();
    private Connection connection;
    private PreparedStatement sql;
    private ResultSet resultSet;

    public boolean Select(String username) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        sql = connection.prepareStatement("select *from library.db_账户");
        int flag = 0;
        try {
            resultSet = sql.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("username");
                if (user.equals(username))
                    flag = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == 0)
            return true;
        else
            return false;
    }

    public boolean Insert(String user, String date, String email) throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
        boolean result = Select(user);
        if (result) {
            sql = connection.prepareStatement("insert into library.db_userinformation(username, birthday, Email) values (?,?,?)");
            sql.setString(1, user);
            sql.setString(2, date);
            sql.setString(3, email);
            sql.executeUpdate();
            return true;
        } else {
            return false;
        }
    }

}
