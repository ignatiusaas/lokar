package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillUpdateDto;
import co.id.ogya.lokakarya.services.GroupAttitudeSkillServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {
    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Override
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill() {
        log.info("Attempting to fetch all GroupAttitudeSkills");
        List<GroupAttitudeSkillDto> listResult = new ArrayList<>();
        try {
            List<GroupAttitudeSkill> listData = groupAttitudeSkillRepo.getGroupAttitudeSkills();
            log.debug("Fetched {} GroupAttitudeSkills from repository", listData.size());
            for (GroupAttitudeSkill data : listData) {
                GroupAttitudeSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all GroupAttitudeSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public GroupAttitudeSkillDto getGroupAttitudeSkillById(String id) {
        log.info("Attempting to fetch GroupAttitudeSkill by ID: {}", id);
        GroupAttitudeSkillDto result = null;
        try {
            GroupAttitudeSkill data = groupAttitudeSkillRepo.getGroupAttitudeSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched GroupAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching GroupAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto) {
        log.info("Attempting to create a new GroupAttitudeSkill with data: {}", groupAttitudeSkillCreateDto);
        GroupAttitudeSkillDto result = null;
        try {
            GroupAttitudeSkill data = convertToEntityCreate(groupAttitudeSkillCreateDto);
            GroupAttitudeSkill savedData = groupAttitudeSkillRepo.saveGroupAttitudeSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created GroupAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating GroupAttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto) {
        log.info("Attempting to update GroupAttitudeSkill with data: {}", groupAttitudeSkillUpdateDto);
        GroupAttitudeSkillDto result = null;
        try {
            GroupAttitudeSkill data = convertToEntityUpdate(groupAttitudeSkillUpdateDto);
            GroupAttitudeSkill updatedData = groupAttitudeSkillRepo.updateGroupAttitudeSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated GroupAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating GroupAttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteGroupAttitudeSkill(String id) {
        log.info("Attempting to delete GroupAttitudeSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = groupAttitudeSkillRepo.deleteGroupAttitudeSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted GroupAttitudeSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete GroupAttitudeSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting GroupAttitudeSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private GroupAttitudeSkill convertToEntity(GroupAttitudeSkillDto convertObject) {
        log.debug("Converting GroupAttitudeSkillDto to entity: {}", convertObject);
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkill convertToEntityCreate(GroupAttitudeSkillCreateDto convertObject) {
        log.debug("Converting GroupAttitudeSkillCreateDto to entity: {}", convertObject);
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkill convertToEntityUpdate(GroupAttitudeSkillUpdateDto convertObject) {
        log.debug("Converting GroupAttitudeSkillUpdateDto to entity: {}", convertObject);
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkillDto convertToDto(GroupAttitudeSkill convertObject) {
        log.debug("Converting GroupAttitudeSkill entity to DTO: {}", convertObject);
        GroupAttitudeSkillDto result = GroupAttitudeSkillDto.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
