package xtron.airbnb_ms.sharedkernel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AbstractAuditingEntity.class)
public abstract class AbstractAuditingEntity <T> implements Serializable {
     public abstract T getId();

     @CreatedDate
     @Column(updatable = false, name="created_date")
     private Instant createDate = Instant.now();

     @LastModifiedBy
     @Column(name = "last_modified_date")
     private Instant lastModifiedDate = Instant.now();
}
