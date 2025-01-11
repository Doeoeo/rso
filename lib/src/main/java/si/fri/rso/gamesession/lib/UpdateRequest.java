package si.fri.rso.gamesession.lib;

public class UpdateRequest {
    private Integer boatId; // ID of the boat being updated
    private String field;   // The field being updated, e.g., "hpHull"
    private String newValue; // The new value for the field

    // Getters and Setters
    public Integer getBoatId() {
        return boatId;
    }

    public void setBoatId(Integer boatId) {
        this.boatId = boatId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}