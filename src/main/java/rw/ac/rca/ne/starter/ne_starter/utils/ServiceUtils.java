package rw.ac.rca.ne.starter.ne_starter.utils;


import rw.ac.rca.ne.starter.ne_starter.enums.EVisibility;
import rw.ac.rca.ne.starter.ne_starter.models.User;

public class ServiceUtils {
    // method to check if a user is valid or deleted
    public static boolean isUserDeleted(User user) {
        return user.getVisibility().equals(EVisibility.VOIDED);
    }
}
