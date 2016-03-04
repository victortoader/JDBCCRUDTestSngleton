package aplicatie.user;

/**
 * Created by Administrator on 9/15/2015.
 */
public class User {

    private Long id;
    private String userName;
    private String password;
    private String fullName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getFullName() != null ? !getFullName().equals(user.getFullName()) : user.getFullName() != null)
            return false;
        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);

    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        }
        int result = id.intValue();
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "aplicatie.model.User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +

                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
