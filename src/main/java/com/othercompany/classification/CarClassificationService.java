package com.othercompany.classification;

public interface CarClassificationService {

    String getCarType(String model) throws ServiceUnAvailableException, UnknownTypeException;

}
