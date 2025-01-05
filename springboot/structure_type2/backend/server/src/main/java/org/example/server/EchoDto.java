package org.example.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EchoDto {
    private int code;
    private String message;
}
