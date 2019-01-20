package lapr.project.utils;

import lapr.project.data.UsersAPI;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthManagerTest {

    private AuthManager authManager;
    private UsersAPI usersAPI;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUser(1);
        user.setEmail("test@example.com");
        user.setHashedSaltedPassword("123");
        user.setWeight(23.2);
        user.setHeight(23.2);
        user.setCreditCardNumber("2321312312");
        user.setCreditPoints(123);
        usersAPI = mock(UsersAPI.class);
        authManager = new AuthManager(usersAPI);
        when(usersAPI.getUserById(1)).thenReturn(user);
        when(usersAPI.getUserByEmail("test@example.com")).thenReturn(user);
    }

    @Test
    void testAuthManager() {
        AuthManager authManager = AuthManager.getInstance();
        AuthManager authManagerClone = AuthManager.getInstance();
        assertEquals(authManager, authManagerClone);
    }

    @Test
    void testLoginById() {
        boolean failedLogin = authManager.login(1, "wrongPass");
        boolean successLogin = authManager.login(1, "123");

        assertFalse(failedLogin);
        assertTrue(successLogin);
        assertTrue(authManager.isLoggedIn());
    }

    @Test
    void testLoginByEmail() {
        user.setPassword("123");
        assertFalse(authManager.login("test@example.com", "31nbnfbd232112"));
        assertTrue(authManager.login("test@example.com", "123"));
    }

    @Test
    void testLogout() {
        assertTrue(authManager.logout());
    }

    @Test
    void testGetUserById() {
        boolean successLogin = authManager.login(1, "123");
        User user1 = authManager.getLoggedInUser();
        assertEquals(user1, user);
    }

    @Test
    void testIsAdmin() {
        assertFalse(authManager.isAdmin());
        user.setIsAdmin(true);
        boolean successLogin = authManager.login(1, "123");
        assertTrue(authManager.isAdmin());
    }

}
