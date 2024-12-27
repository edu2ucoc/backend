package org.example.upload_test;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Rest API
 */
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    // 프로젝트 메인/uploads
    private static final String UPLOAD_DIR = "uploads/";

    public FileUploadController() {
        // 업로드 디렉토리 생성
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/upload")
    // 파일을 받을대 @RequestParam("file") MultipartFile file
    // file 이라는 변수명은 name="file" 이므로 사용함
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("파일이 비어 있습니다.");
        }
        try {
            // 경로 획득 : UPLOAD_DIR + 파일명
            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            // 파일 기록 -> 향후는 클라우드의 저장소 사용 권장 (aws s3), 여기서는 로컬 저장 처리
            // 스토리지에 업로드 -> URL 발생된다 -> 링크를 관리, 클라이언트가 클릭 -> 다운로드 진행됨
            Files.write(filePath, file.getBytes());

            // 응답
            // ResponseEntity : 응답 프로토콜 구조를 직접 구성한다면 사용!!
            return ResponseEntity.status(HttpStatus.OK)
                    .body("파일 업로드 성공: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일 업로드 실패: " + e.getMessage());
        }
    }
}