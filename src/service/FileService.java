package service;

import enums.BloodGroup;
import model.Donor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String FILE_NAME = "donors.txt";

    // saves donor list to file - one donor per line, fields separated by "|"
    public void saveDonors(List<Donor> donors) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Donor d : donors) {
                bw.write(d.getName() + "|" + d.getAge() + "|" + d.getBloodGroup().name() +
                        "|" + d.isAvailable());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save donor data.");
        }
    }

    // loads donor list back from file, returns empty list if file doesn't exist
    public List<Donor> loadDonors() {
        List<Donor> donors = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return donors;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split each line back into its 4 parts using "|"
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    BloodGroup group = BloodGroup.valueOf(parts[2]);
                    boolean available = Boolean.parseBoolean(parts[3]);

                    Donor d = new Donor(name, age, group);
                    d.setAvailable(available);
                    donors.add(d);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load donor data.");
        }

        return donors;
    }
}