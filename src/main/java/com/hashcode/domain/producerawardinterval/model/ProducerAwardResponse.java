package com.hashcode.domain.producerawardinterval.model;

import java.util.List;

public record ProducerAwardResponse(List<ProducerAward> min, List<ProducerAward> max) {
}
