package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RoundRobinScheduler extends AbstractScheduler {

  private int last = -1;

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    List<Integer> threads = getThreads(program);
    for (Integer id : threads) {
      if (id > last) {
        last = id;
        return id;
      }
    }
    last = threads.get(0);
    return threads.get(0);
  }
}
