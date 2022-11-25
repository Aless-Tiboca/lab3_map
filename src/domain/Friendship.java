package domain;

import java.util.Objects;
import java.util.UUID;

public class Friendship extends Entity<UUID>{


    private User user1;
    private User user2;

    public Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.setId(UUID.randomUUID());
    }

    public User getUtilizator1() {
        return user1;
    }

    public User getUtilizator2() {
        return user2;
    }

    public void setUtilizator1(User user1) {
        this.user1 = user1;
    }

    public void setUtilizator2(User user2) {
        this.user2 = user2;
    }

    @Override
    public String toString() {
        return "\nPrietenie: \n" +
                "Utilizatorul = " + user1.getFirstName() +
                " " + user1.getLastName() +
                " " + user1.getusername() +

                ",\nUtilizatorul = " + user2.getFirstName() +
                " " + user2.getLastName() +
                " " + user2.getusername() +
                ';' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship friendship = (Friendship) o;
        return Objects.equals(user1, friendship.user1) && Objects.equals(user2, friendship.user2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}
