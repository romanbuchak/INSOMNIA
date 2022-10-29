package com.rbvgt.dogcellar.controller;

import com.rbvgt.dogcellar.model.Cellar;
import com.rbvgt.dogcellar.service.CellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/cellar")
public class CellarController {
    private final CellarService cellarService;

    @Autowired
    public CellarController(CellarService cellarService) {
        this.cellarService = cellarService;
    }

    @GetMapping("/get")
    private List<Cellar> getAll() {
        return cellarService.getAll();
    }

    //@CrossOrigin
    @GetMapping("get/{id}")
    private Cellar getById(@PathVariable("id") Integer id) {
        return cellarService.getById(id);
    }

    //@CrossOrigin
    @PostMapping
    public void create(@RequestBody Cellar cellar) {
        cellarService.create(cellar);
    }

    //@CrossOrigin
    @PutMapping("/update/{id}")
    private void update(@PathVariable("id") Integer id, @RequestBody Cellar cellar) {
        cellarService.update(id, cellar);
    }

    //@CrossOrigin
    @DeleteMapping("/delete/{id}")
    private void delete(@PathVariable("id") Integer id) {
        cellarService.delete(id);
    }
}
