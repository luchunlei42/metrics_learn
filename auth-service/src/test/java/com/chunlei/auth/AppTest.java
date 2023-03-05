package com.chunlei.auth;

import com.netflix.discovery.converters.Auto;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.ws.soap.Addressing;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
