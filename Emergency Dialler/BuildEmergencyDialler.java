public class BuildEmergencyDialler {

  public static void main(String[] args) {
    Contact contact1 = new Person("Jansen", 4, 2, -9);
    Group contact2 = new Group("contact2");
    Contact person1 = new Person("Jamil", 3, 0, 32);
    Contact person2 = new Person("Ji", 5, -4, -9);
    Contact person3 = new Person("Jane", 2, -4, 1);
    Group contact2sub = new Group("");
    contact2sub.add(person2);
    contact2sub.add(person3);
    contact2.add(person1);
    contact2.add(contact2sub);
    Group contact3 = new Group("contact3");
    Contact person4 = new Person("Joe", 1, 2, 3);
    contact3.add(person4);

    EmergencyDialler ed = new AvgEmergencyDialler(new Location(0, 0), new Dialler());
    ed.addToEmergencyContactList(contact1);
    ed.addToEmergencyContactList(contact2);
    ed.addToEmergencyContactList(contact3);
    ed.emergency();
  }
}
