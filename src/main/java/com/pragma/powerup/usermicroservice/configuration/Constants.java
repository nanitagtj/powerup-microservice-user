package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.InvalidNameException;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long CLIENT_ROLE_ID = 4L;
    public static final Long EMPLOYEE_ROLE_ID = 3L;
    public static final Long OWNER_ROLE_ID = 2L;
    public static final Long ADMIN_ROLE_ID = 1L;
    public static final String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public static final String ROLE_OWNER_NAME = "ROLE_OWNER";
    public static final int MAX_PAGE_SIZE = 2;
    public static final String CREDENTIALS = "Credentials";
    public static final String AUTHENTICATION_ERROR = "authentication_error";
    public static final String BAD_REQUEST = "Bad request";
    public static final String RESOURCE = "resource";
    public static final String DATA_NOT_FOUND_ERROR = "data_not_found_error";
    public static final String VALIDATION_FAILS = "validation fails";
    public static final String INVALID_NAME_EXCEPTION = "Invalid name";
    public static final String INVALID_MAIL_EXCEPTION =  "Invalid mail exception";
    public static final String INVALID_PHONE_EXCEPTION =  "Invalid phone exception";
    public static final String INVALID_DNI_EXCEPTION =  "Invalid Dni exception";
    public static final String INVALID_PASSWORD_EXCEPTION =  "Invalid Password exception";
    public static final String INVALID_BIRTHDATE_EXCEPTION =  "Invalid Birthdate exception";
    public static final String USER_BIRTHDATE =  "Birthdate";
    public static final String UNAUTHORIZED_EXCEPTION = "Unauthorized";
    public static final String MAIL = "mail";
    public static final String  ROLE = "role";
    public static final String  USER = "user";
    public static final String  AGE = "age";
    public static final String ADMIN_NAME = "Admin";
    public static final String OWNER_NAME = "Owner";
    public static final String EMPLOYEE_NAME = "Employee";
    public static final String CLIENT_NAME = "Client";
    public static final String USER_NAME = "User";
    public static final String USER_MAIL = "Email";
    public static final String USER_PHONE = "Phone";
    public static final String USER_DNI = "Dni";
    public static final String USER_PASSWORD = "Password";

    public static final String ADMIN_DESCRIPTION = "Admin";
    public static final String OWNER_DESCRIPTION = "Owner";
    public static final String EMPLOYEE_DESCRIPTION = "Employee";
    public static final String CLIENT_DESCRIPTION = "Client";
    public static final String INVALID_DESCRIPTION = "Invalid user type: ";
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String PERSON_CREATED_MESSAGE = "Person created successfully";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String USER_DELETED_MESSAGE = "User deleted successfully";
    public static final String USER_UPDATED_MESSAGE = "User updated successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials or role not allowed";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String INVALID_AGE_EXCEPTION = "Invalid age, must be +18";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the dni provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
