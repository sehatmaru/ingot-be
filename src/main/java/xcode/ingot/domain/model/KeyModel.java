package xcode.ingot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import xcode.ingot.domain.enums.KeyTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "t_key")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class KeyModel {
    
    @Id @Column(name = "id", length = 36) @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "secure_id")
    private String secureId;

    @Column(name = "user_secure_id")
    private String userSecureId;

    @Column(name = "name")
    private String name;

    @Column(name = "key_type")
    private KeyTypeEnum keyType;

    @Column(name = "password")
    private String password;

    @Column(name = "note")
    private String note;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at")
    private Date createdAt;
 
    @Column(name = "updated_at")
    private Date updatedAt;
 
    @Column(name = "deleted_at")
    private Date deletedAt;
}
