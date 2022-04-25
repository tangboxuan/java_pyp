import java.util.HashSet;
import java.util.Set;

public class Course {

  private final Set<Student> students;
  private final String name;

  public Course(String name, Set<Student> students) {
    this.students = students;
    this.name = name;
  }

  public Set<Postgraduate> getPostgraduates(String nameOfSupervisor) {
    Set<Postgraduate> result = new HashSet<>();
    for (Student student : students) {
      if (student instanceof Postgraduate) {
        Postgraduate postgraduate = (Postgraduate) student;
        if (postgraduate.getSupervisor().getName().equals(nameOfSupervisor)) {
          result.add(postgraduate);
        }
      }
    }
    return result;
  }

}
