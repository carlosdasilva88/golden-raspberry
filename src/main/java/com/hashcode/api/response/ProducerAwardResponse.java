package com.hashcode.api.response;

import com.hashcode.application.dto.ProducerAwardWithIntervalDto;

import java.util.List;

public record ProducerAwardResponse(List<ProducerAwardWithIntervalDto> min, List<ProducerAwardWithIntervalDto> max) {
}
