package by.epam.training.cafe.entity;

import by.epam.training.cafe.enums.AmateurType;
import by.epam.training.cafe.enums.GenderType;
import lombok.Data;

/**
 * Immutable class Client represent entity client with standard set of fields
 * inherent to man.
 *
 * @author Uladzimir Kalesny
 */
@Data
public final class Client {
    /**
     * Textual information about client's name.
     */
    private final String name;
    /**
     * Textual information about client's surname.
     */
    private final String surname;
    /**
     * Numeric information about client's age.
     */
    private final int age;
    /**
     * Textual information about client's genderType.
     */
    private final GenderType genderType;
    /**
     * Textual information about client's address.
     */
    private final String address;
    /**
     * Textual information about client's relation to cafe and his products.
     */
    private final AmateurType amateurType;

    /**
     * Design pattern ClientBuilder look like as static class into Client entity.
     */
    public static class ClientBuilder {
        /**
         * Optional parameter name initialized to default value.
         */
        private String name = "Name data is missing";
        /**
         * Optional parameter surname initialized to default value.
         */
        private String surname = "Surname data is missing";
        /**
         * Optional parameter age initialized to default value.
         */
        private int age = 0;
        /**
         * Optional parameter genderType initialized to default value.
         */
        private GenderType genderType = GenderType.MALE;
        /**
         * Optional parameter address initialized to default value.
         */
        private String address = "Address data is missing";
        /**
         * Optional parameter amateurType initialized to default value.
         */
        private AmateurType amateurType = AmateurType.BEGINNER;

        /**
         * The builder’s setter methods.
         *
         * @param name textual data for builder field name
         * @return the builder itself
         */
        public ClientBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param surname textual data for builder field surname
         * @return the builder itself
         */
        public ClientBuilder surname(final String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param age numeric data for builder field age
         * @return the builder itself
         */
        public ClientBuilder age(final int age) {
            this.age = age;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param genderType data for builder field genderType
         * @return the builder itself
         */
        public ClientBuilder gender(final GenderType genderType) {
            this.genderType = genderType;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param address textual data for builder field address
         * @return the builder itself
         */
        public ClientBuilder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param amateurType data for builder field amateurType
         * @return the builder itself
         */
        public ClientBuilder amateur(final AmateurType amateurType) {
            this.amateurType = amateurType;
            return this;
        }

        /**
         * Build method calls the private constructor of the outer class and
         * passes itself as the argument.
         *
         * @return Client instance
         */
        public Client build() {
            return new Client(this);
        }
    }

    /**
     * Private constructor for initializing fields via builder.
     *
     * @param builder ClientBuilder instance
     */
    private Client(final ClientBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.genderType = builder.genderType;
        this.address = builder.address;
        this.amateurType = builder.amateurType;
    }

    @Override
    public String toString() {
        return name + ", " + surname + ", " + age + ", "
                + genderType + ", " + address + ", " + amateurType + ".";
    }
}
