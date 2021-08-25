package ias;

import student.Factory;

/**
 * Class with text adventure scenarios to play.
 */
public class Adventures {

    /**
     * Initialize the fireman-game.
     *
     * @return the fireman-game
     * @throws TextAdventureException
     */
    public static ias.TextAdventure getFiremanGame() throws TextAdventureException {
        ias.TextAdventure textAdventure = Factory.getGame("Sample1", 2, 2);
        textAdventure.addSceneryType("BurningTree", "A blazing fire on an oak tree.");
        textAdventure.addSceneryType("HalfBurningTree", "A little fire on an oak tree.");
        textAdventure.addItemType("Bucket", "An empty bucket.");
        textAdventure.addItemType("Water", "A bucket full of water.");
        textAdventure.addItemType("Head", "Kopf");
        textAdventure.addItemType("Headweh", "Jetzt brummt de kopf");
        textAdventure.addSceneryType("Lake", "A beautiful lake.");
        textAdventure.addSceneryType("CharredOak", "A poor, charred oak tree.");
        textAdventure.addTransformation("Bucket", "Lake", "Water", "Lake",
                "Now you got a bucket full of water.");
        textAdventure.addTransformation("Water", "BurningTree", "HalfBurningTree", "Bucket",
                "The water quenches the fire. But not completely.");
        textAdventure.addTransformation("Water", "HalfBurningTree", "CharredOak", "Bucket",
                "The water quenches the fire.");
        textAdventure.addComposition("Bucket", "Head", "Headweh", "Kopfwehh");
        textAdventure.placeItem("Lake", 0, 0);
        textAdventure.placeItem("Bucket", 1, 1);
        textAdventure.placeItem("Head", 1, 1);
        textAdventure.placeItem("BurningTree", 0, 0);
        return textAdventure;
    }

    /**
     * Initialize the lumberjack-game.
     *
     * @return The lumberjack-game
     * @throws TextAdventureException
     */
    public static ias.TextAdventure getLumberjackGame() throws TextAdventureException {
        ias.TextAdventure textAdventure = Factory.getGame("Sample2", 1, 1);
        textAdventure.addSceneryType("Tree", "A lush green tree.");
        textAdventure.addItemType("Wood", "Some pieces of wood");
        textAdventure.addSceneryType("Roots", "The sad roots of a former tree.");
        textAdventure.addDecomposition("Tree", "Wood", "Roots", "You fell the tree and get some wood.");
        textAdventure.placeItem("Tree", 0, 0);
        return textAdventure;
    }


