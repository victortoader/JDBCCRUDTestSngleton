package aplicatie.user;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 10/27/2015.
 */
public interface UserDao {

    void insertUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(Long id) throws SQLException;
    User getUser(Long id) throws SQLException;
    List<User> getUsers() throws SQLException;
}
