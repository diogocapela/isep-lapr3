package lapr.project.utils;

import lapr.project.data.UsersAPI;
import lapr.project.model.User;

public class AuthManager {

    private static AuthManager uniqueInstance = null;

    private UsersAPI usersAPI;
    private User loggedInUser = null;

    private AuthManager() {
        usersAPI = new UsersAPI();
    }

    public AuthManager(UsersAPI usersAPI) {
        this.usersAPI = usersAPI;
    }

    public static AuthManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new AuthManager();
        }
        return uniqueInstance;
    }

    public boolean login(int id, String password) {
        User user = usersAPI.getUserById(id);
        if (password.equals(user.getHashedSaltedPassword())) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public boolean login(String email, String password) {
        User user = usersAPI.getUserByEmail(email);
        if (user.checkValidPassword(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public boolean logout() {
        loggedInUser = null;
        return true;
    }

    public boolean isAdmin() {
        if (loggedInUser != null) {
            return loggedInUser.isIsAdmin();
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

}
