package com.finnect.user.application.port.out;

public interface ExistsUserPort {

    boolean existsUserByUsername(String Username);

    boolean existsUserByEmail(String email);
}
