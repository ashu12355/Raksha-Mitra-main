package com.rakshamitra.RakshaMitra.forms;

import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "College Name is required")
    private String collegeName;

    @NotBlank(message = "College Person Name is required")
    private String collegePersonName;

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

    @NotBlank(message = "College Address is required")
    private String collegeAddress;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;
    private boolean approved;
}
