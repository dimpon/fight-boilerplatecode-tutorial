package com.dimpon.tutorals;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.vfs.Vfs;

import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.IntStream;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

	public static void main(String[] args) throws Exception {

		IntStream.range(1, 10).forEach(value -> {
			System.out.println("" + value);
		});

List<String> val = new ArrayList<>();
val.add("xxx");

		List<String> aaa = Arrays.asList("CustomFieldMapping", "RemoveSensitiveInformation", "AdjustToSchemaVersion");

		ArrayList<String> strings = new ArrayList<>(aaa);

		strings.addAll(val);

		// Reflections r = new Reflections("com.dimpon.tutorals.watcher");

		/*
		 * Collection<URL> urls = ClasspathHelper.forPackage("javax.validation.constraints");
		 * 
		 * for (Iterator<URL> iterator = urls.iterator(); iterator.hasNext(); ) {
		 * URL next = iterator.next();
		 * System.out.println(""+next);
		 * }
		 * 
		 * 
		 * Iterable<Vfs.File> files = Vfs.findFiles(urls, file -> true);
		 * Iterator<Vfs.File> iterator = files.iterator();
		 * 
		 * while (iterator.hasNext()) {
		 * Vfs.File next = iterator.next();
		 * System.out.println(""+next);
		 * }
		 * 
		 * // Set<Class<?>> typesAnnotatedWith = r.getTypesAnnotatedWith(Override.class);
		 * 
		 * JarFile file = new JarFile(urls.stream().findFirst().get().getFile());
		 * for (Enumeration<JarEntry> entry = file.entries(); entry.hasMoreElements();) {
		 * JarEntry jarEntry = entry.nextElement();
		 * 
		 * }
		 */
	}

	public String hi(String name) {
		return hello(name);
	}

	private String hello(String name) {
		return name.toUpperCase();
	}
}
