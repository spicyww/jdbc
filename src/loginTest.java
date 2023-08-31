import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**解决sql注入问题：
 * 只需要用户提供的信息不参与sql语句的编译过程
 * 使用PreparedStatement，预编译好sql，之后再传入参数
 * PreparedStatement会在编译的时候进行安全检查
 */

/**
 * 在业务方面要求必须支持SQL注入的时候（比如排序）
 * 业务方面要求进行sql语句拼接的时候，必须使用statement
 */
public class loginTest {
    public static void main(String[] args) {
        Map<String, String> usrLoginInfo = initData();
        boolean loginSuccess = login(usrLoginInfo);
    }

    private static boolean login(Map<String, String> userLoginInfo){
        String name = userLoginInfo.get("name");
        String pwd = userLoginInfo.get("pwd");

        Connection connection = null;
//        Statement statement = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Boolean out = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DBUtil.getConnection();
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/powernode", "root", "i78231761");
//            statement = connection.createStatement();
            String sql = "select * from user where loginName = ? and loginpwd = ?";
            statement = connection.prepareStatement(sql);
//            String sql = "select * from user where loginName = '"+userLoginInfo.get("loginName")+"' and loginpwd = '"+userLoginInfo.get("loginpwd")+"'";
            //给占位符传值
            statement.setString(1,userLoginInfo.get("loginName"));
            statement.setString(2,userLoginInfo.get("loginPwd"));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                out = true;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeConnection(connection,statement,resultSet);
        }
        return out;
    }


    private static Map<String, String> initData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("userName:");
        String name = scanner.nextLine();
        System.out.println("userPwd:");
        String pwd = scanner.nextLine();

        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("name", name);
        userLoginInfo.put("pwd", pwd);

        return userLoginInfo;
    }
}
