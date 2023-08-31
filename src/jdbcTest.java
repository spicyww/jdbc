import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class jdbcTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/powernode";
        String username = "root";
        String password = "i78231761";

        Connection connection = null;
        Statement statement = null;

        try {
            //手动注册驱动（新版本不需要）
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(jdbcUrl,username,password);

            System.out.println("数据库连接对象 = " + connection);

            //获取数据库操作对象
            statement = connection.createStatement();
            //执行sql
//            String sql = "insert into dept(deptno,dname,loc) values(50, '人事部', '北京')";
//            String sql = "DELETE FROM dept WHERE deptno=50";
            String sql = "SELECT * FROM dept";
            // 返回值是影响数据库中的记录条数
            int count = statement.executeUpdate(sql);

            //处理查询结果集

            System.out.println(count==1 ? "保存成功":"保存失败");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
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
