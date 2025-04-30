package io.helidon.examples.quickstart.mp.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickAnalytics {
    private String linkId;
    private Date timestamp;
    @NotBlank(message = "userAgent must not be blank")
    @Size(max = 1024, message = "userAgent is too long")
    private String userAgent;
    @NotBlank(message = "ipAddress must not be blank")
    @Size(max = 256, message = "ipAddress is too long")
    private String ipAddress;
}
