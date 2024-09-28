import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private static PlantManager instance; // Singleton instance
    private List<Plant> plants;
    private List<Reminder> reminders; // List to hold reminders
    private static final String FILE_PATH = "plants.dat";

    private PlantManager() {
        plants = new ArrayList<>();
        reminders = new ArrayList<>();
        loadPlants();
    }

    public static PlantManager getInstance() {
        if (instance == null) {
            instance = new PlantManager();
        }
        return instance;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
        savePlants();
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void displayAllPlants(JTextArea textArea) {
        if (plants.isEmpty()) {
            textArea.setText("No plants available.");
        } else {
            StringBuilder plantDetails = new StringBuilder();
            for (Plant plant : plants) {
                plantDetails.append("-------------------------------\n");
                plantDetails.append(plant.displayDetails()).append("\n");
                plantDetails.append("-------------------------------\n\n");
            }
            textArea.setText(plantDetails.toString());
        }
    }

    public void addReminder(Plant plant, String careType, LocalDateTime time) {
        reminders.add(new Reminder(plant, careType, time));
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
    //needs to e implemented

    private void savePlants() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(plants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPlants() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            plants = (List<Plant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            plants = new ArrayList<>();
        }
    }
}
