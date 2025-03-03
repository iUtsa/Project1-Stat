import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards, pool;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        // Pokémon Cards

        for (int i = 0; i < 3; i++) {
            PokemonCard pikachu = new Pikachu();
            PokemonCard charmander = new Charmander();
            PokemonCard mewtwo = new MewTwo();
            PokemonCard golem = new Golem();


            cards.add(pikachu);
            cards.add(charmander);
            cards.add(mewtwo);
            cards.add(golem);

            // Trainer Cards
            cards.add(new TrainerCard("Potion"));
            cards.add(new TrainerCard("Professor Oak"));
            cards.add(new TrainerCard("Full Heal"));


            // Energy Cards (Each Type Has Unique Effects)
            cards.add(new EnergyCard("Fire Energy", "Fire", 1, 0));  // Boosts Energy by 1
            cards.add(new EnergyCard("Grass Energy", "Grass", 2, 0));  // Boosts Energy by 2
            cards.add(new EnergyCard("Retro Energy", "Retro", 3, 0));  // Boosts Energy by 3
            cards.add(new EnergyCard("Electric Energy", "Electric", 1, 0));  // Boosts Energy by 1
            cards.add(new EnergyCard("Psychic Energy", "Psychic", 3, 0));  // Boosts Energy by 3
        }

        shuffle();
    }


    public ArrayList<Card> initializePool(){
        pool = new ArrayList<>();
        if(!cards.isEmpty()){
            Random rng = new Random();
            Card card = cards.remove(rng.nextInt(cards.size()));
            pool.add(card);
        }
        else {
            System.out.println("Deck is empty! Cannot initialize pool.");
        }
        return pool;
    }


    public void shuffle() {
        Collections.shuffle(cards);

    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public int getDeckSize() {
        return cards.size();
    }
}
