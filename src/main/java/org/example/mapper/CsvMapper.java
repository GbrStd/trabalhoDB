package org.example.mapper;

import org.example.reader.CsvFile;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvMapper {

    @SuppressWarnings("unchecked")
    public static <T> T[] deserializeTo(CsvFile csv, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        List<T> tArrayList = new ArrayList<>();

        T instance = clazz.cast(clazz.getConstructors()[0].newInstance());

        final int headersLength = csv.getHeaders().length;
        int i = 0;
        while (i < csv.getLines().length) {
            int x = i % headersLength;

            if (i > 0 && x == 0)
                instance = clazz.cast(clazz.getConstructors()[0].newInstance());

            final Field[] fields = instance.getClass().getDeclaredFields();
            String header = normalize(csv.getHeaders()[x]);
            final Field field = Arrays.stream(fields)
                    .filter(f -> header.equalsIgnoreCase(f.getName()))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            if (!field.canAccess(instance))
                field.setAccessible(true);

            if (field.getType().isAssignableFrom(int.class)) {
                field.set(instance, Integer.valueOf(csv.getLines()[i]));
            } else if (field.getType().isAssignableFrom(long.class)) {
                field.set(instance, Long.valueOf(csv.getLines()[i]));
            } else if (field.getType().isAssignableFrom(float.class)) {
                field.set(instance, Float.valueOf(csv.getLines()[i]));
            } else {
                field.set(instance, csv.getLines()[i]);
            }

            if (x == 0)
                tArrayList.add(instance);
            i++;
        }

        return tArrayList.toArray((T[]) Array.newInstance(clazz, tArrayList.size()));
    }

    private static String normalize(String s) {
        // Remove os acentos dos headers. Exemplo: Í passa a ser I
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[^\\p{ASCII}]", "");
        // Remove ", _ e espaços
        return s.replaceAll("[\\s_\"]+", "");
    }
}
