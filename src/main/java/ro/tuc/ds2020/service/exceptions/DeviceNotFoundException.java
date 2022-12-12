package ro.tuc.ds2020.service.exceptions;

public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException(Object searchCriteria, Object searchCriteriaValue) {
        super("Could not find device by " + searchCriteria.toString() + " -> " + searchCriteriaValue.toString());
    }
}