    /**
     * Initialize the pokemon-game.
     *
     * @return the pokemon-game
     * @throws TextAdventureException
     */
    public static ias.TextAdventure getPokemonGame() throws TextAdventureException {
        TextAdventure textAdventure = Factory.getGame("Pokemon", 50, 50);

        textAdventure.addItemType("Coin", "One coin");
        textAdventure.addItemType("Money", "Many coins");
        textAdventure.addItemType("Bisasam", "Look, a wild Bisasam.");
        textAdventure.addItemType("Glumanda", "Look, a wild Glumanda.");
        textAdventure.addItemType("Schiggy", "Look, a wild Schiggy.");
        textAdventure.addItemType("Bisaknosp", "Bisaknosp eats plants.");
        textAdventure.addItemType("Glutexo", "Glutexo burns.");
        textAdventure.addItemType("Schillok", "Schillok swimming around.");
        textAdventure.addItemType("Bisaflor", "Bisaflor eats plants.");
        textAdventure.addItemType("Glurak", "Glurak burns.");
        textAdventure.addItemType("Turtok", "Turtok swimming around.");
        textAdventure.addItemType("Taubsi", "Taubsi flying around.");
        textAdventure.addItemType("Tauboga", "Tauboga flying around.");
        textAdventure.addItemType("Tauboss", "Tauboss flying around.");
        textAdventure.addItemType("Mewtu", "Mewtu sees dead people.");
        textAdventure.addItemType("Developmentstone",
                "The Developmentstone brings your Pokemon to the next Level.");
        textAdventure.addItemType("Attackstone",
                "The Attackstone is in combination with your Pokemon to attack other Pokemons.");
        textAdventure.addItemType("Pokeball",
                "Pokeball is there to catch other Pokemons.");
        textAdventure.addSceneryType("Wildtaubsi", "Look, a wild Taubsi.");
        textAdventure.addSceneryType("Deadtaubsi", "Look, a dead Taubsi.");
        textAdventure.addSceneryType("Silvertreasure", "Look, a Treasure.");
        textAdventure.addSceneryType("Goldtreasure", "Look, a Treasure.");
        textAdventure.placeItem("Coin", 0, 0);
        textAdventure.placeItem("Bisasam", 0, 0);
        textAdventure.placeItem("Glumanda", 0, 0);
        textAdventure.placeItem("Schiggy", 0, 0);
        textAdventure.placeItem("Developmentstone", 2, 4);
        textAdventure.placeItem("Developmentstone", 25, 31);
        textAdventure.placeItem("Developmentstone", 49, 11);
        textAdventure.placeItem("Developmentstone", 22, 22);
        textAdventure.placeItem("Developmentstone", 11, 44);
        textAdventure.placeItem("Developmentstone", 23, 43);
        textAdventure.placeItem("Developmentstone", 39, 1);
        textAdventure.placeItem("Developmentstone", 22, 21);
        textAdventure.placeItem("Developmentstone", 45, 30);
        textAdventure.placeItem("Developmentstone", 10, 12);
        textAdventure.placeItem("Developmentstone", 23, 1);
        textAdventure.placeItem("Developmentstone", 49, 49);
        textAdventure.placeItem("Developmentstone", 17, 15);
        textAdventure.placeItem("Attackstone", 1, 1);
        textAdventure.placeItem("Attackstone", 10, 10);
        textAdventure.placeItem("Attackstone", 20, 20);
        textAdventure.placeItem("Attackstone", 30, 30);
        textAdventure.placeItem("Attackstone", 4, 5);
        textAdventure.placeItem("Attackstone", 10, 2);
        textAdventure.placeItem("Attackstone", 23, 44);
        textAdventure.placeItem("Attackstone", 30, 35);
        textAdventure.placeItem("Pokeball", 1, 1);
        textAdventure.placeItem("Pokeball", 3, 22);
        textAdventure.placeItem("Pokeball", 17, 21);
        textAdventure.placeItem("Pokeball", 33, 13);
        textAdventure.placeItem("Pokeball", 45, 22);
        textAdventure.placeItem("Pokeball", 21, 13);
        textAdventure.placeItem("Pokeball", 19, 41);
        textAdventure.placeItem("Pokeball", 12, 32);
        textAdventure.placeItem("Wildtaubsi", 2, 2);
        textAdventure.placeItem("Wildtaubsi", 23, 24);
        textAdventure.placeItem("Wildtaubsi", 11, 7);
        textAdventure.placeItem("Wildtaubsi", 3, 9);
        textAdventure.placeItem("Wildtaubsi", 32, 45);
        textAdventure.placeItem("Wildtaubsi", 31, 29);
        textAdventure.placeItem("Wildtaubsi", 17, 31);
        textAdventure.placeItem("Wildtaubsi", 45, 45);
        textAdventure.placeItem("Wildtaubsi", 18, 32);
        textAdventure.placeItem("Wildtaubsi", 26, 42);
        textAdventure.placeItem("Wildtaubsi", 42, 26);
        textAdventure.placeItem("Wildtaubsi", 17, 49);
        textAdventure.placeItem("Wildtaubsi", 11, 22);
        textAdventure.placeItem("Wildtaubsi", 36, 39);
        textAdventure.placeItem("Wildtaubsi", 31, 24);
        textAdventure.placeItem("Silvertreasure", 2, 3);
        textAdventure.placeItem("Silvertreasure", 32, 33);
        textAdventure.placeItem("Silvertreasure", 14, 13);
        textAdventure.placeItem("Silvertreasure", 47, 11);
        textAdventure.placeItem("Silvertreasure", 12, 34);
        textAdventure.placeItem("Silvertreasure", 21, 33);
        textAdventure.placeItem("Silvertreasure", 43, 21);
        textAdventure.placeItem("Silvertreasure", 49, 3);
        textAdventure.placeItem("Silvertreasure", 12, 49);
        textAdventure.placeItem("Silvertreasure", 41, 34);
        textAdventure.placeItem("Silvertreasure", 12, 33);
        textAdventure.placeItem("Silvertreasure", 22, 34);
        textAdventure.placeItem("Silvertreasure", 1, 40);
        textAdventure.placeItem("Silvertreasure", 19, 28);
        textAdventure.placeItem("Silvertreasure", 45, 32);
        textAdventure.placeItem("Silvertreasure", 21, 23);
        textAdventure.placeItem("Goldtreasure", 33, 34);
        textAdventure.addComposition("Coin", "Coin", "Money", "You made money");
        textAdventure.addComposition("Bisasam", "Developmentstone", "Bisaknosp",
                "Look your Bisasam has developed to Bisaknosp.");
        textAdventure.addComposition("Glumanda", "Developmentstone", "Glutexo",
                "Look your Glumanda has developed to Glutexo.");
        textAdventure.addComposition("Schiggy", "Developmentstone", "Schillok",
                "Look your Schiggy has developed to Schillok.");
        textAdventure.addComposition("Taubsi", "Developmentstone", "Tauboga",
                "Look your Taubsi has developed to Tauboga");
        textAdventure.addComposition("Bisaknosp", "Developmentstone", "Bisaflor",
                "Look your Bisaknosp has developed to Bisaflor");
        textAdventure.addComposition("Glutexo", "Developmentstone", "Glurak",
                "Look your Glutexo has developed to Glurak");
        textAdventure.addComposition("Schillok", "Developmentstone", "Turtok",
                "Look your Schillok has developed to Turtok");
        textAdventure.addComposition("Tauboga", "Developmentstone", "Tauboss",
                "Look your Tauboga has developed to Tauboss");
        textAdventure.addComposition("Wildtaubsi", "Attackstone", "Deadtaubsi",
                "Your Pokemon attacks the wild Taubsi. Taubsi is dead.");
        textAdventure.addComposition("Wildtaubsi", "Pokeball", "Taubsi",
                "You have catch Taubsi. Congratulations, Taubsi is your new Pokemon.");
        textAdventure.addDecomposition("Silvertreasure", "Developmentstone",
                "Attackstone",
                "You open the treasure and you find an Attack- and Developmentstone.");
        textAdventure.addDecomposition("Goldtreasure", "Pokeball", "Mewtu",
                "See, you find the unique Mewtu.");
        textAdventure.addTransformation("Taubsi", "Attackstone", "Taubsi",
                "Developmentstone",
                "Look, your Taubsi transform the Attackstone to a Developmentstone.");
        textAdventure.addTransformation("Tauboga", "Attackstone", "Tauboga",
                "Developmentstone",
                "Look, your Tauboga transform the Attackstone to a Developmentstone.");
        textAdventure.addTransformation("Tauboss", "Attackstone", "Tauboss",
                "Developmentstone",
                "Look, your Tauboss transform the Attackstone to a Developmentstone.");
        textAdventure.addTransformation("Mewtu", "Developmentstone", "Mewtu",
                "Attackstone",
                "Look, your Mewtu transform the Developmentstone to a Attackstone.");
        textAdventure.addTransformation("Bisasam", "Attackstone", "Bisasam",
                "Pokeball",
                "Look, your Bisasam transform the Attackstone to a Pokeball.");
        textAdventure.addTransformation("Bisaknosp", "Attackstone", "Bisaknosp",
                "Pokeball",
                "Look, your Bisaknosp transform the Attackstone to a Pokeball.");
        textAdventure.addTransformation("Bisaflor", "Attackstone", "Bisaflor",
                "Pokeball",
                "Look, your Bisaflor transform the Attackstone to a Pokeball.");
        textAdventure.addTransformation("Glumanda", "Pokeball", "Glumanda",
                "Developmentstone",
                "Look, your Glumanda transform the Pokeball to a Developmentstone.");
        textAdventure.addTransformation("Glutexo", "Pokeball", "Glutexo",
                "Developmentstone",
                "Look, your Glutexo transform the Pokeball to a Developmentstone.");
        textAdventure.addTransformation("Glurak", "Pokeball", "Glurak",
                "Developmentstone",
                "Look, your Glurak transform the Pokeball to a Developmentstone.");
        textAdventure.addTransformation("Schiggy", "Pokeball", "Schiggy",
                "Attackstone",
                "Look, your Schiggy transform the Pokeball to a Attackstone.");
        textAdventure.addTransformation("Schillok", "Pokeball", "Schillok",
                "Attackstone",
                "Look, your Schillok transform the Pokeball to a Attackstone.");
        textAdventure.addTransformation("Turtok", "Pokeball", "Turtok",
                "Attackstone",
                "Look, your Turtok transform the Pokeball to a Attackstone.");

        return textAdventure;
    }
}
