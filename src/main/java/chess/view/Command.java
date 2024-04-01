package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    LOAD("load"),
    ;

    private final String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.commandName.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isLoad() {
        return this == LOAD;
    }

    public String getCommandName() {
        return commandName;
    }
}
