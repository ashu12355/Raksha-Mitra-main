package com.rakshamitra.RakshaMitra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rescue_agencies")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RescueAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agencyName;
    private String contactPersonName;
    private String contactNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private String agencyLocation;
    private double latitude;
    private double longitude;
    private String specialization;
    private int teamSize;
    private String availability;
    private String description;
    private boolean approved = false;

}
