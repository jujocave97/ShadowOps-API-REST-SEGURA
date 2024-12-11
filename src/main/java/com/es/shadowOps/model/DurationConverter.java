package com.es.shadowOps.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

// esta clase no se si seria un util como tal
// esta clase realiza la conversion para que la base de datos entienda la clase Duration de java
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration != null ? duration.toSeconds() : null;
    }

    @Override
    public Duration convertToEntityAttribute(Long seconds) {
        return seconds != null ? Duration.ofSeconds(seconds) : null;
    }
}