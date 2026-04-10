package api;

//Utility class to store endpoint paths in one place
public class Endpoints {
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String APPROVE_USER = "/admin/users/{newUserId}/approve";
    public static final String GET_USER = "/profile";
    public static final String UPDATE_USER = "/admin/users/{userId}/role";
    public static final String DELETE_USER = "/admin/users/{userId}";
}
