package com.sti.cmart.util;

// Lớp này trả về trạng thái hoạt động:exception, đường dẫn, link dẫn tới client
public class Constants {
    public static final String API_VERSION = "/v1";

    public static final String BASE_URL = "/api" + API_VERSION;

    public static final class Paths {
        public static final String APP_PATH = BASE_URL + "/c-mart";
    }

    public static final class Exception {
        public static final class Common {
            public static final String INVALID_PARAM_CODE = "000001";
            public static final String INVALID_PARAM = "Invalid request params";
        }
    }

    public static final class Entity {
        public static final String ENTITY_NOT_FOUND_CODE = "000100";
        public static final String ENTITY_NOT_FOUND = "Entity not found";
        public static final String ENTITY_ALREADY_EXIST_CODE = "000101";
        public static final String ENTITY_ALREADY_EXIST = "Entity already exist";

    }

    public static final class Client {
        public static final String BASE_URL = "https://api/v1/c-mart/";
    }

}
