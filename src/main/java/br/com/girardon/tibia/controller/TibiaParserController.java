package br.com.girardon.tibia.controller;

import br.com.girardon.tibia.service.TibiaParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tibia")
public class TibiaParserController {

    private final TibiaParserService tibiaParserService;

    public TibiaParserController(TibiaParserService tibiaParserService) {
        this.tibiaParserService = tibiaParserService;
    }

    @GetMapping("/logs")
    public ResponseEntity<TibiaParserService> parseLog() {
        TibiaParserService result = tibiaParserService.parseLogFile();
        return ResponseEntity.ok(result);
    }
}
