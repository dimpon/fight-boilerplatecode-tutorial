package com.dimpon.tutorals.jetty;

import com.dimpon.tutorals.concurrent.Throttling;
import com.google.common.util.concurrent.RateLimiter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.net.HttpHeaders.USER_AGENT;

/**
 * @author Dmitrii Ponomarev
 */
public class Load {

    final static RateLimiter rateLimiter = RateLimiter.create(2);

	public static void main(String[] args) throws Exception {

		List<Callable<Integer>> callables = IntStream.range(0, 10000).mapToObj(value -> (Callable<Integer>) () -> {
            rateLimiter.acquire();
			doCall();
			return 1;
		}).collect(Collectors.toList());

		final ExecutorService executor = Executors.newFixedThreadPool(20);
		executor.invokeAll(callables);
		executor.shutdown();
	}

	@SneakyThrows
	private static void doCall() {

		////////////

        String url = "http://localhost:8080";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
	}
}
