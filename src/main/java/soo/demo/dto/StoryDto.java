package soo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import soo.demo.constant.Const;
import soo.demo.domain.StoryDonationTarget;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // 고유번호
    private Integer seq;

    // 등록일
    private Date insertDate;

    // 등록회원
    private Integer insertUser;

    // 수정일
    private Date updateDate;

    // 수정회원
    private Integer updateUser;

    // 사용여부 (0:n, 1:y)
    private Integer useYn;

    // 순서값 desc
    private Integer orderValue;

    // 구분
    private Integer typeCode;

    // 펀드 구분  (1: 일시, 2: 정기)
    private Integer fundTypeCode;

    // 기관키
    private Integer agencySeq;

    // 제목
    private String title;

    // 내용
    private String content;

    // 사업시작일
    private Date businessStartDt;

    // 사업종료일
    private Date businessEndDt;

    // 모금시작일
    private Date fundStartDt;

    // 모금종료일
    private Date fundEndDt;

    // 목표금액
    private Integer targetFundAmount;

    // 모금액
    private Integer currentFundAmount;

    // 모금 수
    private Integer currentFundCount;

    // 이미지키
    private Integer imageSeq;

    // 이미지 경로
    private String imagePath;

    // 이미지 파일
    private String imageFile;

    // 링크
    private String linkUri;

    // 단체타입 정렬값(agency.type_code * -1)
    private Integer agencyTypeOrder;

    // 선택 수 (금액, 중복 상관없이 pick_content 에 저장되는 카운트)
    private Integer pickCount;

    // 모금률
    private Float fundRate;

    // 개인 모금액
    private Integer personFundAmount;

    // 개인 모금 수
    private Integer personFundCount;

    // 기업 모금액
    private Integer companyFundAmount;

    // 기업 모금 수
    private Integer companyFundCount;

    // 더블업 모금액
    @Builder.Default
    private Integer doubleUpFundAmount = 0;

    // 더블업 모금 수
    @Builder.Default
    private Integer doubleUpFundCount = 0;

    // 참여 모금액
    private Integer actionFundAmount;

    // 참여 모금 수 (point_info 에 있는 응원/공유/댓글/터치캠페인 (amount>0) 의 user_seq 중복제거카운트)
    private Integer actionFundCount;

    // 모금 상품 누적
    private Integer currentProductAmount;

    // 개인 모금 상품 누적
    private Integer personProductAmount;

    // 기업 모금 상품 누적
    private Integer companyProductAmount;

    // 참여 모금 상품 누적
    private Integer actionProductAmount;

    // 사연 구분
    private Integer storyTypeCode;

    // 댓글 수 (금액, 중복 상관없이 story_comment 에 저장되는 카운트)
    private Integer commentCount;

    // 공유 수 (금액, 중복 상관없이 share_content 에 저장되는 카운트)
    private Integer shareCount;

    // 모금 상품 참여 기부 단위값 (ex 200)
    private Integer productActionValue;

    // 모금 상품 참여 사용여부
    private Integer productActionYn;

    // 연결 사연 키
    private Integer relationStorySeq;

    // 영수증 이미지 경로
    private String receiptPath;

    // 영수증 이미지 파일
    private String receiptFile;

    // (v2) 메인페이지 노출 여부(0:N, 1:Y)
    private Integer mainSectionYn;

    // 메인페이지 노출 여부 갱신일
    private Date mainSectionUpdateDate;

    // fund_end_days last-day=0 d-day
    private Long fundEndDays;

    // agency table : 기관 명
    private String agency_name;

    // 기관 이미지
    private String agency_img;

    // 모금 상태 ( fundraising/fundraisingEnds )
    private String state;

    private Integer pickYn;

    private Integer pickActionYn;

    private Integer reviewStorySeq;

    // 지원 대상 명 (수혜대상)
    private String benefitTargetContent;

    // 지원 대상 코드 (수혜대상)
    private String benefitTargetCode;

    // 지원 대상(=배분모금함) seq
    private Integer benefitTargetSeq;

    // 프로모션 글
    private String promotion;

    // 태그설정 (#로 구분)
    private String tags;

    // 지원대상 및 인원 텍스트 ex) 쪽방촌, 20가정
    private String targetText;

    // 응원 참여 기부금
    private Integer pickActionAmount;

    // 공유 참여 기부금
    private Integer shareActionAmount;

    // 댓글 참여 기부금
    private Integer commentActionAmount;

    // 응원 참여 기부 제한 횟수
    private Integer pickActionLimit;

    // 공유 참여 기부 제한 횟수
    private Integer shareActionLimit;

    // 댓글 참여 기부 제한 횟수
    private Integer commentActionLimit;

    // 응원 참여 기부 제한 여부 (0: 무제한, 1: 제한)
    private Integer pickActionLimitYn;

    // 공유 참여 기부 제한 여부 (0: 무제한, 1: 제한)
    private Integer shareActionLimitYn;

    // 댓글 참여 기부 제한 여부 (0: 무제한, 1: 제한)
    private Integer commentActionLimitYn;

    // 사연 유형 (1: 기본 유형, 2: 터치 캠페인 유형)
    private Integer categoryCode;

    // 목표 모금액 노출 여부 (1: 노출, 0: 비노출)
    private Integer targetFundAmountDispYn;

    // 터치 전 이미지키
    private Integer touchBeforeImageSeq;

    // 터치 전 이미지경로
    private String touchBeforeImagePath;

    // 터치 전 이미지파일명
    private String touchBeforeImageFile;

    // 터치 후 이미지키
    private Integer touchAfterImageSeq;

    // 터치 후 이미지경로
    private String touchAfterImagePath;

    // 터치 후 이미지파일명
    private String touchAfterImageFile;

    // 담당자명
    private String managerName;

    // 담당자 이메일
    private String managerEmail;

    // 사업 기간 텍스트
    private String businessStartDtText;

    // 정기-모금기간 (30: 30일, 60: 60일, 90: 90일, 0: 목표금액달성시까지)
    private Integer fundRoutinePeriod;

    // 직접후원 액수가 가장 큰 유저
    private Integer largestAmountUser;

    // 직접후원 액수가 가장 큰 유저 기부자명
    private String largestAmountUserName;

    // 직접후원 액수가 가장 큰 금액
    private Double largestAmount;

    // 직접후원 액수가 가장 큰 유저 기부자명 구분  (닉네임/태그/익명 구분)
    private Integer largestAmountUserNameType;

    // 사연 부모키 (정기 사연일 경우, 해당) 부모키 없을때, 0
    private Integer parentSeq;

    // 사연 그룹 아이디 (정기 사연일 경우 그룹으로 묶여야함, 일시일 경우, null 값일 수 있음)
    private String groupId;

    // 사연 회차
    private Integer storyRound;

    // 정기사연 모금중단 여부 (0: 진행중 1: 중단) > 1 일때 배치 제외
    private Integer regularStopYn;

    // 모금함에서 배분한 금액
    private Integer fundMapFundAmount;

    // 모금함 배분 횟수
    private Integer fundMapFundCount;

    // 강제종료 전 모금시작일
    private Date originFundStartDt;

    // 강제종료 전 모금종료일
    private Date originFundEndDt;

    // 다음 회차에 적용될 주기
    private Integer fundRoutinePeriodNext;

    // 실제 모금 종료된 날짜 (웹 사용 용도)
    private Date realEndDt;


    // v1의 companyFundAmount. PointInfo에는 데이터가 없으며, donate() 실행 시 PointInfo 데이터와 해당 컬럼을 합쳐서 최종 계산. (v1에서의 companyFundAmount는 상세 내역이 없으므로 v2로 이관 시 별도 컬럼으로 기록하기 위해 생성)
    private Integer origCompanyFundAmount;

    // v1의 companyFundCount. PointInfo에는 데이터가 없으며, donate() 실행 시 PointInfo 데이터와 해당 컬럼을 합쳐서 최종 계산. (v1에서의 companyFundCount는 상세 내역이 없으므로 v2로 이관 시 별도 컬럼으로 기록하기 위해 생성)
    private Integer origCompanyFundCount;

    // (B2B) 참여 대상. (EMPLOYEE:임직원만, WITH_USERS:사용자도함께, NULL:나눔파트너 X)
    private StoryDonationTarget donationTarget;

    // (B2B) 나눔파트너 한도금액. NULL일 경우 나눔파트너 참여하지 않음
    private Integer partnerAmountLimit;

    // 나눔파트너 seq
    private Integer storyPartnerSeq;
    // 나눔파트너 이름
    private String storyPartnerName;
    @Builder.Default
    // 나눔파트너 임직원 기부금
    private int employeeFundAmount = 0;

    // 현재 mainSectionYn=1 (메인노출) 개수
    private long currentMainCnt;

    // 노출 시 말머리 텍스트
    private String preface;

    // endDate 까지 남아있는 dday
    private String dday;

    // 조회 0건에 따른 랜덤 조회 여부
    @Builder.Default
    private boolean isRandomRecommend = false;

    // 모금함 종료 여부
    @Builder.Default
    private boolean isend = false;

    // 정기사연 : 가장 최근 회차의 seq
    @Builder.Default
    private int lastRoundSeq = 0;


    public void setStoryEnd(){
        this.isend = false;

        // 마감된 경우
        if(!ObjectUtils.isEmpty(this.state) && this.state.equals(Const.STORY_STATE_END)){
            this.isend = true;
            return;
        }

        // 일시사연 : 마감날짜 상관없이 rate 체크. rate가 안됐으면 날짜여부에 따라 날짜 체크
        if(this.fundTypeCode == null || this.fundTypeCode.intValue() != 2){
            if (this.fundRate >= 100) {
                this.isend = true;
                return;

            } else if (this.fundEndDt != null) {
                LocalDate endDate = this.fundEndDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long diff = LocalDate.now().until( endDate, ChronoUnit.DAYS );
                if(diff < 0) {
                    this.isend = true;
                    return;
                }
            }

        } else {
            // 정기사연 : 마감날짜가 있으면 날짜 체크, 없으면 rate 체크
            if(this.fundEndDt != null) {
                LocalDate endDate = this.fundEndDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long diff = LocalDate.now().until(endDate, ChronoUnit.DAYS);
                if (diff < 0) {
                    this.isend = true;
                    return;
                }

            } else if(this.fundRate >= 100) {
                this.isend = true;
                return;
            }
        }
    }
}
