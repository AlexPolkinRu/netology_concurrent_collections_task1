/**
 * @author Aleksandr Polochkin
 * 01.08.2022
 */

public class Call {

    private final int CALL_ID;
    private static int counterCall = 0;
    private final String CALLERS_NAME;
    private final Long CALLERS_PHONENUMBER;

    public Call() {
        counterCall++;
        CALL_ID = counterCall;
        CALLERS_NAME = null;
        CALLERS_PHONENUMBER = null;
    }

    public int getID() {
        return CALL_ID;
    }

}
