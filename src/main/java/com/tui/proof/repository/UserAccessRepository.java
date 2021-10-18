package com.tui.proof.repository;

import com.tui.proof.model.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

    public UserAccess findByUsernameAndPasswordAndEnabled(String username, String password, boolean enabled);

}
