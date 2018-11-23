package worldofzuul.domain;

public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), TAKE("take"), DROP("drop"), INSPECT("inspect"), SEARCH("search"), USE("use"), EXITS("exits");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
