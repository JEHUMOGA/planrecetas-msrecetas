package com.recipiesplan.recipies.dto.output;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class Meta {
    private UUID transacionID;
    private String status;
    private Integer statusCode;
    private String timestamp;

    public Meta(HttpStatus status, HttpStatusCode statusCode, String timestamp) {
        this.transacionID = UUID.randomUUID();
        this.status = status.name();
        this.statusCode = statusCode.value();  
        this.timestamp = timestamp;
    }
}
