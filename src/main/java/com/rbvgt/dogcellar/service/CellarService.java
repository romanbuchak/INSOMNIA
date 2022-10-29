package com.rbvgt.dogcellar.service;

import com.rbvgt.dogcellar.model.Cellar;
import com.rbvgt.dogcellar.repository.CellarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CellarService {
    private final CellarRepository cellarRepository;

    @Autowired
    public CellarService(CellarRepository cellarRepository) {
        this.cellarRepository = cellarRepository;
    }

    public List<Cellar> getAll() {
        return cellarRepository.findAll();
    }

    public Cellar getById(Integer id) {
        return cellarRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void create(Cellar cellar) {
        cellarRepository.save(cellar);
    }

    @Transactional
    public void update(Integer id, Cellar cellar) {
        Cellar newBoxes = cellarRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newBoxes.setLatitude(cellar.getLatitude());
        newBoxes.setLongitude(cellar.getLongitude());
        newBoxes.setData(cellar.getData());
    }

    public void delete(Integer id) {
        cellarRepository.deleteById(id);
    }
}
