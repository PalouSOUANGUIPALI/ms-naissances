package com.asp_dev.naissances.declaration;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("declarations")
public class DeclarationController {
    private final DeclarationService declarationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeclarationDto> search(String query) {
        return this.declarationService.search();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Declaration declaration) {
        this.declarationService.create(declaration);
    }

    @PatchMapping(path = "{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@PathVariable int id, @RequestBody Map<String, String> parameters) {
        this.declarationService.updateStatus(id, parameters);

    }
}
