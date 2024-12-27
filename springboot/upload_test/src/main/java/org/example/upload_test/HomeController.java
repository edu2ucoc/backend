package org.example.upload_test;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Controller 처리
 *  - @Slf4j : 롬복의 로그 처리 어노테이션
 */
@Slf4j
@Controller
public class HomeController {
    // 1. 업로드 페이지
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 2. ajax 업로드,  톰켓 내부 저장
    // 일반데이터 + 파일 동시 전송
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<FileUploadResultDto> upload(
            MemberDto member, // 나이, 이름, 주소 정보를 통으로 받아서 전달
            @RequestParam("uploadFile") MultipartFile uploadFile, // 파일을 받아서 전달
            HttpServletRequest req) {

        // 0. 일반 데이터 -> age, name, addr 확인
        System.out.println(member.toString());

        // 1. 파일 저장할 경로 획득 (여기서는 톰켓내부, 실제는 클라우드의 스토리지 선택)
        //    스프링부트가 작동할려면 s/w 필요 (was(Web Application Server) 서버)
        //    was 서버에 *.jar 파일을 배포되야 서버가 작동!!
        //    was 서버 : tomcat, ..., 인텔리J는 톰켓 내장하고 있음
        String path     = req.getServletContext().getRealPath(""); // 저장한 위치(서버측)
        System.out.println("path:" + path);
        String filename = uploadFile.getOriginalFilename();	// 파일명
        System.out.println("filename:" + filename);

        // 2. 저장 -> 클라우드상의 스토리지에 저장 권장
        try {
            // I/O를 이용하여 파일 저장
            File file = new File(path + "/" + filename);
            BufferedOutputStream bos =  new BufferedOutputStream( new FileOutputStream(file) );
            bos.write(uploadFile.getBytes());
            bos.close();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body( FileUploadResultDto.builder()
                    .code(-1).message(e.getMessage()).build() );
        }
        // 3. 결과 응답 -> dto -> json
        return ResponseEntity.status(HttpStatus.OK).body( FileUploadResultDto.builder()
                .code(1).message(filename).build() );
    }
}
