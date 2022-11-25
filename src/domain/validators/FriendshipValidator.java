package domain.validators;
import domain.Friendship;

public class FriendshipValidator implements Validator<Friendship>{

    @Override
    public void validate(Friendship entity) throws ValidationException {
        if (entity.getUtilizator1().getId() == entity.getUtilizator2().getId())
            throw new ValidationException("Prietenii nu pot avea acelasi ID!");
    }
}
