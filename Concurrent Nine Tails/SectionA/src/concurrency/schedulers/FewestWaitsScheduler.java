package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FewestWaitsScheduler extends AbstractScheduler {

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    List<Integer> threads = getThreads(program);

    Map<Integer, Long> count = threads.stream().collect(Collectors.toMap(Function.identity(),
        i -> program.remainingStatements(i).stream().filter(Stmt::isWait).count()));

    long minWait = count.values().stream().reduce(Long::min).get();

    for (Integer id : threads) {
      if (count.get(id) == minWait) {
        return id;
      }
    }
    return -1;
  }
}
