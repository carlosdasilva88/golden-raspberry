package com.hashcode.application.dto;

public record ProducerAwardWithIntervalDto(String producer, Integer interval, Integer previousWin, Integer followingWin) {
}
