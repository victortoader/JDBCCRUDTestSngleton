package aplicatie.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/2/2015.
 */
public class UserDaoJdbc implements UserDao {

    private static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(dbURL, username, password);
    }

    public void insertUser(User user) throws SQLException {
        try {
            String sql = "INSERT INTO Users(username, password, fullname, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateUser(User user) throws SQLException {
        try {
            String sql = "UPDATE Users SET password=?, fullname=?, email=? WHERE user_id=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Un user existent a fost modificat");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Long id) throws SQLException {
        try {
            String sql = "DELETE FROM Users WHERE user_id=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() throws SQLException {

        try {

            List<User> users = new ArrayList<>();

            String sql = "SELECT user_id, userName, password, fullName, email from Users";
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                Long id = result.getLong(1);
                String userName = result.getString(2);
                String userPassword = result.getString(3);
                String fullName = result.getString("fullname");
                String email = result.getString("email");

                User user = new User();
                user.setId(id);
                user.setUserName(userName);
                user.setPassword(userPassword);
                user.setFullName(fullName);
                user.setEmail(email);

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new RuntimeException("error retrieving all users", e);
        }
    }

    public User getUser(Long id) throws SQLException {
        User user = null;
        try {
            String sql = "SELECT user_id, userName, password, fullName, email from Users where user_id=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                String userName = result.getString(2);
                String userPassword = result.getString(3);
                String fullName = result.getString("fullname");
                String email = result.getString("email");
                user.setUserName(userName);
                user.setPassword(userPassword);
                user.setFullName(fullName);
                user.setEmail(email);
            }
            if (result.next()) {
                throw new RuntimeException("more than 1 users found for id = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("exception getting  user with id = " + id, e);
        }
        return user;
    }

}
