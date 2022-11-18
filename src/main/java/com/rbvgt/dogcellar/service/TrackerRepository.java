package com.rbvgt.dogcellar.service;

import com.rbvgt.dogcellar.domain.TrackerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepository extends JpaRepository<TrackerInfo,Long> {
}
