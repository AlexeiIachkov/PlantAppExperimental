import java.io.Serializable;

public class PlantImplementation implements Plant {
    private String name;
    private String lightCondition;
    private String waterSchedule;
    private String fertilizerSchedule;
    private String imagePath;
    private String category;  // New field for category

    public PlantImplementation(String name, String lightCondition, String waterSchedule, String fertilizerSchedule, String imagePath, String category) {
        this.name = name;
        this.lightCondition = lightCondition;
        this.waterSchedule = waterSchedule;
        this.fertilizerSchedule = fertilizerSchedule;
        this.imagePath = imagePath;
        this.category = category;  // Initialize category
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLightCondition() {
        return lightCondition;
    }

    @Override
    public String getWaterSchedule() {
        return waterSchedule;
    }

    @Override
    public String getFertilizerSchedule() {
        return fertilizerSchedule;
    }

    @Override
    public String getImagePath() {
        return imagePath;  // Return image path
    }

    @Override
    public String getCategory() {
        return category;  // Return category
    }

    @Override
    public String displayDetails() {
        return "Name: " + name + "\nLight Condition: " + lightCondition +
                "\nWater Schedule: " + waterSchedule + "\nFertilizer Schedule: " + fertilizerSchedule +
                "\nCategory: " + category;
    }
}
