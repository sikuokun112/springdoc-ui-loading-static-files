package com.trinhtantai.springdocui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class SwaggerController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public SwaggerController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/test1")
    public ResponseEntity<String> getSwaggerYaml() throws IOException {
        var resource = resourceLoader.getResource("classpath:/static/partner-sns-api.yaml");
        var content = new String(Files.readAllBytes(Paths.get(resource.getURI())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/yaml"));
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @GetMapping("/folder/{folder}/file/{fileName}")
    public ResponseEntity<String> getApiSpec(@PathVariable("folder") String folder,
                                             @PathVariable("fileName") String fileName) throws IOException {
        String basePath = "specmatic/open-api-contract/contracts";
        String fullPath = basePath + "/" + folder + "/" + fileName;

        String projectRoot = System.getProperty("user.dir");
        Path filePath = Paths.get(projectRoot, fullPath);
        if (!Files.exists(filePath)) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        var content = new String(Files.readAllBytes(filePath));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/yaml"));
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
