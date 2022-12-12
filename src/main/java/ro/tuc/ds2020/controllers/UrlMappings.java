package ro.tuc.ds2020.controllers;

public class UrlMappings {
    public static final String URL_PREFIX = "/energyWhatever";

    public static final String ADMINISTRATOR = URL_PREFIX + "/administrator";
    public static final String CLIENT = URL_PREFIX + "/client";
    public static final String AUTH = URL_PREFIX + "/auth";

    public static final String GET_USER = "/getUser";
    public static final String GET_ALL_USERS = "/getAllUsers";
    public static final String CREATE_USER = "/createUser";
    public static final String CREATE_EMPTY_USER = "/createEmptyUser";
    public static final String UPDATE_USER = "/updateUser";
    public static final String DELETE_USER = "/deleteUser";

    public static final String GET_DEVICE = "/getDeviceById";
    public static final String GET_ALL_DEVICES = "/getAllDevices";
    public static final String CREATE_DEVICE = "/createDevice";
    public static final String CREATE_EMPTY_DEVICE = "/createEmptyDevice";
    public static final String UPDATE_DEVICE = "/updateDevice";
    public static final String DELETE_DEVICE = "/deleteDevice";
    public static final String ADD_DEVICE_TO_USER = "/addDeviceToUser";

    public static final String DELETE_USER_DEVICE = "/deleteUserDevice";
    public static final String CREATE_USER_DEVICE = "/createUserDevice";
    public static final String GET_USER_DEVICES = "/getUserDevices";

    public static final String GET_USER_DEVICE_ENERGY_CONSUMPTION = "/getUserDeviceEnergyConsumption";

    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String DUMMY_DATA = "/dummy";
}
