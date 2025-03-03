
import java.util.Random;

public class Person {
    private int birthday; // Represents the day of the year (0-364)

    public Person(Random random) {
        this.birthday = random.nextInt(365); // Assign a random birthday (0-364)
    }

    public int getBirthday() {
        return birthday;
    }
}
