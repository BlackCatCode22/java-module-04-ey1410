import java.io.*;
import java.util.*;

public class ZooApp {
    public static void main(String[] args) {
        // List to store animal objects
        ArrayList<Animal> animals = new ArrayList<>();
        // Map to store count of each species
        Map<String, Integer> speciesCount = new HashMap<>();

        try {
            // Read animals from arrivingAnimals.txt file
            BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"));
            String line;

            // Process each line of the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String species = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                // Create corresponding animal object based on species
                Animal animal = null;
                switch (species) {
                    case "Hyena":
                        animal = new Hyena(name, age);
                        break;
                    case "Lion":
                        animal = new Lion(name, age);
                        break;
                    case "Tiger":
                        animal = new Tiger(name, age);
                        break;
                    case "Bear":
                        animal = new Bear(name, age);
                        break;
                    default:
                        System.out.println("Unknown species: " + species);
                        continue;
                }

                // Add animal to the list and update species count
                if (animal != null) {
                    animals.add(animal);
                    speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                }
            }
            reader.close();

            // Write the report to newAnimals.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("newAnimals.txt"));

            // Write the report for each species
            for (String species : speciesCount.keySet()) {
                writer.write("Species: " + species + ", Count: " + speciesCount.get(species));
                writer.newLine();

                // Write details of each animal in the species group
                for (Animal animal : animals) {
                    if (animal.getSpecies().equals(species)) {
                        writer.write(animal.animalInfo());
                        writer.newLine();
                    }
                }
            }
            writer.close();

            System.out.println("Report generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
