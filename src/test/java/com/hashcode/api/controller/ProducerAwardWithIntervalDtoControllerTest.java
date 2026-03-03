package com.hashcode.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerAwardWithIntervalDtoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String readExpectedJson() throws Exception {
        ClassPathResource resource = new ClassPathResource("expected-producer-interval.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.lines().reduce("", (a, b) -> a + b);
        }
    }

    private String getHeader() throws Exception {
        ClassPathResource resource = new ClassPathResource("data/Movielist.csv");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.readLine();
        }
    }

    @Test
    @DisplayName("Deve validar a estrutura das colunas")
    void mustValidateCsvColumns() throws Exception {
        String header = getHeader();
        assertThat(header).isNotNull();

        String[] expectedColumns = {
                "year",
                "title",
                "studios",
                "producers",
                "winner"
        };

        String[] actualColumns = header.split(";");
        assertThat(Arrays.asList(actualColumns)).containsExactlyInAnyOrder(expectedColumns);
    }

    @Test
    @DisplayName("Deve validar o separador CSV")
    void mustValidateCsvSeparator() throws Exception {
        String header = getHeader();
        final String SEPARATOR = ";";
        assertThat(header).contains(SEPARATOR);
    }


    @Test
    @DisplayName("Deve retornar exatamente o resultado baseado no CSV padrão")
    void mustReturnExactResultBasedOnDefaultCsv() throws Exception {
        String url = "http://localhost:" + port + "/producers/award-interval";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String expectedJson = readExpectedJson();
        assertThat(response.getBody())
                .isNotNull()
                .isEqualToIgnoringWhitespace(expectedJson);
    }
}
