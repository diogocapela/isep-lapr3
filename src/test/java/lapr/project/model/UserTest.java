package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pushdword
 */
class UserTest {

    private User u1;
    private User u2;
    private User u3;
    private User u4;

    private Bike b1 = new Bike();

    UserTest() {
        Locale.setDefault(new Locale("pt", "PT"));
        u1 = new User();
        u1.setEmail("");
        u1.setHashedSaltedPassword("");
        u1.setCreditCardNumber("");
        u1.setHeight(Double.MAX_VALUE);
        u1.setWeight(Double.MAX_VALUE);
        u1.setCreditPoints(Integer.BYTES);
        u1.setIsAdmin(false);

        u2 = null;

        u3 = new User();
        u3.setIdUser(5);
        u3.setEmail("coisas@batatas.es");
        u3.setHashedSaltedPassword("");
        u3.setCreditCardNumber("");
        u3.setHeight(Double.MAX_VALUE);
        u3.setWeight(Double.MAX_VALUE);
        u3.setCreditPoints(Integer.BYTES);
        u3.setIsAdmin(false);

        u4 = new User();
        u4.setIdUser(99);

    }

    @Test
    void testGetEmail() {
        u1.setEmail("cenas@com.pt");
        assertEquals(u1.getEmail(), "cenas@com.pt");
    }


    @Test
    void testGetPassword() {
        u1.setPassword("12345678");
        Assertions.assertTrue(u1.checkValidPassword("12345678"));
    }


    @Test
    void testIsIsAdmin() {
        u1.setIsAdmin(true);
        Assertions.assertTrue(u1.isIsAdmin());
    }

    @Test
    void testGetCreditCardNumber() {
        u1.setCreditCardNumber("12345678");
        assertEquals(u1.getCreditCardNumber(), "12345678");
    }


    @Test
    void testGetHeight() {
        u1.setHeight(Double.MAX_VALUE);
        Assertions.assertTrue(u1.getHeight() == Double.MAX_VALUE);
    }

    @Test
    void testGetWeight() {
        u1.setWeight(Double.MAX_VALUE);
        Assertions.assertTrue(u1.getWeight() == Double.MAX_VALUE);
    }

    @Test
    void testGetCreditPoints() {
        u1.setCreditPoints(Integer.BYTES);
        Assertions.assertTrue(u1.getCreditPoints() == Integer.BYTES);
    }


    @Test
    void testGetIdUser() {
        u1.setIdUser(Integer.SIZE);
        Assertions.assertTrue(u1.getIdUser() == Integer.SIZE);
    }

    @Test
    void testSalt() {
        byte[] salt = User.getNextSalt();
        byte[] notexpected = new byte[16];
        Assertions.assertNotEquals(notexpected, salt);

    }

    @Test
    void testSKF() {
        User uz = new User();
        uz.setEmail("cenas@teste.pt");
        uz.setHashedSaltedPassword("encryptedPassword");
        uz.setCreditCardNumber("123");
        uz.setHeight(Double.MAX_VALUE);
        uz.setWeight(Double.MAX_VALUE);
        uz.setCreditPoints(Integer.BYTES);
        uz.setIsAdmin(false);
        Assertions.assertEquals("PBKDF2WithHmacSHA1", uz.getSkf());
        Throwable exception = assertThrows(AssertionError.class, () -> {
            uz.setSkf("cenasquenaoexistem");
            uz.setPassword("123");
        });
        assertEquals("Error while hashing a password: cenasquenaoexistem SecretKeyFactory not available", exception.getMessage());
        uz.setSkf("PBKDF2WithHmacSHA1");

    }

    @Test
    void toStringTest() {
        String exp = "Mail:cenas@com.pt" + " ID:32";
        assertNotEquals(exp, u1.toString());

        u1.setEmail("cenas@com.pt");
        u1.setIdUser(32);
        assertEquals(exp, u1.toString());
    }


    @Test
    void hashCodeTest() {
        u1.setEmail("cenas@com.pt");
        u1.setIdUser(32);
        assertNotEquals(u1.hashCode(), u3.hashCode());

        u1.setEmail("coisas@batatas.es");
        assertEquals(u1.hashCode(), u3.hashCode());
    }


    @Test
    void equalsTest() {
        assertEquals(u1, u1);
        assertNotEquals(u1, u2);
        assertNotEquals(u1, u3);
        assertNotEquals(u2, u3);
        assertNotEquals(u1, b1);

        u2 = new User();
        u2.setEmail("coisas@batatas.es");
        u2.setHashedSaltedPassword("");
        u2.setCreditCardNumber("");
        u2.setHeight(Double.MAX_VALUE);
        u2.setWeight(Double.MAX_VALUE);
        u2.setCreditPoints(Integer.BYTES);
        u2.setIsAdmin(false);
        assertNotEquals(u1, u2);
        assertEquals(u2, u3);
    }

    @Test
    void addCreditPointsTest() {
        assertTrue(u4.getCreditPoints() == 0);
        Integer expectedIncr = 5;
        u4.addCreditPoints(expectedIncr);
        assertEquals(expectedIncr, u4.getCreditPoints());

        u4.addCreditPoints(expectedIncr);
        expectedIncr += expectedIncr;
        assertEquals(expectedIncr, u4.getCreditPoints());
    }
}
