package com.hashcode.infrastructure.persistence.produceraward;

import com.hashcode.application.dto.ProducerAwardDto;

import java.util.ArrayList;
import java.util.List;

public final class ProducerAwardModelFactory {

    public static List<ProducerAwardModel> build(List<ProducerAwardDto> producerAwardCsvDtoList) {
        List<ProducerAwardModel> producerAwardModelList = new ArrayList<>();
        for(ProducerAwardDto producerAwardDto : producerAwardCsvDtoList) {
            ProducerAwardModel producerAwardModel = new ProducerAwardModel();
            producerAwardModel.setYear(producerAwardDto.year());
            producerAwardModel.setTitle(producerAwardDto.title());
            producerAwardModel.setStudios(producerAwardDto.studios());
            producerAwardModel.setProducers(producerAwardDto.producers());
            producerAwardModel.setWinner((producerAwardDto.winner()));
            producerAwardModelList.add(producerAwardModel);
        }
        return producerAwardModelList;
    }

    public static List<ProducerAwardDto> buildEntityToModel(List<ProducerAwardModel> producerAwardModel) {
        List<ProducerAwardDto> producerAwardDto = new ArrayList<>();
        for(ProducerAwardModel movies : producerAwardModel) {
            producerAwardDto.add(new ProducerAwardDto(movies.getYear(), movies.getTitle(), movies.getStudios(), movies.getProducers(), movies.getWinner()));
        }
        return producerAwardDto;
    }
}
