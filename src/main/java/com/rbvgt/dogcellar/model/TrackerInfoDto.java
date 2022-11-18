package com.rbvgt.dogcellar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rbvgt.dogcellar.mapper.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackerInfoDto implements Convertible {
    @NotNull(groups = Groups.UPDATE.class, message = "Id field for updated shouldn`t be null")
    @Nullable
    private Long id;
    @NotNull(message = "Longitude cannot be null")
    private Double longitude;
    @NotNull(message = "Latitude cannot be null")
    private Double latitude;
    @NotNull(message = "Date time cannot be null")
    private LocalDateTime dateTime;
}
