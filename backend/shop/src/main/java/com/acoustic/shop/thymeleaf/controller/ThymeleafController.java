package com.acoustic.shop.thymeleaf.controller;

import com.acoustic.shop.product.dto.ProdDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

    @GetMapping(value = "/ex1")
    public String ex1(Model model) {
        Point p = new Point(10, 20);
        model.addAttribute("data", p);
        return "thymeleaf/ex1";
    }

    @GetMapping(value = "/ex2")
    public String ex2(Model model) {

        ProdDto prodDto = new ProdDto();
        prodDto.setProdName("테스트 상품 1");
        prodDto.setProdDesc("상품 상세 설명");
        prodDto.setProdPrice(10000);
        prodDto.setProdStock(100);
        prodDto.setProdCreated(LocalDateTime.now());

        model.addAttribute("prodDto", prodDto);

        return "thymeleaf/ex2";
    }

    @GetMapping(value = {"/ex3", "/ex4"})
    public void ex3(Model model) {

        List<ProdDto> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
        ProdDto prodDto = new ProdDto();
        prodDto.setProdName("테스트 상품" + i);
        prodDto.setProdDesc("상품 상세 설명" + i);
        prodDto.setProdPrice(10000 * i);
        prodDto.setProdStock(100);
        prodDto.setProdCreated(LocalDateTime.now());
        list.add(prodDto);

        }
        model.addAttribute("list", list);

    }
}
