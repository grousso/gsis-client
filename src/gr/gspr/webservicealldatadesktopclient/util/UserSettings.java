/*
 *Apache License, Version 2.0
 */
package gr.gspr.webservicealldatadesktopclient.util;

import java.math.BigInteger;

/**
 *
 * @author j.vlachos
 */
public class UserSettings implements java.io.Serializable{
    private static final long serialVersionUID = 1L;


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
    String username;
    String password;
    
    public UserSettings(String userun, String userpass) {
        username=userun;
        password=userpass;
    }
    
}
