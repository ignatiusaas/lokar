package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillCreateDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillGetDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillUpdateDto;

import java.util.List;
import java.util.Map;

public interface EmpTechnicalSkillServ {
    List<EmpTechnicalSkillDto> getAllEmpTechnicalSkill();
    EmpTechnicalSkillDto getEmpTechnicalSkillById(String id);
    EmpTechnicalSkillDto createEmpTechnicalSkill(EmpTechnicalSkillCreateDto empTechnicalSkillCreateDto);
    EmpTechnicalSkillDto updateEmpTechnicalSkill(EmpTechnicalSkillUpdateDto empTechnicalSkillUpdateDto);
    boolean deleteEmpTechnicalSkill(String id);
    List<EmpTechnicalSkillGetDto> getAllEmpTechnicalSkillGets();
    List<EmpTechnicalSkillGetDto> getEmpTechnicalSkillGetByUserId(String userId);
}
