package com.hashcode.application.usecase;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.hashcode.application.dto.ProducerAwardCsvDto;
import com.hashcode.application.dto.ProducerAwardDto;
import com.hashcode.infrastructure.persistence.produceraward.ProducerAwardModelFactory;
import com.hashcode.infrastructure.persistence.produceraward.ProducerAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoadAwardFromCsvUseCase {

    private static final Logger log = LoggerFactory.getLogger(LoadAwardFromCsvUseCase.class);
    private final ProducerAwardRepository producerAwardRepository;

    public LoadAwardFromCsvUseCase(ProducerAwardRepository producerAwardRepository) {
        this.producerAwardRepository = producerAwardRepository;
    }

    public void execute(Path path) {
        List<ProducerAwardCsvDto> producerAwardCsvDtoList = readCsv(path);
        List<ProducerAwardDto> producerAwardDtoListNormalized = normalizeData(producerAwardCsvDtoList);
        producerAwardRepository.saveAll(ProducerAwardModelFactory.build(producerAwardDtoListNormalized));
    }

    public void execute(MultipartFile file) {
        List<ProducerAwardCsvDto> producerAwardCsvDtoList = readCsvFromWeb(file);
        List<ProducerAwardDto> producerAwardDtoListNormalized = normalizeData(producerAwardCsvDtoList);
        producerAwardRepository.saveAll(ProducerAwardModelFactory.build(producerAwardDtoListNormalized));
    }

    private List<ProducerAwardDto> normalizeData(List<ProducerAwardCsvDto> movieList) {
        List<ProducerAwardDto> response = new ArrayList<>();
        for(ProducerAwardCsvDto movie : movieList) {
            List<String> producers = Arrays.stream(movie.producers().split(",| and "))
                    .map(String::trim)
                    .filter(producer -> !producer.isBlank())
                    .collect(Collectors.toList());

            for (String producer : producers) {
                Boolean isWinner = "yes".equals(movie.winner());
                response.add(new ProducerAwardDto(movie.year(), movie.title(), movie.studios(), producer, isWinner));
            }
        }
        return response;
    }

    private List<ProducerAwardCsvDto> readCsvFromWeb(MultipartFile file) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
        try (Reader reader = new InputStreamReader(file.getInputStream())){
            MappingIterator<ProducerAwardCsvDto> mappingIterator = csvMapper
                    .readerFor(ProducerAwardCsvDto.class)
                    .with(csvSchema)
                    .readValues(reader);
            return mappingIterator.readAll();
        } catch (IOException e) {
            log.error("Erro ao processar arquivo CSV: {}", file.getName(), e);
            throw new RuntimeException(e);
        }
    }

    private List<ProducerAwardCsvDto> readCsv(Path path) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
        try (Reader reader = Files.newBufferedReader(path)){
            MappingIterator<ProducerAwardCsvDto> mappingIterator = csvMapper
                    .readerFor(ProducerAwardCsvDto.class)
                    .with(csvSchema)
                    .readValues(reader);
            return mappingIterator.readAll();
        } catch (IOException e) {
            log.error("Erro ao processar arquivo CSV: {}", path, e);
            throw new RuntimeException(e);
        }
    }
}
