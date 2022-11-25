package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;

public interface Service<ID> {
    /**
     * @param user - entitatea care trebuie adaugata
     * @return adevarat daca entitatea este adaugata
     *         altfel fals
     * @throws IllegalArgumentException
     *                  daca entitatea este egala cu null
     * @throws ValidationException
     *                  daca validarea nu a putut fi efectuata.
     */
    boolean addUtilizator(User user);


    /**
     * @param username - parametrul dupa care realizam stergerea
     * @return s-a sters entitatea
     *         altefl null
     * @throws IllegalArgumentException
     *                  daca id-ul este null
     */
    Entity<ID> deleteUtilizator(String username);


    /**
     * @param username1 si
     * @param username2 - usernameurile utilizatorilor intre care creeam prietenia
     *
     * @return adevarat daca a fost cvreeata
     *         fals atfel
     * @throws IllegalArgumentException
     *                  daca vreun username e null
     * @throws ValidationException
     *                  daca este acelasi username in ambele campuri
     */
    boolean createPrietenie(String username1, String username2);


    /**
     *  @param username1 si
     *  @param username2 - usernameurile dintre care trebuie sa stergem prietenia
     *
     *  @return daca prietenia exista, se sterge
     *          null daca nu
     *  @throws IllegalArgumentException
     *               daca unul din cele 2 e null
     */
    Entity<ID> deletePrietenie(String username1, String username2);


    /**
    * @return un iterator cu toti utilizatorii
    */
    Iterable<User> getAllUtilizatori();


    /**
     * @return un iterator cu toate prieteniile
     */
    Iterable<Friendship> getAllPrietenii();
}

