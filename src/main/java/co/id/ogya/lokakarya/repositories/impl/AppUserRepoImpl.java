package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
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
public class AppUserRepoImpl implements AppUserRepo {

    private final RowMapper<AppUser> rowMapper = new BeanPropertyRowMapper<>(AppUser.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppUser> getAppUsers() {
        String sql = "SELECT * FROM TBL_APP_USER";
        try {
            log.info("Fetching all AppUsers");
            List<AppUser> appUsers = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppUser getAppUserById(String id) {
        String sql = "SELECT * FROM TBL_APP_USER WHERE ID = ?";
        try {
            log.info("Fetching AppUser by ID: {}", id);
            AppUser appUser = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching AppUser by ID: {}. Error: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUserGets() {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_NAME " +
                "FROM TBL_APP_USER au " +
                "LEFT JOIN TBL_DIVISION d ON au.DIVISION_ID = d.ID";
        try {
            log.info("Fetching all detailed AppUsers with LEFT JOIN");
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql);
            log.info("Fetched {} detailed AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching detailed AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAppUserGetById(String id) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_NAME " +
                "FROM TBL_APP_USER au " +
                "LEFT JOIN TBL_DIVISION d ON au.DIVISION_ID = d.ID " +
                "WHERE au.ID = ?";
        try {
            log.info("Fetching detailed AppUser by ID: {}", id);
            Map<String, Object> appUser = jdbcTemplate.queryForMap(sql, id);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by ID: {}. Error: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getAppUserByUsername(String username) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_NAME " +
                "FROM TBL_APP_USER au " +
                "LEFT JOIN TBL_DIVISION d ON au.DIVISION_ID = d.ID " +
                "WHERE LOWER(USERNAME) LIKE LOWER(CONCAT('%', ?, '%'))";
        try {
            log.info("Fetching detailed AppUser by username: {}", username);

            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, username);

            if (users.isEmpty()) {
                log.warn("No AppUser found for username: {}", username);
                return null;
            }

            if (users.size() > 1) {
                log.warn("Multiple AppUsers found for username: {}. Returning the first match.", username);
            }

            Map<String, Object> appUser = users.get(0);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;

        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by username: {}. Error: {}", username, e.getMessage(), e);
            return null;
        }
    }


    @Override
    public Map<String, Object> getAppUserByFullName(String fullName) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_NAME " +
                "FROM TBL_APP_USER au " +
                "LEFT JOIN TBL_DIVISION d ON au.DIVISION_ID = d.ID " +
                "WHERE LOWER(FULL_NAME) LIKE LOWER(CONCAT('%', ?, '%'))";
        try {
            log.info("Fetching detailed AppUser by full name: {}", fullName);

            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, fullName);

            if (users.isEmpty()) {
                log.warn("No AppUser found for full name: {}", fullName);
                return null;
            }

            if (users.size() > 1) {
                log.warn("Multiple AppUsers found for full name: {}. Returning the first match.", fullName);
            }

            // Return the first match
            Map<String, Object> appUser = users.get(0);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;

        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by full name: {}. Error: {}", fullName, e.getMessage(), e);
            return null;
        }
    }


    @Override
    public AppUser saveAppUser(AppUser appUser) {
        appUser.prePersist();
        String sql = "INSERT INTO TBL_APP_USER (ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            log.info("Saving AppUser: {}", appUser);
            int rowsAffected = jdbcTemplate.update(sql, appUser.getId(), appUser.getUsername(), appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(),
                    appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getDivisionId(), appUser.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to save AppUser: {}", appUser);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppUser: {}. Error: {}", appUser, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        String sql = "UPDATE TBL_APP_USER SET FULL_NAME = ?, POSITION = ?, EMAIL_ADDRESS = ?, EMPLOYEE_STATUS = ?, JOIN_DATE = ?, ENABLED = ?, PASSWORD = ?, " +
                "DIVISION_ID = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        try {
            log.info("Updating AppUser with ID: {}", appUser.getId());
            int rowsAffected = jdbcTemplate.update(sql, appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(),
                    appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getDivisionId(), appUser.getUpdatedBy(), appUser.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to update AppUser with ID: {}", appUser.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppUser with ID: {}. Error: {}", appUser.getId(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean deleteAppUser(String id) {
        String sql = "DELETE FROM TBL_APP_USER WHERE ID = ?";
        try {
            log.info("Deleting AppUser with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppUser with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppUser with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppUser with ID: {}. Error: {}", id, e.getMessage(), e);
            return false;
        }
    }
}
