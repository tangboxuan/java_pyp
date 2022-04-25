import java.util.HashSet;
import java.util.Set;

public class ProgrammingTest {

  public static void main(String[] args) {
    Academic ricardo = new Academic("Ricardo Rodriguez");
    Academic ismael = new Academic("Ismael Bento");
    Undergraduate gg4 = new Undergraduate("gg4", "George", "george@ic.ac.uk", ricardo);
    Undergraduate pr3 = new Undergraduate("pr3", "Peter", "peter@ic.ac.uk", ismael);
    Postgraduate te2 = new Postgraduate("te2", "tom", "tom@ic.ac.uk", ricardo);
    Postgraduate yj34 = new Postgraduate("yj34", "Yves", "yves@ic.ac.uk", ismael);
    Postgraduate jj8 = new Postgraduate("jj8", "John", "john@ic.ac.uk", ismael);
    Set<Student> students = new HashSet<>();
    students.add(gg4);
    students.add(pr3);
    students.add(te2);
    students.add(yj34);
    students.add(jj8);
    Course course = new Course("Example Course", students);

    Set<Postgraduate> postgraduates = course.getPostgraduates("Ismael Bento");
    Notifier notifier = new Notifier(postgraduates);
    notifier.doNotifyAll("You have been notified");
  }

}
