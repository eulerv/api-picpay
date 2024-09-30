package com.github.eulerv.picpaydesafiobackend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("wallets")
public record Wallet(
        @Id Long id,
        Long userId,
        String fullName,
        Long cpf,
        String email,
        String password,
        int type,
        BigDecimal balance) {

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, userId, fullName, cpf, email, password, type, balance.subtract(value));
    }
    public Wallet credit(BigDecimal value) {
        return new Wallet(id, userId,fullName, cpf, email, password, type, balance.add(value));
    }
}