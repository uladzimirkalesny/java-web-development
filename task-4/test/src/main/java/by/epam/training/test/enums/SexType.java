package by.epam.training.test.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public enum SexType {
    MALE("man"),
    FEMALE("woman");

    private String description;

    @Override
    public String toString() {
        return this.description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
