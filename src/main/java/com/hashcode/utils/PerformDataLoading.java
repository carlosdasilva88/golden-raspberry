package com.hashcode.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PerformDataLoading implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(PerformDataLoading.class);

    @Autowired
    private CsvLoader csvLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Carregamento dos dados do arquivo csv inicializado");
        csvLoader.run();
        log.info("Carregamento dos dados do arquivo csv finalizado");
    }
}
