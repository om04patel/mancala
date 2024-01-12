package mancala;

public class Player {
    // Instance variables
    private String playerName;
    private Store playerStore;

    // Constructors
    public Player() {
         // Initialize a new player a name and store
        playerName = "Player";
        playerStore = new Store();
    }

    public Player(String name) {
         // Initialize a new player with a given name and default store
        this.playerName = name;
        this.playerStore = new Store();
    }
// Method to get the player's name
    public String getName() {
        return this.playerName;
    }
 // Method to set the player's name
    public void setName(String name) {
        this.playerName = name;
    }
 // Method to get the player's store
    public Store getStore() {
        return this.playerStore;
    }
 // Method to get the count of stones in the player's store
    public int getStoreCount() {
        return playerStore.getTotalStones();
    }
 // Method to set the player's store
    public void setStore(Store store){
        playerStore = store;
    }
@Override
    public String toString() {
        return "Player: " + playerName + ", Store: " + playerStore.toString();
    }
}
