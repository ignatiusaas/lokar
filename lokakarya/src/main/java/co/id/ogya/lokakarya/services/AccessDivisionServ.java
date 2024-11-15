package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionCreateDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionUpdateDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionDto;
import co.id.ogya.lokakarya.entities.AccessDivision;

import java.util.List;

public interface AccessDivisionServ {
    List<AccessDivisionDto> getAllAccessDivision();
    AccessDivisionDto getAccessDivisionById(String id);
    AccessDivisionDto createAccessDivision(AccessDivisionCreateDto accessDivisionCreateDto);
    AccessDivisionDto updateAccessDivision(AccessDivisionUpdateDto accessDivisionUpdateDto);
    boolean deleteAccessDivision(String id);

}