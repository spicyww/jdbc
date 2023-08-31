import java.sql.*;


public class jdbcTest3 {
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
            String sql = "SELECT * FROM dept";
            // int executeUpdate(insert/delete/update)
            // ResultSet executeQuery(select)

            // 返回resultset
            ResultSet resultset = statement.executeQuery(sql);

            //处理查询结果集
            while (resultset.next()){
                // 编号从1开始
                String deptno = resultset.getString(1);
                String dname = resultset.getString(2);
                String loc = resultset.getString(3);
                System.out.println("DEPTNO:" + deptno + "  " + "DNAME:" + dname + "  " + "LOC:" + "loc");
            }


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

