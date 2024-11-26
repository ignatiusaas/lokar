package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;
import co.id.ogya.lokakarya.repositories.EmpAttitudeSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class EmpAttitudeSkillRepoImpl implements EmpAttitudeSkillRepo {

    private final RowMapper<EmpAttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(EmpAttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpAttitudeSkill> getEmpAttitudeSkills() {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL";
        log.info("Fetching all EmpAttitudeSkills with query: {}", sql);
        try {
            List<EmpAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} EmpAttitudeSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmpAttitudeSkill getEmpAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Fetching EmpAttitudeSkill by ID: {} with query: {}", id, sql);
        try {
            EmpAttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched EmpAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAttitudeSkillGets() {
        String sql = "SELECT eas.ID, FULL_NAME, ATTITUDE_SKILL, SCORE, ASSESSMENT_YEAR " +
                "FROM TBL_EMP_ATTITUDE_SKILL eas " +
                "LEFT JOIN TBL_ATTITUDE_SKILL ats ON eas.ATTITUDE_SKILL_ID = ats.ID " +
                "LEFT JOIN TBL_APP_USER au ON eas.USER_ID = au.ID";
        log.info("Fetching all EmpAttitudeSkills with LEFT JOIN query: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} EmpAttitudeSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getEmpAttitudeSkillGetById(String id) {
        String sql = "SELECT eas.ID, FULL_NAME, ATTITUDE_SKILL, SCORE, ASSESSMENT_YEAR " +
                "FROM TBL_EMP_ATTITUDE_SKILL eas " +
                "LEFT JOIN TBL_ATTITUDE_SKILL ats ON eas.ATTITUDE_SKILL_ID = ats.ID " +
                "LEFT JOIN TBL_APP_USER au ON eas.USER_ID = au.ID " +
                "WHERE eas.ID = ?";
        log.info("Fetching EmpAttitudeSkill by ID: {} with LEFT JOIN query: {}", id, sql);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched EmpAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        empAttitudeSkill.prePersist();
        String sql = "INSERT INTO TBL_EMP_ATTITUDE_SKILL (ID, USER_ID, ATTITUDE_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Saving EmpAttitudeSkill: {} with query: {}", empAttitudeSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAttitudeSkill.getId(), empAttitudeSkill.getUserId(),
                    empAttitudeSkill.getAttitudeSkillId(), empAttitudeSkill.getScore(),
                    empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved EmpAttitudeSkill: {}", empAttitudeSkill);
                return empAttitudeSkill;
            } else {
                log.warn("No rows affected while saving EmpAttitudeSkill: {}", empAttitudeSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving EmpAttitudeSkill: {}. Error: {}", empAttitudeSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        String sql = "UPDATE TBL_EMP_ATTITUDE_SKILL SET USER_ID = ?, ATTITUDE_SKILL_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, " +
                "UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Updating EmpAttitudeSkill with ID: {} using query: {}", empAttitudeSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAttitudeSkill.getUserId(), empAttitudeSkill.getAttitudeSkillId(),
                    empAttitudeSkill.getScore(), empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getUpdatedBy(),
                    empAttitudeSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated EmpAttitudeSkill: {}", empAttitudeSkill);
                return empAttitudeSkill;
            } else {
                log.warn("No rows affected while updating EmpAttitudeSkill with ID: {}", empAttitudeSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating EmpAttitudeSkill with ID: {}. Error: {}", empAttitudeSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmpAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Deleting EmpAttitudeSkill with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted EmpAttitudeSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting EmpAttitudeSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting EmpAttitudeSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
