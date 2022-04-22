
/**
 * Inputtable is an interface for InputGetter
 * */
public interface Inputtable {
    public Commands inputCommand(Player player, String command);

    public int inputRandomSeed();
}