package com.menu.Model;
/**
 * Created by Doston Hamrakulov doston.hamrakulov@gmail.com on 5/10/2017.
 */

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    public User(){

    }
    public User(int _userId,String _userName,String _userEmail,String _userPassword){
        userId = _userId;
        userName = _userName;
        userEmail = _userEmail;
        userPassword = _userPassword;
    }

    public User(String name, String email, String password) {
        userName = name;
        userEmail = email;
        userPassword = password;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



}
