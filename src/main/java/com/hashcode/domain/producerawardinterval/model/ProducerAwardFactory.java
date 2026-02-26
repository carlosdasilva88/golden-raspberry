package com.hashcode.domain.producerawardinterval.model;

public final class ProducerAwardFactory {

    public static ProducerAward build(String producer, Integer interval, Integer previousYearWin, Integer followingYearWin) {
        ProducerAward producerAward = new ProducerAward();
        producerAward.setProducer(producer);
        producerAward.setInterval(interval);
        producerAward.setPreviousWin(previousYearWin);
        producerAward.setFollowingWin(followingYearWin);
        return producerAward;
    }
}
