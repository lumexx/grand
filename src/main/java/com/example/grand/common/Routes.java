package com.example.grand.common;

public interface Routes {
    String URI_SEPARATOR = "/";
    String UUID_PATH_VARIABLE = "uuid";
    String UUID_PATH_VARIABLE_FULL = "{" + UUID_PATH_VARIABLE + "}";

    String USERS = "users";

    String LOGIN = "login";

    String PHONES = "phones";
    String PHONES_UUID = PHONES + URI_SEPARATOR + UUID_PATH_VARIABLE_FULL;

    String EMAILS = "emails";
    String EMAILS_UUID = EMAILS + URI_SEPARATOR + UUID_PATH_VARIABLE_FULL;

    String TRANSFER = "transfer";


}
