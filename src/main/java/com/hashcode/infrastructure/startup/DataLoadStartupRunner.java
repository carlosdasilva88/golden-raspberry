package com.hashcode.infrastructure.startup;


import com.hashcode.application.usecase.LoadAwardFromCsvUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class DataLoadStartupRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoadStartupRunner.class);
    private final LoadAwardFromCsvUseCase loadAwardFromCsvUseCase;

    @Value("${data.csv.path}")
    private String csvFilePath;

    public DataLoadStartupRunner(LoadAwardFromCsvUseCase loadAwardFromCsvUseCase) {
        this.loadAwardFromCsvUseCase = loadAwardFromCsvUseCase;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Carregamento dos dados do arquivo csv inicializado");
        loadAwardFromCsvUseCase.execute(Paths.get(csvFilePath));
        log.info("Carregamento dos dados do arquivo csv finalizado");
    }
}
