package com.example.grand.user.domain;

import com.example.grand.common.Constant;
import com.example.grand.common.domain.BaseEntity;
import com.example.grand.email.domain.EmailData;
import com.example.grand.phone.domain.PhoneData;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    public static final int USER_NAME_MAX_LENGTH = 500;
    public static final int USER_PASSWORD_MAX_LENGTH = 500;

    @Size(max = USER_NAME_MAX_LENGTH)
    @Column(name = "name", length = 500)
    private String name;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = USER_PASSWORD_MAX_LENGTH)
    @Length(min = 8, max = 500)
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneData> phones;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailData> emails;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.emails.stream().map(EmailData::getEmail)
                .collect(Collectors.joining(Constant.USERNAME_SPLIT_SYMBOL));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
