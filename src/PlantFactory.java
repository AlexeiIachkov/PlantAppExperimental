public class PlantFactory {
    public static Plant createPlant(String name, String lightCondition, String waterSchedule, String fertilizerSchedule, String imagePath, String category) {
        return new PlantImplementation(name, lightCondition, waterSchedule, fertilizerSchedule, imagePath, category);
    }
}
