import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class jdbcTest2 {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/powernode";
        String username = "root";
        String password = "i78231761";

        Connection connection = null;
        Statement statement = null;


        try {
            //手动注册驱动（新版本不需要）
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(jdbcUrl,username,password);
            //关闭自动提交
            connection.setAutoCommit(false);
            System.out.println("数据库连接对象 = " + connection);

            //获取数据库操作对象
            statement = connection.createStatement();
            String sql = "insert into dept(deptno,dname,loc) values(50, '人事部', '北京')";
            String sql2 = "insert into dept(deptno,dname,loc) values(50, '人事部', '北京')";
            // 返回值是影响数据库中的记录条数
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
//            int count = statement.executeUpdate(sql);

            //处理查询结果集

            connection.commit();
            System.out.println("Transaction committed successfully!");

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Transaction rolled back due to an error!");;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //恢复自动提交
            connection.setAutoCommit(true);
            //为了保证资源必定释放，需要再finally里关闭资源
            try{
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
