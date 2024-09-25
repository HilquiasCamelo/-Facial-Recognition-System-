package com.hilquiascamelo.facialrecognitionsystem.web;

import com.hilquiascamelo.facialrecognitionsystem.application.RecognitionService;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class RecognitionController {

    private final RecognitionService recognitionService;

    public RecognitionController(RecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    @PostMapping("api/recognize")
    public ResponseEntity<Boolean> recognize(@RequestParam("image") MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return ResponseEntity.badRequest().body(false); // Retorna 400 se a imagem não for fornecida
        }
        try {
            // Salvar a imagem em um diretório temporário
            Path tempFile = Files.createTempFile("upload-", image.getOriginalFilename());
            Files.copy(image.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Carregar a imagem usando OpenCV
            Mat inputImage = opencv_imgcodecs.imread(tempFile.toString());

            // Verifica se a imagem foi carregada corretamente
            if (inputImage.empty()) {
                return ResponseEntity.status(400).body(false); // Retorna 400 se a imagem não pôde ser carregada
            }

            // Reconhecer a imagem
            boolean isRecognized = recognitionService.recognizeImage(inputImage);
            return ResponseEntity.ok(isRecognized);
        } catch (IOException e) {
            // Log e trate a exceção de I/O
            return ResponseEntity.status(500).body(false);
        } catch (Exception e) {
            // Log e trate qualquer outra exceção
            return ResponseEntity.status(500).body(false);
        }
    }
}
