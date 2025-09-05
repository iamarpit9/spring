package com.example.demo.controller;

import com.example.demo.service.AutocompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final AutocompleteService autocompleteService;

    @GetMapping("/autocomplete")
    public List<Long> autocomplete(@RequestParam String prefix,
                                   @RequestParam(defaultValue = "5") int k) {
        return autocompleteService.suggest(prefix, k);
    }
}
