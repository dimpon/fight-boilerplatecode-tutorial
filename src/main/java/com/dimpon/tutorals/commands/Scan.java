package com.dimpon.tutorals.commands;

import lombok.SneakyThrows;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
public class Scan {

	public static void main(String[] args) throws Exception {

		Stream<Class> classes = getClasses("com.dimpon.tutorals.caases");
		List<Class> collect = classes.collect(Collectors.toList());

		Stream<Class> classes1 = getClasses("com.dimpon.tutorals.builder");
		List<Class> collect1 = classes1.collect(Collectors.toList());
	}


	@SneakyThrows
	private static Stream<Class> getClasses(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final String path = packageName.replace('.', '/');

		Enumeration<URL> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<>();

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}

		return dirs.stream()
				.map(dir -> findClasses(dir, packageName))
				.flatMap(Collection::stream);
	}



	@SneakyThrows
	private static List<Class> findClasses(File directory, String packageName) {

		if (!directory.exists()) {
			return Collections.emptyList();
		}

		if (directory.listFiles() == null) {
			return Collections.emptyList();
		}

		return Arrays.stream(directory.listFiles())
				.filter(f -> !f.isDirectory())
				.filter(f -> f.getName().endsWith(".class"))
				.map(file -> loadClass(file,packageName))
				.collect(Collectors.toList());
	}

	@SneakyThrows
	private static Class<?> loadClass(File file, String packageName) {
		return Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
	}
}
