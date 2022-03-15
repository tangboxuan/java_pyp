package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractScheduler implements Scheduler {

  protected List<Integer> getThreads(ConcurrentProgram program) throws DeadlockException {
    List<Integer> threads = new ArrayList(program.getEnabledThreadIds());
    if (threads.isEmpty()) {
      throw new DeadlockException();
    }
    Collections.sort(threads);
    return threads;
  }
}

