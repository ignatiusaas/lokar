package co.id.ogya.lokakarya.dto.technicalskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class TechnicalSkillDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("technical_skill")
    private String technicalSkill;

    @JsonProperty("enabled")
    private byte enabled;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
