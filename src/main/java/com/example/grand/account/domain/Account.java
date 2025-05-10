package com.example.grand.account.domain;

import com.example.grand.common.domain.BaseEntity;
import com.example.grand.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @PositiveOrZero
    @Column(name = "balance")
    private BigDecimal balance;

    @NotNull
    @PositiveOrZero
    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

}