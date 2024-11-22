package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementCreateDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementGetDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementUpdateDto;
import co.id.ogya.lokakarya.services.AchievementServ;
import co.id.ogya.lokakarya.utils.ServerResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/achievement")
public class AchievementController extends ServerResponseList {
    @Autowired
    AchievementServ achievementServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAchievements() {
        log.info("Fetching all achievements");
        long startTime = System.currentTimeMillis();

        try {
            List<AchievementDto> result = achievementServ.getAllAchievement();
            ManagerDto<List<AchievementDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all achievements in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all achievements: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch achievements", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAchievementById(@PathVariable String id) {
        log.info("Fetching achievement with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AchievementDto result = achievementServ.getAchievementById(id);
            ManagerDto<AchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched achievement with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching achievement by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch achievement with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAchievementGets() {
        log.info("Fetching all achievements");
        long startTime = System.currentTimeMillis();

        try {
            List<AchievementGetDto> result = achievementServ.getAllAchievementGet();
            ManagerDto<List<AchievementGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all achievements in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all achievements: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch achievements", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAchievementGetById(@PathVariable String id) {
        log.info("Fetching achievement with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AchievementGetDto result = achievementServ.getAchievementGetById(id);
            ManagerDto<AchievementGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched achievement with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching achievement by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch achievement with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAchievement(@RequestBody AchievementCreateDto achievementCreateDto) {
        log.info("Creating new achievement with data: {}", achievementCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AchievementDto result = achievementServ.createAchievement(achievementCreateDto);
            ManagerDto<AchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new achievement in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating achievement: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create achievement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAchievement(@RequestBody AchievementUpdateDto achievementUpdateDto) {
        log.info("Updating achievement with data: {}", achievementUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AchievementDto result = achievementServ.updateAchievement(achievementUpdateDto);
            ManagerDto<AchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated achievement in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating achievement: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update achievement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAchievement(@PathVariable String id) {
        log.info("Deleting achievement with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = achievementServ.deleteAchievement(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted achievement with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting achievement with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete achievement with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}