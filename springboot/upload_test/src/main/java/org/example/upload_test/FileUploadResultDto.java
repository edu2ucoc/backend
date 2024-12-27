package org.example.upload_test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FileUploadResultDto {
    private int code;
    private String message;

}
