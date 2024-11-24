package org.wooteco.pre.convenienceStore.util;

import org.wooteco.pre.convenienceStore.exception.CustomIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {
    private static final String FILE_ERROR = "읽을 파일이 없습니다.";

    public static List<String> readFile(final String filePath) {
        List<String> fileData;
        try {
            fileData = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new CustomIOException(FILE_ERROR);
        }
        return fileData;
    }
}
