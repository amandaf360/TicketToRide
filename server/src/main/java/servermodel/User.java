package servermodel;

public class User implements java.io.Serializable
{
    private String username;
    private String password;
    private String authToken;

    public User(String username, String password, String authToken)
    {
        this.username = username;
        this.password = password;
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public byte[] getSerializadVersion()
    {
        return new byte[2];
    }
}
