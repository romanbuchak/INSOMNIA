package com.rbvgt.dogcellar.service;

import com.rbvgt.dogcellar.model.TrackerInfoDto;
import java.io.IOException;
import java.util.Collection;

public interface TrackerInfoService {
    TrackerInfoDto create(TrackerInfoDto dto);
    TrackerInfoDto update(TrackerInfoDto dto,Long id);
    TrackerInfoDto findById(Long id);
    Collection<TrackerInfoDto> findAll();
    void deleteById(Long id);
    void deleteAll();
    void saveTrackerInfo() throws IOException;
}