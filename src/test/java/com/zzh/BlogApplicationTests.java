package com.zzh;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    private static final String salt = "180406zzh!@#";
    @Test
    void contextLoads() {

        String password = DigestUtils.md5Hex("zzh200410"+ salt);
        System.out.println(password);

    }
}