package com.dimpon.tutorals.lombok.miscellaneous;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class CleanupExample {

    public static void main(String[] args) throws IOException {

        @Cleanup
        InputStream in = new FileInputStream("pom.xml");

        @Cleanup
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            log.info(line);
        }

    }
}
