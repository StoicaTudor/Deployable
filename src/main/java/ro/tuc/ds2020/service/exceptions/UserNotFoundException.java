package ro.tuc.ds2020.service.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(Object searchCriteria, Object searchCriteriaValue) {
        super("Could not find user by " + searchCriteria.toString() + " -> " + searchCriteriaValue.toString());
    }
}
