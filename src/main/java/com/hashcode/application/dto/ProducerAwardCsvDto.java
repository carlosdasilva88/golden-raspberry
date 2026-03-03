package com.hashcode.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProducerAwardCsvDto(Integer year, String title, String studios, String producers, String winner) {
}
