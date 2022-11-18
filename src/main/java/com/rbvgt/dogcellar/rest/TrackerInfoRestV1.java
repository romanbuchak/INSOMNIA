package com.rbvgt.dogcellar.rest;

import com.rbvgt.dogcellar.model.Groups;
import com.rbvgt.dogcellar.model.TrackerInfoDto;
import com.rbvgt.dogcellar.service.TrackerInfoService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/tracker/pet")
public class TrackerInfoRestV1 {

    private final TrackerInfoService trackerInfoService;

    @Autowired
    public TrackerInfoRestV1(TrackerInfoService trackerInfoService) {
        this.trackerInfoService = trackerInfoService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackerInfoDto getById(@PathVariable final Long id) {
        return trackerInfoService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<TrackerInfoDto> getAll() {
        return trackerInfoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackerInfoDto create(final @RequestBody TrackerInfoDto address) {
        return trackerInfoService.create(address);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TrackerInfoDto update(final @RequestBody @Validated(value = Groups.UPDATE.class) TrackerInfoDto updateAddress) {
        return trackerInfoService.update(updateAddress, updateAddress.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull final Long id) {
        trackerInfoService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        trackerInfoService.deleteAll();
    }

}