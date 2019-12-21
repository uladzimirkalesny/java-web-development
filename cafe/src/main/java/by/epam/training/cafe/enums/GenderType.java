package by.epam.training.cafe.enums;

/**
 * Enumeration GenderType represent gender of client.
 *
 * @author Uladzimir Kalesny
 */
public enum GenderType {
    /**
     * Default male gender.
     */
    MALE,
    /**
     * Female gender.
     */
    FEMALE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
