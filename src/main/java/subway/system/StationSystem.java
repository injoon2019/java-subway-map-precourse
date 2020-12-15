package subway.system;

import java.util.Scanner;
import subway.domain.Station;
import subway.manager.StationManager;
import subway.validator.StationManagerValidator;
import view.StationManagerView;

public class StationSystem {
    static final int OPTION_ONE = 1;
    static final int OPTION_TWO = 2;
    static final int OPTION_THREE = 3;
    public static final String ASK_OPTION_MESSAGE = "## 원하는 기능을 선택하세요.";
    public static final String ERROR_PREFIX = "[ERROR] ";
    public static String userOption = "";

    private StationManager stationManager;

    public StationSystem() {
        stationManager = new StationManager();
    }

    public void run(Scanner scanner) {
        StationManagerView.printStationManagerMainScreen();
        userOption = getUserOption(scanner);
        callOptionMenu(userOption, scanner);
    }

    public static String getUserOption(Scanner scanner) {
        try {
            System.out.println(ASK_OPTION_MESSAGE);
            String userOption = scanner.nextLine();
            System.out.println();
            StationManagerValidator.validateUserOption(userOption);
            return userOption;
        } catch (IllegalArgumentException iae) {
            System.out.println(ERROR_PREFIX + iae.getMessage());
            return getUserOption(scanner);
        }
    }

    public void callOptionMenu(String userOption, Scanner scanner) {
        if (!Character.isDigit(userOption.charAt(0))) {
            return;
        }
        int optionNumber = Integer.parseInt(userOption);
        if (optionNumber == OPTION_ONE) {
            stationManager.enrollStation(scanner);
        }
        if (optionNumber == OPTION_TWO) {
            stationManager.deleteStation(scanner);
        }
        if (optionNumber == OPTION_THREE) {
            stationManager.searchStation(scanner);
        }
    }
}
