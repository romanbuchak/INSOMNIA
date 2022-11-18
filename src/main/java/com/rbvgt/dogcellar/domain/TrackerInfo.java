package com.rbvgt.dogcellar.domain;

import com.rbvgt.dogcellar.mapper.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracker_info")
@Entity(name = "tracker_info")
public class TrackerInfo  implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
}
