package com.dimpon.tutorals.jetty;

import com.dimpon.tutorals.concurrent.Throttling;
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

/**
 * @author Dmitrii Ponomarev
 */
public class Load {

	public static void main(String[] args) throws Exception {

		List<Callable<Integer>> callables = IntStream.range(0, 1000000).mapToObj(value -> (Callable<Integer>) () -> {
			doCall();
			return 1;
		}).collect(Collectors.toList());

		final ExecutorService executor = Executors.newFixedThreadPool(200);
		executor.invokeAll(callables);
		executor.shutdown();
	}

	@SneakyThrows
	private static void doCall() {
		URL url = new URL("http://localhost:8080");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		content.append(con.getResponseCode());

		con.disconnect();

		System.out.println(content.toString());
	}
}
