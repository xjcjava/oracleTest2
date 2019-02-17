
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

public class Oracle2Java {
    @Test
    public void test01() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.75.128:1521:orcl", "scott", "tiger");
        PreparedStatement pstm = connection.prepareStatement("SELECT * from emp");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("ename"));
        }

        resultSet.close();
        pstm.close();
        connection.close();
    }

    /**
     * java调用存储过程
     * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
     *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.75.128:1521:orcl", "scott", "tiger");
        CallableStatement cs = connection.prepareCall("{call sum(?,?)}");
        cs.setObject(2,7788);
        cs.setObject(1, OracleTypes.NUMBER);
        cs.execute();
        System.out.println(cs.getObject(1));
        cs.close();
        connection.close();
    }
}
