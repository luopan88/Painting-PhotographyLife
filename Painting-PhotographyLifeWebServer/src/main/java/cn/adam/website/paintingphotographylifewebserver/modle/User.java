package cn.adam.website.paintingphotographylifewebserver.modle;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User implements UserDetails {
    private static final long serialVersionUID = -7120261322343848808L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "昵称为空")
    private String name;
    @Email(message = "请输入正确的邮箱")
    @NotNull(message = "邮箱为空")
    private String username;
    @NotNull(message = "密码为空")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities = new HashSet<>();
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        authorities.add("EverOne");
    }

    public User(String name, String username, String password, Set<String> authorities) {
        this(name, username, password, authorities, true, true, true, true);
    }
    public User(String name, String username, String password, String... authorities) {
        this(name, username, password, Arrays.asList(authorities), true, true, true, true);
    }

    public User(@NotNull(message = "昵称为空") String name,
                @Email(message = "请输入正确的邮箱") @NotNull(message = "邮箱为空") String username,
                @NotNull(message = "密码为空") String password,
                Collection<String> authorities,
                Boolean accountNonExpired,
                Boolean accountNonLocked,
                Boolean credentialsNonExpired,
                Boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities.addAll(authorities);
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        authorities.add("EverOne");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
