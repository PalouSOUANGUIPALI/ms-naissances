package com.asp_dev.naissances.declaration;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("declarations")
public class DeclarationController {
    private final DeclarationService declarationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Declaration> search(String query) {
        return this.declarationService.search();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Declaration declaration) {
        this.declarationService.create(declaration);
    }
}
