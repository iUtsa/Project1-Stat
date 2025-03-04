import java.util.Random;

public class Person {
    private int birthday; // Represents the day of the year (0-364)

    /**
     * Constructor that assigns a random birthday to the person.
     * @param random The Random object used to generate a random birthday.
     */
    public Person(Random random) {
        this.birthday = random.nextInt(365); // Assign a random birthday (0-364)
    }

    /**
     * Getter method to retrieve the person's birthday.
     * @return The birthday of the person as an integer (0-364).
     */
    public int getBirthday() {
        return birthday;
    }
}