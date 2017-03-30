package com.wish.pay.web.controller;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@EnableAutoConfiguration
public class MainController {

    @RequestMapping("/")
    String index() {
        return "index";
    }
}