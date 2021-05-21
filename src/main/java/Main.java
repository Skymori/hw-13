import com.google.gson.Gson;
import user.Address;
import user.Company;
import user.Geo;
import user.User;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final Gson GSON = new Gson();
    public static final int DEFAULT_USER_ID = 1;
    public static final String DEFAULT_USER_NAME = "Calvin Kline";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Create new User");
        User userCreate = createDefaultUser();
        User createUser = GSON.fromJson(HttpClientUtil.createUser(userCreate), User.class);
        System.out.println("createdUser " + createUser);

        printText();

        System.out.println("Update User");
        User updateUser = new User();
        updateUser.setName("New User Name");
        updateUser.setEmail(createUser.getEmail());
        updateUser.setWebsite(createUser.getWebsite());
        updateUser.setAddress(createUser.getAddress());
        updateUser.setPhone(createUser.getPhone());
        updateUser.setCompany(createUser.getCompany());

        String st = HttpClientUtil.updateUser(DEFAULT_USER_ID, updateUser);
        User checkUpdateUser = GSON.fromJson(st, User.class);
        System.out.println(checkUpdateUser);

        printText();

        System.out.println("Delete User");
        createUser.setId(DEFAULT_USER_ID);
        System.out.println("Deleted Operation" + HttpClientUtil.deleteUser(createUser));

        printText();

        System.out.println("Get All Users");
        List<User> allUsers = HttpClientUtil.getAllUsers();
        allUsers.forEach(System.out::println);

        printText();

        System.out.println("Get User Id = 1");
        System.out.println("User Id 1 = "+ HttpClientUtil.getUserInfoId(DEFAULT_USER_ID));

        printText();

        System.out.println("Get User Name Calvin Kline");
        System.out.println("User get Name ="+ HttpClientUtil.getUserName(DEFAULT_USER_NAME));

        printText();

        System.out.println("Get all Comments User Id = 1: ");
        System.out.println(HttpClientUtil.getAllCommit(createUser));

        printText();

        System.out.println("Get all open Task User Id 1: ");
        List<Task> allOpenTasks = HttpClientUtil.getListTaskUser(createUser);
        allOpenTasks.forEach(System.out::println);
    }
    private static User createDefaultUser() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setName("Den");
        user.setUsername("Braun");
        user.setEmail("Den@gmail.com");
        user.setAddress(crateDefaultAddress());
        user.setPhone("937-99-92");
        user.setWebsite("www.dart.org.ua");
        user.setCompany(createDefaultCompany());
        return user;
    }

    private static Address crateDefaultAddress(){
        Address address = new Address();
        address.setStreet("baker street");
        address.setSuite("221b");
        address.setCity("New York");
        address.setZipcode("323-323");
        Geo geo = new Geo();
        geo.setLat("50");
        geo.setLng("30");
        address.setGeo(geo);
        return address;
    }
    private static Company createDefaultCompany(){
        Company company = new Company();
        company.setName("Star Death");
        company.setCatchPhrase("Winter Is Coming");
        company.setBs("time to go");
        return company;
    }
    private static void printText(){
        System.out.println("****************");
    }
}
