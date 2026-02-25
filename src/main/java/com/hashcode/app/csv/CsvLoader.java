package com.hashcode.app.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.hashcode.movie.domain.model.MovieList;
import com.hashcode.movie.domain.service.MovieListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvLoader {

    private static final Logger log = LoggerFactory.getLogger(CsvLoader.class);

    @Value("${data.csv.path}")
    private String csvFilePath;

    @Autowired
    MovieListService movieListService;

    public void run() throws IOException {
        Path path = Paths.get(csvFilePath);
        this.loadFile(path);
    }

    private void loadFile(Path path) {
        log.info("Leitura do arquivo csv {} inicializada", path);
        List<MovieList> movieList = this.readCsv(path);
        log.info("Persistindo os dados");
        movieListService.save(movieList);
    }

    private List<MovieList> readCsv(Path path) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
        try (Reader reader = Files.newBufferedReader(path)){
            MappingIterator<MovieList> mappingIterator = csvMapper
                    .readerFor(MovieList.class)
                    .with(csvSchema)
                    .readValues(reader);
            return mappingIterator.readAll();
        } catch (IOException e) {
            log.error("Erro ao processar arquivo CSV: {}", path, e);
            throw new RuntimeException(e);
        }
    }
}
