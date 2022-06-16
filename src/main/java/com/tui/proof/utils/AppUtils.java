package com.tui.proof.utils;

import com.tui.proof.exception.TuiUserException;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    public final static String USER_LABEL = "user";
    public final static String PASSWORD_LABEL = "password";

    public static void checkHttpParameters(HttpHeaders headers) throws TuiUserException {
        boolean isFullHeader = headers != null;
        List<String> adminList = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();

        if(isFullHeader) {
            adminList = headers.get(USER_LABEL);
            passwordList = headers.get(PASSWORD_LABEL);
        }

        if(!isFullHeader || CollectionUtils.isEmpty(adminList) || CollectionUtils.isEmpty(passwordList)) {
            throw new TuiUserException();
        }
    }

}
