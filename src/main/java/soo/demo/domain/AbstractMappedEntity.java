package soo.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Data
abstract public class AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@CreatedDate
	@JsonFormat(shape = Shape.STRING)
	@Column(name = "insert_date", columnDefinition = "timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일'")
	private Date insertDate;
	
	@LastModifiedDate
	@JsonFormat(shape = Shape.STRING)
	@Column(name = "update_date", columnDefinition = "timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정일'")
	private Date updateDate;
}










