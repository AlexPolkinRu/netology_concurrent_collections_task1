/**
 * @author Aleksandr Polochkin
 * 01.08.2022
 */

public class Main {

    final static int NUMBER_OF_OPERATORS = 3;

    public static void main(String[] args) {

        TelephoneStation telephoneStation = new TelephoneStation();
        telephoneStation.start();

        final ThreadGroup callCentre = new ThreadGroup("Колл-центр");

        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            (new Thread(callCentre, new Operator(telephoneStation, String.valueOf(i + 1)))).start();
        }

        while (!telephoneStation.isFinished());

        callCentre.interrupt();
    }
}
