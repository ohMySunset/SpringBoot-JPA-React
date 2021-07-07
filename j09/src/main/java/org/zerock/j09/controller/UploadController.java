package org.zerock.j09.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.j09.dto.UploadResultDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {

    @Value("${spring.servlet.multipart.location}")
    private String path;


    @ResponseBody
    @GetMapping(value = "/down")
    public ResponseEntity<byte[]> down(String file) { // 파일 다운로드
        File target = new File(path, file);

        String mimeType = null;

        try {
            // 타입을 알아낼 수 있음.
            mimeType = Files.probeContentType(target.toPath());

            if (mimeType.startsWith("image") == false) {
                mimeType = "application/octet-stream";
            }

            byte[] arr = FileCopyUtils.copyToByteArray(target);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @ResponseBody
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UploadResultDTO> upload(MultipartFile[] files) {

        //log.warn(path);

        List<UploadResultDTO> result = new ArrayList<>();

        for (MultipartFile file : files) {

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            // 저장할 경로, 저장할 이름 정의
            File outFile = new File(path, uuid + "_" + fileName);
            File thumbFile = new File(path, "s_" + uuid + "_" + fileName);

            try {

                InputStream fin = file.getInputStream();

                // 들어온 파일을 path에 저장
                Files.copy(fin, outFile.toPath());

                // BufferedImage : 메모리 상에 있는 이미지
                BufferedImage originalImg = ImageIO.read(outFile);

                BufferedImage thumbnail = Thumbnails.of(originalImg)
                        .size(100, 100)
                        .asBufferedImage();

                ImageIO.write(thumbnail, "JPG", thumbFile);

                fin.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            result.add(UploadResultDTO.builder().uuid(uuid).fileName(fileName).build());

        }//end for

        return result;
    }


}
