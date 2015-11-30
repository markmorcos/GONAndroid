package com.mem.gon.helpers;

import com.mem.gon.models.User;

/**
 * Created by mark on 30/11/15.
 */
public class Session {

    private static User user;

    public static void create(User user) {
        Session.user = user;
    }

    public static void destroy() {
        user = null;
    }

    public static boolean isUserSignedIn() {
        return user != null;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
}
