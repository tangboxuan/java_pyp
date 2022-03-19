import java.util.HashSet;
import java.util.Set;

public class Group implements Contact{
  private final String name;
  private final Set<Contact> contacts;

  public Group(String name) {
    this.name = name;
    this.contacts = new HashSet<>();
  }

  public void add(Contact contact) {
    contacts.add(contact);
  }

  public boolean remove(Contact contact) {
    if (contacts.contains(contact)) {
      contacts.remove(contact);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Set<Person> getPeople() {
    Set<Person> result = new HashSet<>();
    for (Contact c : contacts) {
      result.addAll(c.getPeople());
    }
    return result;
  }

  @Override
  public String getName() {
    return name;
  }
}
