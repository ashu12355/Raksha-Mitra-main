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
@Table(name = "college")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RescueAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String collegeName;
    private String collegePersonName;
    private String contactNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private String collegeAddress;
    private String city;
    private String state;
    private boolean approved = false;
}
