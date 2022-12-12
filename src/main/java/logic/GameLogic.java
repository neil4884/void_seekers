package logic;

public final class GameLogic {
    private static final GameLogic instance = new GameLogic();;

    public GameLogic() {
        System.out.println("Logic Instantiated");
    }

    public static GameLogic getInstance() {
        return instance;
    }
}
