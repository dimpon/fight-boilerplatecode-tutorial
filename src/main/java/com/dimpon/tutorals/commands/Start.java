package com.dimpon.tutorals.commands;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

	public static void main(String[] args) {

		Pair<String, String> pair1 = Pair.of("1", "One 1");
		Pair<String, String> pair2 = Pair.of("1", "One 2");
		Pair<String, String> pair3 = Pair.of("2", "Two 1");
		Pair<String, String> pair4 = Pair.of("2", "Two 2");
		Pair<String, String> pair5 = Pair.of("3", "Three 1");
		Pair<String, String> pair6 = Pair.of("3", "Three 2");

		Map<String, Optional<? extends Pair>> map = new HashMap<>();


        Optional<ImmutablePair<String, String>> of = Optional.of(ImmutablePair.of("L", "R"));

        map.put("1",of);

		// Stream.of(pair1,pair2,pair3,pair4,pair5,pair6).flatMap(stringStringPair -> , ArrayList:new)

	}
}
