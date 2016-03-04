package aplicatie.user;

import aplicatie.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 10/27/2015.
 */
public class UserDaoHibernate implements UserDao {
    @Override
    public void insertUser(User user) throws SQLException {
        HibernateUtils.getSession().save(user);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        HibernateUtils.getSession().update(user);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        HibernateUtils.getSession()
                .createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return HibernateUtils.getSession()
                .createQuery("from User u")
                .list();
    }

    @Override
    public User getUser(Long id) throws SQLException {
        return (User) HibernateUtils.getSession().get(User.class, id);
    }
}
