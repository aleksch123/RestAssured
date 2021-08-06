package com.example.mint.constants;

public class Constants {
    public static class RunVariable {
        public static String server= Servers.ILEDECASINO_URL;
        public static String path= "";
    }
    public static class Servers {
        public static String ILEDECASINO_URL= "https://development-iledecasino.com/";

    }
    public static class Path {
        public static String API_PATH="api/";

    }

    public static class Actions {
        public static String CHANGE_FAKE_NAME="Account.changeFakename";
        public static String CHANGE_PASSWORD="Account.changePassword";
    }

}
