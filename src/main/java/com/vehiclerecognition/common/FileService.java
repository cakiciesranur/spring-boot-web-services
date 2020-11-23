package com.vehiclerecognition.common;

import com.vehiclerecognition.common.exception.EmptyFileException;
import com.vehiclerecognition.common.exception.ReadFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    private static final Logger logger = LogManager.getLogger(FileService.class);

    @Value("${vehicle.file-path}")
    private String filePath;

    public List<String> readFile() throws ReadFileException, EmptyFileException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> lineList = lines.collect(Collectors.toList());
            if (lineList.isEmpty()) {
                throw new EmptyFileException();
            }
            return lineList;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadFileException(e);
        }
    }
}
