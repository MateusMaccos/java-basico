package dio.boards.persistence.entity;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BlockEntity {
    private Long id;
    private OffsetDateTime blockedAt;
    private OffsetDateTime unblockedAt;
    private String blockReason;
    private String unblockReason;


}
