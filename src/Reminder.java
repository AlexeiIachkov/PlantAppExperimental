import java.time.LocalDateTime;

public class Reminder {
    private Plant plant;
    private String careType; // "Water", "Fertilizer", etc.
    private LocalDateTime reminderTime;

    public Reminder(Plant plant, String careType, LocalDateTime reminderTime) {
        this.plant = plant;
        this.careType = careType;
        this.reminderTime = reminderTime;
    }

    public Plant getPlant() {
        return plant;
    }

    public String getCareType() {
        return careType;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void notifyUser() {
        // Code to notify the user, e.g., using a dialog or notification system.
        System.out.println("Reminder: Time to " + careType + " " + plant.getName() + "!");
    }
}

//hasn't been implemented yet