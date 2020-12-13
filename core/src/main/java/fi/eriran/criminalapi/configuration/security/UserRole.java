package fi.eriran.criminalapi.configuration.security;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;

public enum UserRole {
    INVESTIGATOR(Sets.newEnumSet(
            Arrays.asList(UserPermission.USER_READ, UserPermission.USER_MODIFY, UserPermission.CRIMINAL_READ),
            UserPermission.class
    )),
    SUPPORT(Sets.newEnumSet(
            Arrays.asList(UserPermission.USER_CREATE),
            UserPermission.class
    ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
