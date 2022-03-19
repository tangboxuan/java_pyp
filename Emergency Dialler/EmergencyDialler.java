import java.util.Iterator;
import java.util.Set;

public abstract class EmergencyDialler {
  protected final PriorityQueue<Contact> queue = new LinkedListPriorityQueue<>();
  protected final Location location;
  private final Dialler dialler;

  public EmergencyDialler(Location location, Dialler dialler) {
    this.location = location;
    this.dialler = dialler;
  }

  public Contact emergency() {
    Iterator<Contact> iter = queue.iterator();
    while(iter.hasNext()) {
      boolean success = true;
      Contact contact = iter.next();
      for (Person p : contact.getPeople()) {
        if (!dialler.call(p.getTelephoneNumber(), "Emergency")) {
          success = false;
          break;
        }
      }
      if (success) {
        return contact;
      }
    }
    return null;
  }

  public abstract void addToEmergencyContactList(Contact contact);
}
