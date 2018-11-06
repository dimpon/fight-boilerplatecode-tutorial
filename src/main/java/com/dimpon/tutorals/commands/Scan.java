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

    public static void main(String[] args) throws Exception{

        Stream<Class> classes = getClasses("com.dimpon.tutorals.caases");
        List<Class> collect = classes.collect(Collectors.toList());

        Stream<Class> classes1 = getClasses("com.dimpon.tutorals.builder");
        List<Class> collect1 = classes1.collect(Collectors.toList());
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
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
    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */

    @SneakyThrows
    private static List<Class> findClasses(File directory, String packageName)  {

        if (!directory.exists()) {
            return Collections.emptyList();
        }

        List<Class> classes = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
