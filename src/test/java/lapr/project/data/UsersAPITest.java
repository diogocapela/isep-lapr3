package lapr.project.data;

class UsersAPITest {

//   private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
//   private static final String DATABASE_USERNAME = "bddad_na4_B";
//   private static final String DATABASE_PASSWORD = "qwerty";
//
//   private static UsersAPI usersAPI;
//
//    @BeforeAll
//    static void start() {
//        usersAPI = new UsersAPI(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        /*DataHandler dataHandler = new DataHandler(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        try {
//            new Utils().runTestScript(dataHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//    }
//
//    @BeforeEach
//    void testSetup() {
//
//    }
//
//    /**
//     * Test getUserById
//     */
//    @Test
//    void testGetUserById() {
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idUser = usersAPI.addUser(user);
//        user.setIdUser(idUser);
//        User user2 = usersAPI.getUserById(idUser);
//        assertEquals(user, user2);
//        usersAPI.removeUserById(idUser);
//    }
//
//    /**
//     * Test getUserByEmail
//     */
//    @Test
//    void testGetUserByEmail() {
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idUser = usersAPI.addUser(user);
//        user.setIdUser(idUser);
//        User user2 = usersAPI.getUserByEmail("target_email@gmail.com");
//        assertEquals(user, user2);
//        usersAPI.removeUserById(idUser);
//    }
//
//    /**
//     * Test getAllUsers, addUser and removeUserById
//     */
//    @Test
//    void testGeneralUsersById() {
//        int sizeBefore = usersAPI.getAllUsers().size();
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idAddedUser = usersAPI.addUser(user);
//        Integer idRemovedUser = usersAPI.removeUserById(idAddedUser);
//        int sizeAfter = usersAPI.getAllUsers().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Test getAllUsers, addUser and removeUserByEmail
//     */
//    @Test
//    void testGeneralUsersByEmail() {
//        int sizeBefore = usersAPI.getAllUsers().size();
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idAddedUser = usersAPI.addUser(user);
//        Integer idRemovedUser = usersAPI.removeUserByEmail("target_email@gmail.com");
//        Logger.log(idAddedUser);
//        Logger.log(idRemovedUser);
//        int sizeAfter = usersAPI.getAllUsers().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Update
//     */
//  @Test
//   void testUpdateUser() {
//       User user = new User();
//       user.setEmail("target_email@gmail.com");
//       user.setHashedSaltedPassword("123");
//       user.setCreditCardNumber("123455678");
//       user.setHeight(1.8);
//       user.setWeight(80.0);
//       user.setCreditPoints(10);
//       user.setIsAdmin(false);
//       Integer idUser = usersAPI.addUser(user);
//       user = usersAPI.getUserById(idUser);
//       String email = "123@gmail.com";
//       user.setEmail(email);
//       idUser = usersAPI.updateUser(user);
//       user = usersAPI.getUserById(idUser);
//       assertEquals(email, user.getEmail(), "testUpdateUser");
//       usersAPI.removeUserById(idUser);
//   }

}
