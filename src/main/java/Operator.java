import java.util.Random;

/**
 * @author Aleksandr Polochkin
 * 01.08.2022
 */

public class Operator extends Thread{

    private final int DELAY_BETWEEN_ANSWERS = new Random().nextInt(1000) + 3000;

    private final TelephoneStation TELEPHONE_STATION;
    private final String NAME;

    public Operator(TelephoneStation telephoneStation, String name) {
        TELEPHONE_STATION = telephoneStation;
        NAME = name;
    }

    @Override
    public void run() {

        Call currentCall;

        while (!isInterrupted()) {

            currentCall = TELEPHONE_STATION.getCall();

            if (null != currentCall) {

                System.out.println("Оператор " + NAME + " ответил на звонок " + currentCall.getID());

                try {
                    sleep(DELAY_BETWEEN_ANSWERS);
                } catch (InterruptedException e) {
                    break;
                }

            }

        }

        System.out.println("Оператор " + NAME + " закончил работу");

    }
}
