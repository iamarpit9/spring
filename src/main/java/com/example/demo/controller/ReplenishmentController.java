package com.example.demo.controller;

import com.example.demo.service.ReplenishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replenishment")
@RequiredArgsConstructor
public class ReplenishmentController {

    private final ReplenishmentService replenishmentService;

    // GET /api/v1/replenishment/next?limit=5
    @GetMapping("/next")
    public List<ReplenishmentService.Item> getNext(
            @RequestParam(defaultValue = "5") int limit) {
        return replenishmentService.next(limit);
    }
}
