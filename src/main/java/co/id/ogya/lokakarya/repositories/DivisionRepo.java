package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.Division;

import java.util.List;

public interface DivisionRepo {
    List<Division> getDivisions();

    Division getDivisionById(String id);

    Division saveDivision(Division division);

    Division updateDivision(Division division);

    Boolean deleteDivision(String id);

    Division getDivisionByName(String divisionName);
}
