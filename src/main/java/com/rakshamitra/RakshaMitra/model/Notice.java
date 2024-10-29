package com.rakshamitra.RakshaMitra.model;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String eventType;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime; 
    private LocalTime endTime; 
    private String title;
    private String address;
    private String registrationLink;
    private String description;
    private Double registrationFee;
   
    private Long agencyId; // Reference to the rescue agency (college)



   
}
