package com.tui.proof

import com.tui.proof.exception.TuiUserException
import com.tui.proof.model.UserAccess
import com.tui.proof.repository.UserAccessRepository
import com.tui.proof.service.UserSearchConfigurationService
import org.springframework.http.HttpHeaders
import spock.lang.Specification

class TuiConfigurationUserServiceTest extends Specification{
    
    def userAccessRepository = Mock(UserAccessRepository);
    def searchConfigurationService = new UserSearchConfigurationService(userAccessRepository)
    
    def "can user make a query "() {
        given:
            HttpHeaders headers = new HttpHeaders();
            headers.put("user", Arrays.asList("admin"))
            headers.put("password", Arrays.asList("password"))
        when:
            Boolean result = searchConfigurationService.isEnabled(headers);
        then:
            1 * userAccessRepository.findByUsernameAndPasswordAndEnabled(_ as String, _ as String, true) >> UserAccess.builder()
                                                                                                                    .username("admin")
                                                                                                                    .password("password")
                                                                                                                    .enabled(true)
                                                                                                                    .build()
            result
    }
    
    def "can user make query without http header"() {
        given:
            HttpHeaders headers = null
        when:
            Boolean result = searchConfigurationService.isEnabled(headers);
        then:
           thrown TuiUserException
    }
    
    def "can user make query user not enabled"() {
        given:
            HttpHeaders headers = new HttpHeaders();
            headers.put("user", Arrays.asList("admin"))
            headers.put("password", Arrays.asList("password"))
        when:
            Boolean result = searchConfigurationService.isEnabled(headers);
        then:
            1 * userAccessRepository.findByUsernameAndPasswordAndEnabled(_ as String, _ as String, true) >> null
            !result
    }
}
