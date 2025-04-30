package io.helidon.examples.quickstart.mp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickAnalytics {
    private String linkId;
    private Date timestamp;
    private String userAgent;
    private String ipAddress;}
