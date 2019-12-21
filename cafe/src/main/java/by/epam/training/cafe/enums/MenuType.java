package by.epam.training.cafe.enums;

/**
 * Enumeration MenuType represent type of menu.
 *
 * @author Uladzimir Kalesny
 */
public enum MenuType {
    /**
     * Season menu.
     */
    SEASON,
    /**
     * Chief menu.
     */
    CHIEF,
    /**
     * Custom menu.
     */
    CUSTOM;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
