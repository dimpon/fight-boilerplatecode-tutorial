package com.dimpon.tutorals.lombok.miscellaneous;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CleanupMe {

    String path;

    @SneakyThrows(IOException.class)
    public void loadData() {
        log.info("Scan folder...");

        @Cleanup
        Stream<Path> stream = Files.find(Paths.get(path), 1, (path1, basicFileAttributes) -> true);
        stream.sorted(Comparator.naturalOrder())
                .forEach(this::readOneFile);

    }

    private void readOneFile(Path path) {
        log.info(path.toString());
    }


}
