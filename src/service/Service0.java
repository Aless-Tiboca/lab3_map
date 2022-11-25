package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;
import repository.Repository0;
import java.util.UUID;

public class Service0 implements Service<UUID> {
    private Repository0<UUID, User> utilizatorRepo;
    private Repository0<UUID, Friendship> prietenieRepo;

    public Service0(Repository0<UUID, User> utilizatorRepo, Repository0<UUID, Friendship> prietenieRepo) {
        this.utilizatorRepo = utilizatorRepo;
        this.prietenieRepo = prietenieRepo;
    }

    @Override
    public boolean addUtilizator(User user) {
        Entity<UUID> u = null;
        try {
            if (user.getFirstName() == null)
                throw new IllegalArgumentException("usernameul nu trebuie sa fie null!");
            else if (getUtilizatorByusername(user.getusername()) != null)
                throw new IllegalArgumentException("Exista deja un cont cu acest username!");
            u = utilizatorRepo.save(user);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        if (u != null) {
            System.err.println("Exista un alt utilizator cu acest ID");
            return false;
        }

        return true;
    }

    @Override
    public Entity<UUID> deleteUtilizator(String username) {
        User u = null;
        try {
            u = getUtilizatorByusername(username);
            if (u == null) {
                System.err.println("Nu exista niciun utilizator cu acest ID!");
                return null;
            }

            for (User friend : u.getFriends()) {
                for (Friendship p : prietenieRepo.findAll())
                    if ((p.getUtilizator1().equals(u) && p.getUtilizator2().equals(friend)) || (p.getUtilizator1().equals(friend) && p.getUtilizator2().equals(u))) {
                        prietenieRepo.delete(p.getId());
                        break;
                    }
                friend.removeFriend(u);
            }


            u = (User) utilizatorRepo.delete(u.getId());
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        if (u == null) {
            System.err.println("Nu exista niciun utilizator cu acest ID!");
            return null;
        }

        return u;
    }

    @Override
    public boolean createPrietenie(String username1, String username2) {

        Entity<UUID> f = null;
        User u1, u2;
        try {
            if (username1 == null || username2 == null)
                throw new IllegalArgumentException("usernameul trebuie sa nu fie null!");

            u1 = getUtilizatorByusername(username1);
            u2 = getUtilizatorByusername(username2);
            if (u1 == null || u2 == null || u1.equals(u2))
                throw new ValidationException("Nu exista cei doi utilizatori!");

            f = prietenieRepo.save(new Friendship(u1, u2));
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        if (f != null) {
            System.err.println("Utilizatorii acestia sunt deja prieteni!");
            return false;
        }

        u1.addFriend(u2);
        u2.addFriend(u1);
        return true;
    }


    @Override
    public Entity<UUID> deletePrietenie(String username1, String username2) {
        Entity<UUID> f = null;
        User u1, u2;
        try {
            if (username1 == null || username2 == null)
                throw new IllegalArgumentException("usernameurile trebuie sa fie diferite de null!");
            u1 = getUtilizatorByusername(username1);
            u2 = getUtilizatorByusername(username2);

            if (u1 == null || u2 == null || u1.equals(u2))
                throw new ValidationException("Nu exisat cei doi utilizatori!");

            Iterable<Friendship> l = prietenieRepo.findAll();
            for (Friendship elem : l)
                if (
                        (elem.getUtilizator1().getId().equals(u1.getId()) && elem.getUtilizator2().getId().equals(u2.getId()))
                                || (elem.getUtilizator1().getId().equals(u2.getId()) && elem.getUtilizator2().getId().equals(u1.getId()))
                ) {
                    f = prietenieRepo.delete(elem.getId());
                    break;
                }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        if (f == null) {
            System.err.println("Cei doi utilizatori nu sunt prieteni!");
            return null;
        }

        u1.removeFriend(u2);
        u2.removeFriend(u1);
        return f;
    }

    /**
     * @return iterator for all users
     */
    @Override
    public Iterable<User> getAllUtilizatori() {
        return utilizatorRepo.findAll();
    }

    /**
     * @return iterator for all friendships
     */
    @Override
    public Iterable<Friendship> getAllPrietenii() {
        return prietenieRepo.findAll();
    }

    public User getUtilizatorByusername(String username) {
        Iterable<User> it = utilizatorRepo.findAll();
        for (User u : it)
            if (u.getusername().equals(username))
                return u;
        return null;
    }
}
