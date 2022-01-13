package ch.bbbaden.lohnabrechner.model;

import java.io.Serializable;

/**
 *
 * @author Metehan
 */

public class User implements Serializable{
    private int id;
    private String name;
    private String password;
    private boolean isAdminLogin;
    
    public User(int id, String name, String password, boolean isAdminLogin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isAdminLogin = isAdminLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAdminLogin() {
        return isAdminLogin;
    }

    public void setIsAdminLogin(boolean isAdminLogin) {
        this.isAdminLogin = isAdminLogin;
    }
}
