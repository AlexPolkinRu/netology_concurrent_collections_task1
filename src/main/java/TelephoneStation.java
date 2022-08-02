import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Aleksandr Polochkin
 * 01.08.2022
 */

public class TelephoneStation extends Thread {

    private final int NUMBER_OF_INCOMING_CALL = 10;
    private final int DELAY_BETWEEN_CALL = 1000;

    /*
       Для накопления и обработки входящих звонков применим очередь,
       так как обрабатывать звонки справедливо в порядке их поступления
       по принципу FIFO.

       Утилитный класс Collections исключим в виду существования более
       быстрых решений.

       Варианты потокобезопасных имплеменаций очереди:
       PriorityBlockingQueue
       ConcurrentLinkedQueue
       ArrayBlockingQueue
       LinkedBlockingQueue
       SynchronousQueue
       LinkedTransferQueue
       DelayQueue

       Нам нужна "честность" в разборе очереди. Поэтому остаются:
       ConcurrentLinkedQueue
       ArrayBlockingQueue
       LinkedBlockingQueue
       SynchronousQueue
       LinkedTransferQueue

       Последние два варианта отпадают, потому что мы не можем
       заблокировать поток АТС, ведь он нам нужен для генерации
       поступающих звонков.

       Подходят варианты:
       ConcurrentLinkedQueue
       ArrayBlockingQueue
       LinkedBlockingQueue

       ArrayBlockingQueue исключим в виду фиксированного размера
       очереди и блокировки

       LinkedBlockingQueue исключим также по причине блокировки и
       бОльших затрат памяти чем в ArrayBlockingQueue

       Остановим свой выбор на ConcurrentLinkedQueue
    */

    Queue<Call> calls = new ConcurrentLinkedQueue<>();

    private volatile boolean isFinished = false;

    public Call getCall() {
        return calls.poll();
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void run() {

        for (int i = 0; i < NUMBER_OF_INCOMING_CALL; i++) {

            calls.add(new Call());

            System.out.println("Звонок " + (i + 1) + " ожидает ответа.");

            try {
                sleep(DELAY_BETWEEN_CALL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        while (!calls.isEmpty());

        isFinished = true;

        System.out.println("Звонков больше нет");

    }
}
