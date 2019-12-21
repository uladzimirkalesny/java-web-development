package by.epam.training.cafe.enums;

import lombok.AllArgsConstructor;

/**
 * Enumeration AmateurType represent textual description about
 * visits to the cafe by the client.
 *
 * @author Uladzimir Kalesny
 */

@AllArgsConstructor
public enum AmateurType {
    /**
     * Beginner amateur.
     */
    BEGINNER("I visit at the first time to cafe"),
    /**
     * Regular amateur.
     */
    REGULAR("I visit cafe sometimes"),
    /**
     * Inveterate amateur.
     */
    INVETERATE("I often come to cafe");

    /**
     * Textual information about amateur level.
     */
    private final String description;

    /**
     * Getter method provides textual description about amateur level.
     *
     * @return string instance.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
