import java.sql.*;

/**
 * JDBC工具类，方法都是私有的
 * 因为方法都是动态的，不需要创建对象调用
 */
public class DBUtil {
    static{
        try{
            Class.forName("com.mysql.jdbc.Drivier");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private DBUtil(){

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/powernode", "root", "i78231761");
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
