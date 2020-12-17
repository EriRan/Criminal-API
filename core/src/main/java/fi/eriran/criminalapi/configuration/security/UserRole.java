package fi.eriran.criminalapi.configuration.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    INVESTIGATOR(Sets.newEnumSet(
            Arrays.asList(UserPermission.USER_READ, UserPermission.USER_MODIFY, UserPermission.CRIMINAL_READ),
            UserPermission.class
    )),
    SUPPORT(Sets.newEnumSet(
            Arrays.asList(UserPermission.USER_CREATE, UserPermission.USER_READ, UserPermission.USER_MODIFY),
            UserPermission.class
    ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ " + this.name()));
        return grantedAuthorities;
    }
}
