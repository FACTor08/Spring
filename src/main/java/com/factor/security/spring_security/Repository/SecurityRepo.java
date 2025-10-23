package com.factor.security.spring_security.Repository;

import com.factor.security.spring_security.Model.Security;
import com.factor.security.spring_security.Model.Web;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityRepo extends JpaRepository<Security, Long> {
    Optional<Security> findByUserName(String userName);

}
