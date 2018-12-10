package worldofzuul;

public enum CommandWord {
    GO("go"), HELP("help"), TAKE("take"), DROP("drop"), INSPECT("inspect"), USE("use");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
