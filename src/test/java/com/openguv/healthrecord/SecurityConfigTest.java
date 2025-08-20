package com.openguv.healthrecord;

import com.openguv.healthrecord.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Test
    void filterChain_ShouldBeConfigured() throws Exception {
        SecurityFilterChain filterChain = securityConfig.filterChain(null);
        assertNotNull(filterChain);
    }
}