public class AvgEmergencyDialler extends EmergencyDialler{

  public AvgEmergencyDialler(Location location, Dialler dialler) {
    super(location, dialler);
  }

  @Override
  public void addToEmergencyContactList(Contact contact) {
    int count = 0;
    double total = 0;
    for (Person p : contact.getPeople()) {
      count++;
      total += p.getAddress().distanceTo(location);
    }
    queue.add(total/count, contact);
  }
}
