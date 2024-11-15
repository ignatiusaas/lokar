package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillUpdateDto;
import co.id.ogya.lokakarya.entities.AttitudeSkill;
import co.id.ogya.lokakarya.repositories.AttitudeSkillRepo;
import co.id.ogya.lokakarya.services.AttitudeSkillServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class AttitudeSkillServImpl implements AttitudeSkillServ {
    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Override
    public List<AttitudeSkillDto> getAllAttitudeSkill() {
        log.info("Attempting to fetch all AttitudeSkills");
        List<AttitudeSkillDto> listResult = new ArrayList<>();
        try {
            List<AttitudeSkill> listData = attitudeSkillRepo.getAttitudeSkills();
            log.debug("Fetched {} AttitudeSkills from repository", listData.size());
            for (AttitudeSkill data : listData) {
                AttitudeSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AttitudeSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AttitudeSkillDto getAttitudeSkillById(String id) {
        log.info("Attempting to fetch AttitudeSkill by ID: {}", id);
        AttitudeSkillDto result = null;
        try {
            AttitudeSkill data = attitudeSkillRepo.getAttitudeSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched AttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AttitudeSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AttitudeSkillDto createAttitudeSkill(AttitudeSkillCreateDto attitudeSkillCreateDto) {
        log.info("Attempting to create a new AttitudeSkill with data: {}", attitudeSkillCreateDto);
        AttitudeSkillDto result = null;
        try {
            AttitudeSkill data = convertToEntityCreate(attitudeSkillCreateDto);
            AttitudeSkill savedData = attitudeSkillRepo.saveAttitudeSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created AttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AttitudeSkillDto updateAttitudeSkill(AttitudeSkillUpdateDto attitudeSkillUpdateDto) {
        log.info("Attempting to update AttitudeSkill with data: {}", attitudeSkillUpdateDto);
        AttitudeSkillDto result = null;
        try {
            AttitudeSkill data = convertToEntityUpdate(attitudeSkillUpdateDto);
            AttitudeSkill updatedData = attitudeSkillRepo.updateAttitudeSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAttitudeSkill(String id) {
        log.info("Attempting to delete AttitudeSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = attitudeSkillRepo.deleteAttitudeSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted AttitudeSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete AttitudeSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AttitudeSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AttitudeSkill convertToEntity(AttitudeSkillDto convertObject) {
        log.debug("Converting AttitudeSkillDto to entity: {}", convertObject);
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AttitudeSkill convertToEntityCreate(AttitudeSkillCreateDto convertObject) {
        log.debug("Converting AttitudeSkillCreateDto to entity: {}", convertObject);
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AttitudeSkill convertToEntityUpdate(AttitudeSkillUpdateDto convertObject) {
        log.debug("Converting AttitudeSkillUpdateDto to entity: {}", convertObject);
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AttitudeSkillDto convertToDto(AttitudeSkill convertObject) {
        log.debug("Converting AttitudeSkill entity to DTO: {}", convertObject);
        AttitudeSkillDto result = AttitudeSkillDto.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}