import java.io.Serializable;

public interface Plant extends Serializable {
    String getName();
    String getLightCondition();
    String getWaterSchedule();
    String getFertilizerSchedule();
    String displayDetails();
    String getImagePath();
    String getCategory();  // New method for category
}
//many of these methods will be used in the future
