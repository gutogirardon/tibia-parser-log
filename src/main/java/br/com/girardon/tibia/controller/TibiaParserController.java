package br.com.girardon.tibia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tibia")
public class TibiaParserController {

    @GetMapping("/logs")
    public String showParsedTibiaLogs() {
        return "Tibia logs parsed successfully!";
    }
}
