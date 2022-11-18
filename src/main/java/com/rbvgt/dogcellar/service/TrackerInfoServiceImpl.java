package com.rbvgt.dogcellar.service;

import com.rbvgt.dogcellar.domain.TrackerInfo;
import com.rbvgt.dogcellar.mapper.DtoConverter;
import com.rbvgt.dogcellar.model.TrackerInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import javax.persistence.EntityNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TrackerInfoServiceImpl implements TrackerInfoService {
    private final static String ID_CANNOT_BE_NULL = "Id cannot be null";
    private final static String GIVEN_DTO_CANNOT_BE_NULL = "Given dto cannot be null";
    private final static String ENTITY_NOT_FOUND = "Entity with id %s doesnt exist";
    private static int countOfSavedTrackerInfo =2;

    protected final DtoConverter<TrackerInfo, TrackerInfoDto> converter = new DtoConverter(new ModelMapper());

    private final TrackerRepository repository;

    @Autowired
    public TrackerInfoServiceImpl(TrackerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public TrackerInfoDto create(TrackerInfoDto dto) {
        Assert.notNull(dto, ID_CANNOT_BE_NULL);
        TrackerInfo createdInfo = repository.save(converter.convertToEntity(dto, TrackerInfo.class));

        return converter.convertToDto(createdInfo, TrackerInfoDto.class);
    }

    @Override
    @Transactional
    public TrackerInfoDto update(TrackerInfoDto dto, Long id) {
        Assert.notNull(dto, GIVEN_DTO_CANNOT_BE_NULL);
        Assert.notNull(id, ID_CANNOT_BE_NULL);

        TrackerInfo foundInfo = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, id)));
        converter.update(dto, foundInfo);

        TrackerInfo updatedInfo = repository.save(foundInfo);

        return converter.convertToEntity(updatedInfo, TrackerInfoDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TrackerInfoDto findById(Long id) {
        return repository.findById(id)
                .map(trackerInfo -> converter.convertToDto(trackerInfo, TrackerInfoDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<TrackerInfoDto> findAll() {
        return repository.findAll()
                .stream()
                .map(trackerInfo -> converter.convertToDto(trackerInfo, TrackerInfoDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void saveTrackerInfo() throws IOException {
        TrackerInfoDto trackerInfoDto = new TrackerInfoDto();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/info.txt"));
        String line;
        int innerCounter =0;
        while ((line = reader.readLine()) != null) {

            if (line.startsWith("latitude and longitude")) {
                String []lines = line.split(" ");
                trackerInfoDto.setLatitude(Double.parseDouble(lines[4]));
                trackerInfoDto.setLongitude(Double.parseDouble(lines[6]));
            }
            if (line.startsWith("date and time")) {
                trackerInfoDto.setDateTime(parseTime(line));
            }
            if (line.startsWith("---") && countOfSavedTrackerInfo>innerCounter){
                innerCounter++;
            }
            if (line.startsWith("---") && (countOfSavedTrackerInfo <= innerCounter)){
                TrackerInfoDto createdInfo =  this.create(trackerInfoDto);
                countOfSavedTrackerInfo++;
            }
        }
        reader.close();
    }

    private LocalDateTime parseTime(String line) {
        String[] lines = line.split(" ");
        String[] stringDate = lines[4].split("/");
        int dateOfMonth = Integer.parseInt(stringDate[0]);
        int month = Integer.parseInt(stringDate[1]);
        int year = Integer.parseInt(stringDate[2]);
        String[] stringTime = lines[5].split(":");
        int hours = Integer.parseInt(stringTime[0]);
        int minutes = Integer.parseInt(stringTime[1]);
        int seconds = Integer.parseInt(stringTime[2]);
        return LocalDateTime.of(year, month, dateOfMonth, hours, minutes, seconds, Integer.parseInt(lines[6]));
    }
}
