package tests;

import aplicatie.dao.JDBCDAO;
import aplicatie.user.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 9/15/2015.
 */
public class AppTest {
    Connection conn;

    public static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(dbURL, username, password);
    }

    @Before
    public void before() throws Exception {
        conn = getConnection();

    }

    @After
    public void after() throws Exception {
        String sql2 = "TRUNCATE `sampledb`.`users`;";
        PreparedStatement statement2 = conn.prepareStatement(sql2);
        statement2.executeUpdate();
        conn.close();
    }

    @Test
    public void testCRUD() throws Exception {

        User user = new User();
        user.setUserName("Bill");
        user.setPassword("secretpass");
        user.setFullName("bill gates");
        user.setEmail("bill.gates@microsoft.com");

        //insert
        JDBCDAO.INSTANCE.insertUser(user, conn);

        //test insert
        List<User> users_retrived = JDBCDAO.INSTANCE.getUsers(conn);
        Assert.assertEquals(1, users_retrived.size());
        Assert.assertEquals(user, users_retrived.get(0));

        //update
        JDBCDAO.INSTANCE.updateUser(user, conn);

        //test update
        List<User> user_retrived2 = JDBCDAO.INSTANCE.getUsers(conn);
        Assert.assertEquals(user, user_retrived2.get(0));

        //delete
        JDBCDAO.INSTANCE.deleteUser(user.getId(), conn);

        //test delete
        List<User> users_retrived3 = JDBCDAO.INSTANCE.getUsers(conn);
        Assert.assertTrue(users_retrived3.isEmpty());

    }


}
