package soo.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "agency")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Agency extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq", unique = true, nullable = false, updatable = false)
	private Integer seq;

	@Column(name = "insert_user", nullable = true)
	private Integer insertUser;

	@Column(name = "update_user", nullable = true)
	private Integer updateUser;

	@Column(name = "use_yn")
	@ColumnDefault("0")
	private Integer useYn;

	@Column(name = "order_value")
	@ColumnDefault("0")
	private Integer orderValue;

	@Column(name = "type_code")
	@ColumnDefault("0")
	private Integer typeCode;

	@Column(name = "name", length = 500)
	private String name;

	@Column(name = "content", length = 4000)
	private String content;

	@Column(name = "business_name", length = 500)
	private String businessName;

	@Column(name = "business_number", length = 500)
	private String businessNumber;

	@Column(name = "foundation", length = 500)
	private String foundation;

	@Column(name = "operating_corp", length = 500)
	private String operatingCorp;

	@Column(name = "owner", length = 500)
	private String owner;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "foundation_date")
	private Date foundationDate;

	@Column(name = "donation_deduction", length = 500)
	private String donationDeduction;

	@Column(name = "telephone", length = 500)
	private String telephone;

	@Column(name = "address", length = 500)
	private String address;

	@Column(name = "homepage", length = 500)
	private String homepage;

	@Column(name = "image_seq")
	@ColumnDefault("0")
	private Integer imageSeq;

	@Column(name = "image_path", length = 500)
	private String imagePath;

	@Column(name = "image_file", length = 500)
	private String imageFile;

	@Column(name = "image_thumb_seq")
	@ColumnDefault("0")
	private Integer imageThumbSeq;

	@Column(name = "image_thumb_path", length = 500)
	private String imageThumbPath;

	@Column(name = "image_thumb_file", length = 500)
	private String imageThumbFile;

	@Column(name = "view_count")
	@ColumnDefault("0")
	private Integer viewCount;

	@Column(name = "agency_type_order") // 단체타입 정렬값(agency.type_code * -1)
	@ColumnDefault("0")
	private Integer agencyTypeOrder;

	@Column(name = "pick_count")
	@ColumnDefault("0")
	private Integer pickCount;

	@Column(name = "story_count")
	@ColumnDefault("0")
	private Integer storyCount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "new_end_date")
	private Date newEndDate;

	@Column(name = "current_fund_amount")
	@ColumnDefault("0")
	private Integer currentFundAmount;

	@Column(name = "current_fund_count")
	@ColumnDefault("0")
	private Integer currentFundCount;

	// 2022.08.17 컬럼 생성
	@Column(name = "manager_name", columnDefinition="varchar(100) COMMENT '담당자 명'")
	private String managerName;

	@Column(name = "manager_email", columnDefinition="varchar(100) COMMENT '담당자 이메일'")
	private String managerEmail;

	@Column(name = "manager_telephone", columnDefinition="varchar(100) COMMENT '담당자 전화번호'")
	private String managerTelephone;

	@Column(name = "introduce", columnDefinition="TEXT COMMENT '단체소개'")
	private String introduce;

	@Column(name = "introduce_sub", columnDefinition="TEXT COMMENT '단체 한 줄 소개'")
	private String introduceSub;

	@Column(name = "bank_name", columnDefinition="varchar(100) COMMENT '계좌 은행명'")
	private String bankName;

	@Column(name = "bank_num", columnDefinition="varchar(100) COMMENT '계좌번호'")
	private String bankNum;

	@Column(name = "bank_holder", columnDefinition="varchar(100) COMMENT '계좌 예금주'")
	private String bankHolder;

	@Column(name = "receipt_yn", columnDefinition="int(10) NOT NULL DEFAULT 0 COMMENT '기부금영수증 신청 여부 0:n, 1:y'")
	private Integer receiptYn;

	@Column(name = "supplier", columnDefinition="varchar(500) COMMENT '발급처'")
	private String supplier;

	@Column(name = "doc_id", columnDefinition="varchar(100) COMMENT '필수서류 문서아이디'")
	private String docId;

	@Column(name = "business_area", columnDefinition="varchar(255) COMMENT '사업영역'")
	private String businessArea;

	@Column(name = "business_area_code", columnDefinition="varchar(255) COMMENT '사업영역 코드'")
	private String businessAreaCode;

	@Column(name = "post_code", columnDefinition="varchar(30) COMMENT '우편번호'")
	private String postCode;

	@Column(name = "address_detail", columnDefinition="varchar(300) COMMENT '상세주소'")
	private String addressDetail;

	@Column(name = "supplier_other", columnDefinition="varchar(300) COMMENT '발급처 직접입력'")
	private String supplierOther;

//	@Column(name = "category_list")
//	public List<Category> categoryList;
//
//	@Column(name = "new_yn")
//	private Integer newYn ;
//
//	@Column(name = "pick_yn")
//	private Integer pickYn ;
//
//	@Column(name = "story_comment_first")
//	private StoryComment storyCommentFirst;
}










