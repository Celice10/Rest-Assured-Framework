package utils;

import org.json.JSONObject;


public class PayloadBuilder{



    public static JSONObject loginUserPayload(String email, String password) {
        JSONObject data = new JSONObject();

        data.put("email", email);
        data.put("password", password);

        return data;
    }

    public static JSONObject registerUserPayload(String firstName, String lastName, String email, String password, String confirmPassword, String groupId) {

        JSONObject data = new JSONObject();

        data.put("firstName", firstName);
        data.put("lastName", lastName);
        data.put("email", email);
        data.put("password", password);
        data.put("confirmPassword", confirmPassword);
        data.put("groupId", groupId);

        return data;

    }


    public static JSONObject approveUserPayload(String userId, String status) {
        JSONObject data = new JSONObject();

        data.put("userId", userId);
        data.put("status", status);

        return data;
    }

    public static JSONObject updateUserRolePayload(String userId, String newRole) {
        JSONObject data = new JSONObject();

        data.put("userId", userId);
        data.put("role", newRole);

        return data;
    }

    public static JSONObject deleteUserPayload(){

        return new JSONObject();
    }
}
