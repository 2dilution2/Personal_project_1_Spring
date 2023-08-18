package com.acoustic.shop.test.controller;

import com.acoustic.shop.test.dto.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/test")
    public TestDto test() {
        TestDto dto = new TestDto();
        dto.setName("홍길동");
        dto.setAge(10);
        return dto;
    }


}
