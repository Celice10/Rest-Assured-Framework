package utils;

public class TokenManager {

    private static String token;  //store current token

    public static void setToken(String newToken) {
        token = newToken;
    }
    public static String getToken() {
        return token;
    }
}
