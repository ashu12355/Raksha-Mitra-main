package com.rakshamitra.RakshaMitra.forms;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RescueAgencyForm {

    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Minimum 5 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Username must not contain numbers or special characters")
    private String agencyName;

    @NotBlank(message = "Contact Person Name is required")
    private String contactPersonName;

    @Size(min = 10, max = 10)
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact Number must be numeric and exactly 10 digits")
    private String contactNumber;

    @Email(message = "Invalid Email")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
             message = "Password must contain at least one letter, one number, and one special character")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;

    @NotBlank(message = "Agency Location is required")
    private String agencyLocation;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;


    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Digits(integer = 2, fraction = 0, message = "Team Size must be a non-negative integer")
    @Min(value = 0, message = "Team Size must be at least 0")
    private Integer teamSize;

    @NotBlank(message = "Availability is required")
    private String availability;

    @NotBlank(message = "Description is required")
    private String description;

    private boolean approved;
}
