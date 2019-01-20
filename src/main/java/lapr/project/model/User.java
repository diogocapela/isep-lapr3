package lapr.project.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class User {

    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static String sKf = "PBKDF2WithHmacSHA1";
    private Integer idUser = null;
    private String username;
    private String email;
    private String hashedSaltedPassword;
    private String creditCardNumber;
    private Double height;
    private Double weight;
    private Integer creditPoints;
    private Double avgSpeed;
    private Boolean isAdmin;

    public User() {
        // empty constructor
        hashedSaltedPassword = getSaltString();
        creditCardNumber = "";
        height = 150.0;
        weight = 50.0;
        creditPoints = 0;
        avgSpeed = 10.0;
        isAdmin = false;
    }

    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    protected static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Returns a salted and hashed password using the provided hash.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with
     * zeros)
     *
     * @param password the password to be hashed
     * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
     * @return the hashed password with a pinch of salt
     */
    protected static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(sKf);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false
     * otherwise.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with
     * zeros)
     *
     * @param password     the password to check
     * @param salt         the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     * @return true if the given password and salt match the hashed value, false
     * otherwise
     */
    protected static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) {
                return false;
            }
        }
        return true;
    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    protected final String getSaltString() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();

    }

    public String getSkf() {
        return sKf;
    }

    public void setSkf(String secretKeyFactory) {
        sKf = secretKeyFactory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password FROM DATABASE!
     *
     * @return : Hashed and salted password in String format
     */
    public String getHashedSaltedPassword() {
        return hashedSaltedPassword;
    }

    /**
     * Set the password AS IS to DATABASE!
     *
     * @param hashedSaltedPassword : salted hashed password
     */
    public void setHashedSaltedPassword(String hashedSaltedPassword) {
        this.hashedSaltedPassword = hashedSaltedPassword;
    }

    /**
     * Set new password!
     *
     * @param password
     */
    public void setPassword(String password) {
        byte[] newSalt = getNextSalt();
        byte[] newHash = hash(password.toCharArray(), newSalt);
        this.hashedSaltedPassword = bytesToHex(newSalt) + "$" + bytesToHex(newHash);
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }

    public void addCreditPoints(int creditPoints) {
        if (this.creditPoints == null) {
            this.creditPoints = creditPoints;
        } else {
            this.creditPoints = this.creditPoints + creditPoints;
        }
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Double getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Verify if given password is valid on the database
     *
     * @param givenPassword : clear password
     * @return : True of password is valid or false if it's wrong
     */
    public boolean checkValidPassword(String givenPassword) {
        byte[] salt;
        byte[] hash;

        String[] spl = hashedSaltedPassword.split("\\$");
        salt = hexStringToByteArray(spl[0]);
        hash = hexStringToByteArray(spl[1]);

        return isExpectedPassword(givenPassword.toCharArray(), salt, hash);
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("Mail:").append(this.email).append(" ID:").append(idUser);
        return sbf.toString();
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.email, other.email);
    }


}
