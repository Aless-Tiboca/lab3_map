package domain;
import java.util.*;

public class User extends Entity<UUID>{
    private String firstName;
    private String lastName;
    private String username;
    private Map<UUID, User> friends;

    public User(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        friends = new HashMap<>();
        this.setId(UUID.randomUUID());
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getusername() {return username;}

    public void setusername(String username) {this.username = username;}

    public Iterable<User> getFriends() {return friends.values();}

    @Override
    public String toString() {
        return "Utilizatorul: " +
                "\nPrenumele: " + firstName  +
                ",\nNumele: " + lastName  +
                ",\nusername-ul: " + username +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {return Objects.hash(getFirstName(), getLastName(), getusername());}

    public void addFriend(User u) {friends.put(u.id, u);}

    public boolean removeFriend(User u) {
        return friends.remove(u.id) != null;
    }

}