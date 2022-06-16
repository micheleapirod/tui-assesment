package com.tui.proof.service;

import com.tui.proof.exception.TuiUserException;
import com.tui.proof.model.UserAccess;
import com.tui.proof.repository.UserAccessRepository;
import com.tui.proof.utils.AppUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class UserSearchConfigurationService {

    private final UserAccessRepository userAccessRepository;

    @Autowired
    public UserSearchConfigurationService(UserAccessRepository userAccessRepository) {
        this.userAccessRepository = userAccessRepository;
    }

    @Transactional(readOnly = true)
    public Boolean isEnabled(HttpHeaders headers) throws TuiUserException {
        AppUtils.checkHttpParameters(headers);
        String username = headers.get(AppUtils.USER_LABEL).get(0);
        String password = headers.get(AppUtils.PASSWORD_LABEL).get(0);
        UserAccess userAccess = userAccessRepository.findByUsernameAndPasswordAndEnabled(username, password, true);
        return userAccess != null && StringUtils.isNotBlank(userAccess.getUsername()) && BooleanUtils.isTrue(userAccess.isEnabled());
    }

    @PostConstruct
    protected void createUserAdmin() {
        UserAccess userAccess = UserAccess.builder()
                .enabled(true)
                .password("password")
                .username("admin")
                .token(UUID.randomUUID().toString())
                .build();
        userAccessRepository.save(userAccess);
    }

}
