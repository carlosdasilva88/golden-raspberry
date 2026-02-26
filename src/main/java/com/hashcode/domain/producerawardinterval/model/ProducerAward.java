package com.hashcode.domain.producerawardinterval.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerAward {
    String producer;
    Integer interval;
    Integer previousYearWin;
    Integer followingYearWin;
}
