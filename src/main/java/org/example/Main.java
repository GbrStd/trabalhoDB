package org.example;

import org.example.db.ArrivalDao;
import org.example.mapper.CsvMapper;
import org.example.model.Arrival;
import org.example.reader.CsvFile;
import org.example.reader.CsvReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Deseja inserir? true/false");
        if (teclado.nextBoolean()) {

            CsvReader csvReader = new CsvReader(';', new File("C:\\Users\\logatti\\Desktop\\chegadas_2019.csv"));

            final CsvFile csvFile = csvReader.read(StandardCharsets.ISO_8859_1);

            Arrival[] arrivals;
            try {
                arrivals = CsvMapper.deserializeTo(csvFile, Arrival.class);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            ArrivalDao.getInstance().insert(arrivals);
        }

        System.out.println("Digite o ID: ");
        int id = teclado.nextInt();

        ArrivalDao.getInstance().delete(id);

        Arrival arrival = new Arrival();

        arrival.setContinente("Asia");
        arrival.setCodContinente(1);
        arrival.setPais("Japão");
        arrival.setCodPais(2);
        arrival.setUf("São Paulo");
        arrival.setCodUf(1);
        arrival.setVia("Maritima");
        arrival.setCodVia(100);
        arrival.setAno(1999);
        arrival.setMes("Setembro");
        arrival.setCodMes(9);
        arrival.setChegadas(200);

        ArrivalDao.getInstance().update(1, arrival);

        ArrivalDao.getInstance().findById(1).ifPresent(System.out::println);

    }

}