package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
class TlisaWebApplicationTests {

    /**
     * 测试JWT生成
     */
    @Test
    public void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 15);
        claims.put("username", "Tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "HelloWorld")//签名算法
                .setClaims(claims)//载荷部分
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置令牌有效期（ms）
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJWT(){

        Claims claims= Jwts.parser()
                .setSigningKey("HelloWorld")//设置算法密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTUsImV4cCI6MTcyMDM0MzYyMiwidXNlcm5hbWUiOiJUb20ifQ.zA457p6cVjwl_LJ780eNyLoEcnJ_VW4934KAAhGohAc")
                .getBody();
        System.out.println(claims);
    }



    private static String signKey = "abc";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    @Test
    public void generateJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 15);
        claims.put("username", "Tom");
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        System.out.println(jwt);

    }


}
