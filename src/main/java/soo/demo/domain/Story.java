package soo.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import soo.demo.domain.observer.StoryObserver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(StoryObserver.class)
@Entity
@Table(name = "story")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Story extends AbstractMappedEntity implements Serializable {

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

    @Column(name = "type_code", columnDefinition = "int(10) default 0 comment '(1: 사연, 2: 단체, 3: 공동 모금함, 4: 모금함(지원대상별), 5: 돌고후원)'")
    @ColumnDefault("0")
    private Integer typeCode;

    @Column(name = "fund_type_code", columnDefinition = "int(10) default 0 comment '1: 일시 2: 정기'")
    private Integer fundTypeCode;

    @Column(name = "agency_seq")
    private Integer agencySeq;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "content", length = 4000)
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "business_start_dt")
    private Date businessStartDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "business_end_dt")
    private Date businessEndDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "fund_start_dt")
    private Date fundStartDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "fund_end_dt")
    private Date fundEndDt;

    @Column(name = "target_fund_amount")
    @ColumnDefault("0")
    private Integer targetFundAmount;

    @Column(name = "current_fund_amount")
    @ColumnDefault("0")
    private Integer currentFundAmount;

    @Column(name = "current_fund_count")
    @ColumnDefault("0")
    private Integer currentFundCount;

    @Column(name = "image_seq")
    @ColumnDefault("0")
    private Integer imageSeq;

    @Column(name = "image_path", length = 500)
    private String imagePath;

    @Column(name = "image_file", length = 500)
    private String imageFile;

    @Column(name = "link_uri", length = 500)
    private String linkUri;

    @Column(name = "agency_type_order")
    @ColumnDefault("0")
    private Integer agencyTypeOrder;

    // 금액, 중복 상관없이 pick_content 에 저장되는 카운트
    @Column(name = "pick_count")
    @ColumnDefault("0")
    private Integer pickCount;

    @Column(name = "fund_rate")
    private Float fundRate;

    @Column(name = "person_fund_amount")
    @ColumnDefault("0")
    private Integer personFundAmount;

    @Column(name = "person_fund_count")
    @ColumnDefault("0")
    private Integer personFundCount;

    @Column(name = "company_fund_amount")
    @ColumnDefault("0")
    private Integer companyFundAmount;

    @Column(name = "company_fund_count")
    @ColumnDefault("0")
    private Integer companyFundCount;

    @Column(name = "double_up_fund_amount")
    @ColumnDefault("0")
    private Integer doubleUpFundAmount;

    @Column(name = "double_up_fund_count")
    @ColumnDefault("0")
    private Integer doubleUpFundCount;

    @Column(name = "action_fund_amount")
    @ColumnDefault("0")
    private Integer actionFundAmount;

    // point_info 에 있는 응원/공유/댓글/터치캠페인 (amount>0) 의 user_seq 중복제거카운트
    @Column(name = "action_fund_count")
    @ColumnDefault("0")
    private Integer actionFundCount;

    @Column(name = "current_product_amount")
    @ColumnDefault("0")
    private Integer currentProductAmount;

    @Column(name = "person_product_amount")
    @ColumnDefault("0")
    private Integer personProductAmount;

    @Column(name = "company_product_amount")
    @ColumnDefault("0")
    private Integer companyProductAmount;

    @Column(name = "action_product_amount")
    @ColumnDefault("0")
    private Integer actionProductAmount;

    @Column(name = "story_type_code")
    @ColumnDefault("0")
    private Integer storyTypeCode;

    // 금액, 중복 상관없이 story_comment 에 저장되는 카운트
    @Column(name = "comment_count")
    @ColumnDefault("0")
    private Integer commentCount;

    // 금액, 중복 상관없이 share_content 에 저장되는 카운트
    @Column(name = "share_count")
    @ColumnDefault("0")
    private Integer shareCount;

    @Column(name = "product_action_value")
    @ColumnDefault("0")
    private Integer productActionValue;

    @Column(name = "product_action_yn")
    @ColumnDefault("0")
    private Integer productActionYn;

    @Column(name = "relation_story_seq")
    @ColumnDefault("0")
    private Integer relationStorySeq;

    @Column(name = "receipt_path", length = 500)
    private String receiptPath;

    @Column(name = "receipt_file", length = 500)
    private String receiptFile;

    // (v2) 메인페이지 노출 여부(0:N, 1:Y)
    @Column(name = "main_section_yn")
    private Integer mainSectionYn;

    @Column(name = "main_section_update_date", columnDefinition = "datetime DEFAULT NULL COMMENT '메인노출여부 갱신일'")
    private Date mainSectionUpdateDate;

    @Column(name = "benefit_target_content", columnDefinition = "varchar(500) comment '지원 대상 명 (수혜대상)'")
    private String benefitTargetContent;

    @Column(name = "benefit_target_code", columnDefinition = "varchar(500) comment '지원 대상 코드 (수혜대상)'")
    private String benefitTargetCode;

    @Column(name = "benefit_target_seq", columnDefinition = "bigint(20) default '0' comment '지원 대상(=배분모금함) seq'")
    private Integer benefitTargetSeq;

    @Column(name = "promotion", columnDefinition = "varchar(500) comment '프로모션 글'")
    private String promotion;

    @Column(name = "tags", columnDefinition = "varchar(500) comment '태그설정 (#로 구분)'")
    private String tags;

    @Column(name = "target_text", columnDefinition = "varchar(500) comment '지원대상 및 인원 텍스트 ex) 쪽방촌, 20가정'")
    private String targetText;

    @Column(name = "pick_action_amount", columnDefinition = "double default 0 comment '응원 참여 기부금'")
    private Integer pickActionAmount;

    @Column(name = "share_action_amount", columnDefinition = "double default 0 comment '공유 참여 기부금'")
    private Integer shareActionAmount;

    @Column(name = "comment_action_amount", columnDefinition = "double default 0 comment '댓글 참여 기부금'")
    private Integer commentActionAmount;

    @Column(name = "pick_action_limit", columnDefinition = "int(10) default 0 comment '응원 참여 기부 제한 횟수'")
    private Integer pickActionLimit;

    @Column(name = "share_action_limit", columnDefinition = "int(10) default 0 comment '공유 참여 기부 제한 횟수'")
    private Integer shareActionLimit;

    @Column(name = "comment_action_limit", columnDefinition = "int(10) default 0 comment '댓글 참여 기부 제한 횟수'")
    private Integer commentActionLimit;

    @Column(name = "pick_action_limit_yn", columnDefinition = "int(10) default 0 comment '응원 참여 기부 제한 여부 (0: 무제한, 1: 제한)'")
    private Integer pickActionLimitYn;

    @Column(name = "share_action_limit_yn", columnDefinition = "int(10) default 0 comment '공유 참여 기부 제한 여부 (0: 무제한, 1: 제한)'")
    private Integer shareActionLimitYn;

    @Column(name = "comment_action_limit_yn", columnDefinition = "int(10) default 0 comment '댓글 참여 기부 제한 여부 (0: 무제한, 1: 제한)'")
    private Integer commentActionLimitYn;

    @Column(name = "category_code", columnDefinition = "int(10) default 1 comment '사연 유형 (1: 기본 유형, 2: 터치 캠페인 유형)'")
    private Integer categoryCode;

    @Column(name = "target_fund_amount_disp_yn", columnDefinition = "int(10) default 1 comment '목표 모금액 노출 여부 (1: 노출, 0: 비노출)'")
    private Integer targetFundAmountDispYn;

    @Column(name = "touch_before_image_seq", columnDefinition = "int(10) comment '터치 전 이미지키'")
    private Integer touchBeforeImageSeq;

    @Column(name = "touch_before_image_path", columnDefinition = "varchar(500) comment '터치 전 이미지경로'")
    private String touchBeforeImagePath;

    @Column(name = "touch_before_image_file", columnDefinition = "varchar(500) comment '터치 전 이미지파일명'")
    private String touchBeforeImageFile;

    @Column(name = "touch_after_image_seq", columnDefinition = "int(10) comment '터치 후 이미지키'")
    private Integer touchAfterImageSeq;

    @Column(name = "touch_after_image_path", columnDefinition = "varchar(500) comment '터치 후 이미지경로'")
    private String touchAfterImagePath;

    @Column(name = "touch_after_image_file", columnDefinition = "varchar(500) comment '터치 후 이미지파일명'")
    private String touchAfterImageFile;

    @Column(name = "manager_name", columnDefinition = "varchar(100) comment '담당자명'")
    private String managerName;

    @Column(name = "manager_email", columnDefinition = "varchar(200) comment '담당자 이메일'")
    private String managerEmail;

    @Column(name = "business_start_dt_text", columnDefinition = "varchar(500) comment '사업 기간 텍스트'")
    private String businessStartDtText;

    @Column(name = "fund_routine_period", columnDefinition = "int(10) default 0 comment '정기-모금기간 (30: 30일, 60: 60일, 90: 90일, 0: 목표금액달성시까지)'")
    private Integer fundRoutinePeriod;

    @Column(name = "state", columnDefinition = "varchar(25) default 'fundraising' comment '모금 상태 (fundraising:모금중, fundraisingEnds:모금종료)'")
    private String state;

    // 직접후원 액수가 가장 큰 유저
    @Column(name = "largest_amount_user", columnDefinition = "int(10) COMMENT '직접후원 액수가 가장 큰 유저'")
    private Integer largestAmountUser;

    // 직접후원 액수가 가장 큰 유저 기부자명 (닉네임/태그/익명)
    @Column(name = "largest_amount_user_name", columnDefinition = "varchar(500) comment '직접후원 액수가 가장 큰 유저 기부자명'")
    private String largestAmountUserName;

    // 직접후원 액수가 가장 큰 유저 기부자명 구분  (닉네임/태그/익명 구분)
    @Column(name = "largest_amount_user_name_type", columnDefinition = "int(10) default 0 comment '직접후원 액수가 가장 큰 유저 기부자명 타입'")
    @ColumnDefault("0")
    private Integer largestAmountUserNameType;

    // 직접후원 액수가 가장 큰 금액
    @Column(name = "largest_amount", columnDefinition = "DOUBLE NOT NULL DEFAULT 0 COMMENT '직접후원 액수가 가장 큰 금액'")
    @ColumnDefault("0")
    private Double largestAmount;

    // 사연 부모키 (정기 사연일 경우, 해당) 부모키 없을때, 0
    @Column(name = "parent_seq", columnDefinition = "int(10) default 0 comment '사연 부모키 (정기 사연일 경우, 해당)'")
    private Integer parentSeq;

    // 사연 그룹 아이디 (정기 사연일 경우 그룹으로 묶여야함, 일시일 경우, null 값일 수 있음)
    @Column(name = "group_id", columnDefinition = "varchar(40) comment '사연 그룹 아이디 (정기 사연일 경우 그룹으로 묶여야함)'")
    private String groupId;

    // 사연 회차
    @Column(name = "story_round", columnDefinition = "int(10) default 1 comment '사연 회차'")
    private Integer storyRound;

    // 정기사연 모금중단 여부 (0: 진행중 1: 중단) > 1 일때 배치 제외
    @Column(name = "regular_stop_yn", columnDefinition = "int(10) default 0 comment '정기사연 모금중단 여부 (0: 진행중 1: 중단)'")
    private Integer regularStopYn;

    // 모금함에서 배분한 금액
    @Column(name = "fundMap_fund_amount", columnDefinition = "int(10) NOT NULL DEFAULT 0 COMMENT '모금함배분 금액'")
    private Integer fundMapFundAmount;

    // 모금함 배분 횟수
    @Column(name = "fundMap_fund_count", columnDefinition = "int(10) NOT NULL DEFAULT 0 COMMENT '모금함배분 횟수'")
    private Integer fundMapFundCount;

    // 강제종료 전 모금시작일
    @Column(name = "origin_fund_start_dt", columnDefinition = "datetime DEFAULT NULL COMMENT '강제종료 전 모금시작일'")
    private Date originFundStartDt;

    // 강제종료 전 모금종료일
    @Column(name = "origin_fund_end_dt", columnDefinition = "datetime DEFAULT NULL COMMENT '강제종료 전 모금종료일'")
    private Date originFundEndDt;

    // 다음 회차에 적용될 주기
    @Column(name = "fund_routine_period_next", columnDefinition = "int(10) default 0 comment '정기-다음 회차에 적용될 주기 (30: 30일, 60: 60일, 90: 90일, 0: 목표금액달성시까지)'")
    private Integer fundRoutinePeriodNext;

    // 실제 모금 종료된 날짜 (웹 사용 용도)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "real_end_dt", columnDefinition = "datetime DEFAULT NULL COMMENT '실제 모금 종료된 날짜 (웹 사용 용도)'")
    private Date realEndDt;

    // v1의 companyFundAmount. PointInfo에는 데이터가 없으며, donate() 실행 시 PointInfo 데이터와 해당 컬럼을 합쳐서 최종 계산. (v1에서의 companyFundAmount는 상세 내역이 없으므로 v2로 이관 시 별도 컬럼으로 기록하기 위해 생성)
    @Column(name = "orig_company_fund_amount", columnDefinition = "int(10) default 0 comment 'v1의 companyFundAmount. 변경되지 않음.'")
    @ColumnDefault("0")
    private Integer origCompanyFundAmount;

    // v1의 companyFundCount. PointInfo에는 데이터가 없으며, donate() 실행 시 PointInfo 데이터와 해당 컬럼을 합쳐서 최종 계산. (v1에서의 companyFundCount는 상세 내역이 없으므로 v2로 이관 시 별도 컬럼으로 기록하기 위해 생성)
    @Column(name = "orig_company_fund_count", columnDefinition = "int(10) default 0 comment 'v1의 companyFundCount. 변경되지 않음.'")
    @ColumnDefault("0")
    private Integer origCompanyFundCount;

    // (B2B) 참여 대상. (EMPLOYEE:임직원만, WITH_USERS:사용자도함께, NULL:나눔파트너 X)
    @Column(name = "donation_target", length = 20, columnDefinition = "varchar(20) comment '참여 대상. (EMPLOYEE:임직원만, WITH_USERS:사용자도함께, NULL:나눔파트너 X)'")
    @Enumerated(EnumType.STRING)
    private StoryDonationTarget donationTarget;

    // (B2B) 나눔파트너 한도금액. NULL일 경우 나눔파트너 참여하지 않음
    @Column(name = "partner_amount_limit", columnDefinition = "int comment '나눔파트너 한도금액. NULL일 경우 나눔파트너 참여하지 않음'")
    private Integer partnerAmountLimit;


    public void updateMainSectionYn(Integer mainSectionYn) {
        this.mainSectionYn = mainSectionYn;
        this.mainSectionUpdateDate = new Date();
    }
}
