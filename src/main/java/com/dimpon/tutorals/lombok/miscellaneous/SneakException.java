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
public class SneakException {

    String path;

    @SneakyThrows(IOException.class)
    public void loadData() {
        log.info("Scan folder...");

        Stream<Path> stream = Files.find(Paths.get(path), 1, (p, a) -> true);

        stream.sorted(Comparator.naturalOrder())
                .forEach(this::readOneFile);

    }

    private void readOneFile(Path path) {
        log.info(path.toString());
    }


    ////////////////////////


    public static void main(String[] args) {
        SneakException me = new SneakException("./src/main/java/com/dimpon/tutorals");
        me.loadData();
    }


}
