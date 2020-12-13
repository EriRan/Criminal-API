package fi.eriran.criminalapi.configuration.security;

public enum UserPermission {
    CRIMINAL_READ("criminal:read"),
    CRIMINAL_MODIFY("criminal:modify"),
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_MODIFY("user:modify");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
