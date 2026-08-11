package soo.demo.constant;

public class Const {
    // test인 경우 true, live는 false
    static public final boolean TEST_DEBUG_USER = false;
    static public final Integer TEST_USER = 111;

    static public final String PUBVER = "201912161";
    static public final Integer CODE_OK = 1;
    static public final Double LAT = 37.5666805;
    static public final Double LNG = 126.9784147;
    static public final Integer USER_SYSTEM = 100;
    static public final Integer USER_NULL = -1;

    static public final String ymdhmssz = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static public final String ymdhms = "yyyy-MM-dd HH:mm:ss";
    static public final String ymd = "yyyy-MM-dd";

    static public final String HEADER_DEVICE = "x-dolgo-device";
    static public final String HEADER_DEVICE_SERIAL = "x-dolgo-device-serial";
    static public final String HEADER_TOKEN = "x-dolgo-token";
    static public final String HEADER_FCM_TOKEN = "x-dolgo-fcm-token";
    static public final String HEADER_DEVICE_VALUE = "5518509C175E0361";
    static public final String HEADER_TOKEN_NULL = "token-null";

    //221.155.157.11 수연님 집
    //125.142.163.70 수연님 2
    static public final String ADMIN_IP = "127.0.0.1/112.216.230.34/10.140.151.71/210.206.68.66/221.155.157.11/125.142.163.70";
    static public final String FB_APP_ID = "315453039332338";

    static public final Integer PAGE_SIZE = 10;
    static public final Integer SEQ_ZERO = 0;
    static public final Integer LIMIT_FULL = 999;
    static public final Integer ROOT_ZERO = 0;
    static public final Integer USE_Y = 1;
    static public final Integer USE_N = 0;
    static public final Integer USE_NONE = -1;

    static public final String DEFAULT_IMAGE_PATH = "https://dolgo-api.dolgo.org/dolgo/static/pages/images/default/";
    static public final String DEFAULT_IMAGE_FILE = "profile_l@3x.png";
    static public final String TAG_IMAGE_PATH = "https://dolgo-api.dolgo.org/dolgo/static/pages/images/default/";
    static public final String TAG_IMAGE_FILE = "sharp.png";

    static public final String EMPTY_USER_NAME = "회원";

    static public final Integer VOLUNTEER_ACTIVE_CODE = 101;
    static public final Integer VOLUNTEER_CODE_AREA_TEXT_CODE = 21010;
    static public final Integer SCHEDULE_ACTIVE_CODE = 102;

    // project type ====
    static public final Integer LOG_SMS_TYPE_CODE = 1;
    static public final Integer LOG_PAYMENT_WEBHOOK_TYPE_CODE = 2;
    static public final Integer LOG_ERROR_TYPE_CODE = 3;

    static public final Integer LOG_EMPTY_CONTENT_TYPE_CODE = -1;
    static public final Integer LOG_AUTH_CONTENT_TYPE_CODE = 1;
    static public final Integer LOG_PAYMENT_ORDER_CONTENT_TYPE_CODE = 2;
    static public final Integer LOG_REGULAR_PAY_ERROR_MESSAGE_TYPE_CODE = 3;
    static public final Integer LOG_REGULAR_PAY_ERROR_MESSAGE_FAILED_TYPE_CODE = 4;
    static public final Integer LOG_TOSS_PAYMENTS_JSON_CONTENT_TYPE_CODE = 5;

    static public final Integer NOTICE_TYPE_CODE = 1;
    static public final Integer HELP_TYPE_CODE = 2;

    static public final Integer LOGIN_SYSTEM_TYPE_CODE = 0;
    static public final Integer LOGIN_EMAIL_TYPE_CODE = 1;
    static public final Integer LOGIN_FACEBOOK_TYPE_CODE = 2;
    static public final Integer LOGIN_GOOGLE_TYPE_CODE = 3;
    static public final Integer LOGIN_KAKAO_TYPE_CODE = 4;
    static public final Integer LOGIN_APPLE_TYPE_CODE = 5;

    // USER-TYPE-CODE 회원 구분 (0: 돌고시스템, 1: 회원, 2: 관리자, 3: 파트너)
    static public final Integer USER_SYSTEM_TYPE_CODE = 0;
    static public final Integer USER_TYPE_CODE = 1;
    static public final Integer USER_ADMIN_TYPE_CODE = 2;
    static public final Integer USER_OWNER_TYPE_CODE = 3;
    static public final Integer USER_PARTNER_MANAGER_TYPE_CODE = 4;

    static public final Integer AUTH_EMAIL_TYPE_CODE = 1;
    static public final Integer AUTH_EMAIL_RESET_TYPE_CODE = 2;
    static public final Integer AUTH_SMS_TYPE_CODE = 11;
    static public final Integer AUTH_SMS_RESET_TYPE_CODE = 12;
    static public final Integer AUTH_PAYMENT_TYPE_CODE = 21;

    // ImageMap contentTypeCode (contentSeq 는 각 타입의 seq)
    static public final Integer IMAGE_MAP_PROFILE_TYPE = 1;
    static public final Integer IMAGE_MAP_STORY_TYPE = 2; // 사연 배경  Story seq
    static public final Integer IMAGE_MAP_BANNER_TYPE = 3;
    static public final Integer IMAGE_MAP_CORP_IMG_TYPE = 4; // 기부즘영수증(기업) 사업자등록증 이미지
    static public final Integer IMAGE_MAP_STORY_CONTENT_TYPE = 5; // 사연 본문 첨부 이미지 리스트 StoryContent seq
    static public final Integer IMAGE_MAP_STORY_NEWS_TYPE = 6; // 사연 소식 첨부 이미지 리스트 StoryNews seq
    static public final Integer IMAGE_MAP_FUND_MAP_TYPE = 7; // 모금함
    static public final Integer IMAGE_MAP_STORY_TOUCH_BEFORE_TYPE = 8; // 사연 터치 이미지 (터치 전)
    static public final Integer IMAGE_MAP_STORY_TOUCH_AFTER_TYPE = 9; // 사연 터치 이미지 (터치 후)
    static public final Integer IMAGE_MAP_STORY_NEWS_CONTENT_TYPE = 10; // 사연 소식 본문 이미지 리스트 StoryNews seq
    static public final Integer IMAGE_MAP_AGENCY_TYPE = 11; // 단체 이미지
    static public final Integer IMAGE_MAP_PARTNER_TYPE = 12; // 파트너 이미지
    static public final Integer IMAGE_MAP_AGENCY_DOC_TYPE = 13; // 단체 필수서류 이미지
    static public final Integer IMAGE_MAP_STORY_NEWS_THUMBNAIL = 14; // 사연 소식 썸네일 이미지
    static public final Integer IMAGE_MAP_PARTNER_DEPOSIT_IMAGE_TYPE = 15; // 파트너 예치금 입금 증빙자료
    static public final Integer IMAGE_MAP_PARTNER_LICENSE_TYPE = 16; // 파트너 사업자등록증 이미지

    static public final Integer PICK_CONTENT_STORY_TYPE = 1;
    static public final Integer PICK_CONTENT_AGENCY_TYPE = 2;
    static public final Integer PICK_CONTENT_VOLUNTEER_TYPE = 3;

    static public final Integer CATEGORY_STORY_TYPE = 1;
    static public final Integer CATEGORY_AGENCY_TYPE = 2;

    static public final Integer STORY_TYPE_CODE_STORY = 1;
    static public final Integer STORY_TYPE_CODE_PRODUCT = 2;
    static public final Integer STORY_TYPE_CODE_ROUTINE = 3;
    static public final Integer STORY_TYPE_CODE_REVIEW = 4;

    static public final Integer LIMIT_STORY_COMMENT = 5;
    static public final Integer STORY_COMMENT_STORY_TYPE = 1;
    static public final Integer STORY_COMMENT_AGENCY_TYPE = 2;

    static public final Integer STORY_ACTION_PRICE = 100;

    // comment type code > (1:닉네임 or 기업명, 2:태그, 3:익명)
    static public final Integer STORY_COMMENT_NICKNAME_TYPE = 1;
    static public final Integer STORY_COMMENT_TAG_TYPE = 2;
    static public final Integer STORY_COMMENT_ANONYMOUS_TYPE = 3;

    // 댓글 타입 (1:직접기부 응원메시지, 2:댓글작성, 3:계좌로 직접이체(23.02.15 추가))
    static public final Integer STORY_COMMENT_TYPE_ALL = -1;
    static public final Integer STORY_COMMENT_TYPE_PAYMENT = 1;
    static public final Integer STORY_COMMENT_TYPE_COMMENT = 2;
    static public final Integer STORY_COMMENT_TYPE_DIRECT_TRANS = 3;

    static public final Integer STORY_PICK_TYPE_ACTION = 1;

    static public final Integer CONTENT_REASON_PAYMENT_ROUTINE_CANCEL_TYPE_CODE = 1;

    // PaymentOrder > contentTypeCode (1: 사연, 2: 단체, 3: 공동 모금함, 4: 모금함(지원대상별), 5: 돌고후원, 6: 예치금 추가, 7: 보증금 추가, 8: 더블업, 9: 투게더, 10: 보증금 정산, 11: 보증금 차감)
    static public final Integer PAYMENT_ORDER_CONTENT_STORY_TYPE = 1;
    static public final Integer PAYMENT_ORDER_CONTENT_AGENCY_TYPE = 2;
    static public final Integer PAYMENT_ORDER_CONTENT_FUND_TYPE = 3;
    static public final Integer PAYMENT_ORDER_CONTENT_FUND_MAP_TYPE = 4;
    static public final Integer PAYMENT_ORDER_CONTENT_DONATION_TYPE = 5;
    static public final Integer PAYMENT_ORDER_CONTENT_POINT_TYPE = 6;

    static public final Integer PAYMENT_ORDER_CONTENT_SETTLEMENT_TYPE = 7;
    static public final Integer PAYMENT_ORDER_CONTENT_DOUBLEUP_TYPE = 8;
    static public final Integer PAYMENT_ORDER_CONTENT_TOGETHER_TYPE = 9;
    static public final Integer PAYMENT_ORDER_CONTENT_SETTLEMENT_PAY_TYPE = 10;
    static public final Integer PAYMENT_ORDER_CONTENT_SETTLEMENT_DEDUCT_TYPE = 11;

    // 돌고 공동모금함 title
    static public final String PAYMENT_ORDER_CONTENT_FUND_TITLE = "돌고 공동모금함";

    // PaymentOrder > typeCode (1: 일시, 2: 정기)
    static public final Integer PAYMENT_TYPE_NORMAL = 1;
    static public final Integer PAYMENT_TYPE_ROUTINE = 2;

    static public final Integer SOCIAL_INFO_TYPE_ALL = -1;
    static public final Integer SOCIAL_INFO_TYPE_SIMPLE = 1;
    static public final Integer SOCIAL_INFO_TYPE_SIMPLE_CORP = 4;

    //	static public final String ORDER_STATUS_PAY_DONE = "2,3";
    static public final String ORDER_STATUS_PAY_DONE = "2";
    // 1:주문완료, 2:결제완료, 3:정기결제주문완료 -1:결제실패, -2:결제완료취소, -3:정기결제취소
    // payment.status, payment_order.order_status 에서 사용
    static public final Integer ORDER_STATUS_ORDER = 1;
    static public final Integer ORDER_STATUS_PAY = 2;
    static public final Integer ORDER_STATUS_PAY_ROUTINE = 3;
    static public final Integer ORDER_STATUS_PAY_FAIL = -1;
    static public final Integer ORDER_STATUS_PAY_CANCEL = -2;
    static public final Integer ORDER_STATUS_PAY_ROUTINE_CANCEL = -3;

    // 자주묻는질문 카테고리 코드 1: 서비스이용 2: 결제/기부금영수증
    static public final Integer NOTICE_CATEGORY_CODE = 42011;
    static public final Integer NOTICE_CATEGORY_SERVICE = 1;
    static public final Integer NOTICE_CATEGORY_PAYMENT = 2;

    // 배너 타입 코드 1: 사연(메인) 2: 봉사 3: 설정 4: 단체
    static public final Integer BANNER_TYPE_CODE = 1080;
    static public final String BANNER_TYPE_CODE_MAIN = "1";
    static public final String BANNER_TYPE_CODE_VOLUNTEER = "2";
    static public final String BANNER_TYPE_CODE_SETTING = "3";
    static public final String BANNER_TYPE_CODE_AGENCY = "4";

    // TRANSACTION-USER-TYPE-CODE
    // Transaction > userTypeCode,senderTypeCode,receiverTypeCode
    // (0: 돌고시스템, 1: 회원, 2: 관리자, 3: 파트너)
    // (100: 사연, 200: 단체, 300: 공동 모금함, 400: 모금함(지원대상별), 500: 돌고후원)
    // sender
    static public final int TRANSACTION_USER_SYSTEM_TYPE_CODE = 0;
    static public final int TRANSACTION_USER_TYPE_CODE = 1;
    static public final int TRANSACTION_USER_ADMIN_TYPE_CODE = 2;
    static public final int TRANSACTION_USER_OWNER_TYPE_CODE = 3;
    // receiver
    static public final int TRANSACTION_USER_STORY_TYPE_CODE = 100;
    static public final int TRANSACTION_USER_AGENCY_TYPE_CODE = 200;
    static public final int TRANSACTION_USER_FUND_TYPE_CODE = 300;
    static public final int TRANSACTION_USER_FUND_MAP_TYPE_CODE = 400;
    static public final int TRANSACTION_USER_DONATION_TYPE_CODE = 500;

    // TRANSACTION-TYPE-CODE
    // Transaction > typeCode (1: 충전, 2: 차감(배분), 3: 반환(오류시))
    static public final Integer TRANSACTION_ADD_TYPE_CODE = 1;
    static public final Integer TRANSACTION_SUB_TYPE_CODE = 2;
    static public final Integer TRANSACTION_RETURN_TYPE_CODE = 3;

    // TRANSACTION-TYPE-NAME
    // Transaction > typeName (1: 충전, 2: 차감(배분), 3: 반환(오류시))
    static public final String TRANSACTION_ADD_TYPE_NAME = "충전";
    static public final String TRANSACTION_SUB_TYPE_NAME = "차감";
    static public final String TRANSACTION_RETURN_TYPE_NAME = "반환";

    // POINT_INFO_TYPE_CODE
    // PointInfo > memo에 등록될 타입 코드
    static public final String POINT_INFO_TYPE_CODE_PAY_DEPOSIT = "결제_입금";
    static public final String POINT_INFO_TYPE_CODE_PAY_CARD = "결제_카드";
    static public final String POINT_INFO_TYPE_CODE_PAY_TELEPHONE = "결제_휴대폰";
    static public final String POINT_INFO_TYPE_CODE_PAY_KAKAO = "결제_카카오";
    static public final String POINT_INFO_TYPE_CODE_PAY_POINT = "결제_포인트";
    static public final String POINT_INFO_TYPE_CODE_PAY_PAYCO = "결제_페이코";
    static public final String POINT_INFO_TYPE_CODE_PAY_TOSS = "결제_토스";
    static public final String POINT_INFO_TYPE_CODE_PAY_OTHER = "결제_기타";

    static public final String POINT_INFO_TYPE_CODE_DONATION_SEND = "기부G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_RECEIVE = "받음G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_LIKE = "좋아요G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_COMMENT = "댓글G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_CHEER = "응원G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_SHARE = "공유G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_TOUCH = "터치캠페인G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_PARTNER = "파트너G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_FUNDMAP = "모금함배분G";
    static public final String POINT_INFO_TYPE_CODE_DONATION_FUND = "공동모금함배분G";

    static public final String POINT_INFO_TYPE_CODE_CHARGE = "충전";
    static public final String POINT_INFO_TYPE_CODE_CHARGE_FUND = "충전_공동모금함";
    static public final String POINT_INFO_TYPE_CODE_CHARGE_FUND_MAP = "충전_모금함";
    static public final String POINT_INFO_TYPE_CODE_CHARGE_DOLGO = "충전_돌고후원";
    static public final String POINT_INFO_TYPE_CODE_RECALL = "회수";
    static public final String POINT_INFO_TYPE_CODE_RETURN = "반납";
    static public final String POINT_INFO_TYPE_CODE_WITHDRAWAL = "출금";
    static public final String POINT_INFO_TYPE_CODE_REFUND = "환불";

    // PaymentOrder > 결제방식 (card:신용카드, kakaopay:카카오페이, payco:페이코, tosspay:토스, trans:계좌이체, direct_trans:계좌로 직접이체(23.02.15 추가))
    static public final String PAYMENT_ORDER_PAY_CARD_TYPE = "card";
    static public final String PAYMENT_ORDER_PAY_KAKAOPAY_TYPE = "kakaopay";
    static public final String PAYMENT_ORDER_PAY_PAYCO_TYPE = "payco";
    static public final String PAYMENT_ORDER_PAY_TOSSPAY_TYPE = "tosspay";
    static public final String PAYMENT_ORDER_PAY_TRANS_TYPE = "trans";
    static public final String PAYMENT_ORDER_PAY_DIRECT_TRANS_TYPE = "direct_trans";

    // FundMap > 지원 대상 코드 (전체, 아동/청소년, 어르신, 여성, 장애인, 동물, 환경, 기타)
    static public final Integer FUNDMAP_TARGET_CODE_SEQ = 43000;
    static public final String FUNDMAP_TARGET_CODE_ALL = "all";
    static public final String FUNDMAP_TARGET_CODE_CHILDREN = "child";
    static public final String FUNDMAP_TARGET_CODE_OLD = "old";
    static public final String FUNDMAP_TARGET_CODE_WOMEN = "women";
    static public final String FUNDMAP_TARGET_CODE_DISABLED = "disabled";
    static public final String FUNDMAP_TARGET_CODE_ANIMALS = "animals";
    static public final String FUNDMAP_TARGET_CODE_ENVIRONMENT = "environment";
    static public final String FUNDMAP_TARGET_CODE_OTHER = "other";
    static public final String FUNDMAP_TARGET_CODE_GLOBAL = "global";

    // Story > 지원 대상 코드 (종합, 아동/청소년, 어르신, 여성, 장애인, 동물, 환경, 지구촌, 기타)
    static public final Integer STORY_TARGET_CODE_SEQ = 44000;
    static public final String STORY_TARGET_CODE_ALL = "all";
    static public final String STORY_TARGET_CODE_CHILDREN = "child";
    static public final String STORY_TARGET_CODE_OLD = "old";
    static public final String STORY_TARGET_CODE_WOMEN = "women";
    static public final String STORY_TARGET_CODE_DISABLED = "disabled";
    static public final String STORY_TARGET_CODE_ANIMALS = "animals";
    static public final String STORY_TARGET_CODE_ENVIRONMENT = "environment";
    static public final String STORY_TARGET_CODE_GLOBAL = "global";
    static public final String STORY_TARGET_CODE_OTHER = "other";

    // Story > typeCode (1: 일반, 2: 펀딩) >> v2는 무조건 2 펀딩으로 사용
    static public final Integer STORY_TYPE_CODE_SEQ = 2030;
    static public final Integer STORY_TYPE_CODE_NORMAL = 1;
    static public final Integer STORY_TYPE_CODE_FUND = 2;

    // Story > fundTypeCode (1: 일시, 2: 정기)
    static public final Integer STORY_FUND_TYPE_CODE_SEQ = 2040;
    static public final Integer STORY_FUND_TYPE_NORMAL = 1;
    static public final Integer STORY_FUND_TYPE_ROUTINE = 2;

    // Story > storyCategoryCode (1: 기본 유형, 2: 터치 캠페인 유형)
    static public final Integer STORY_CATEGORY_CODE_SEQ = 45000;
    static public final Integer STORY_CATEGORY_CODE_NORMAL = 1;
    static public final Integer STORY_CATEGORY_CODE_TOUCH = 2;

    // 정기기부 status (saved:임시저장, paycheck:결제 웹훅에서 pay 확인 완료(실질적인 정기기부 정상등록), none:사용안함, cancel:결제취소, failed:실패, stop:정기결제 중단)
    static public final String REGULAR_PAY_STATUS_SAVED = "saved";
    static public final String REGULAR_PAY_STATUS_PAYCHECK = "paycheck";
    static public final String REGULAR_PAY_STATUS_NONE = "none";
    static public final String REGULAR_PAY_STATUS_CANCEL = "cancel";
    static public final String REGULAR_PAY_STATUS_FAILED = "failed";
    static public final String REGULAR_PAY_STATUS_STOP = "stop";

    // 사연 상태 ( fundwaiting/fundraising/fundraisingEnds )
    static public final String STORY_STATE_WAITING = "fundwaiting";
    static public final String STORY_STATE_ALL = "all";
    static public final String STORY_STATE_ING = "fundraising";
    static public final String STORY_STATE_END = "fundraisingEnds";
    static public final String STORY_STATE_EXCEPT_END = "exceptFundraisingEnds";

    // 결제 웹훅 상태 정상
    static public final String WEBHOOK_RESULT_SUCCESS = "true";

    // 결제 수단 코드
    static public final String PAY_METHOD_POINT_CODE = "point";

    // 결제 수단
    static public final String PAY_METHOD_CARD = "신용카드";
    static public final String PAY_METHOD_KAKAO = "카카오페이";
    static public final String PAY_METHOD_TRANS = "계좌이체";
    static public final String PAY_METHOD_PAYCO = "페이코";
    static public final String PAY_METHOD_TOSS = "토스";
    static public final String PAY_METHOD_DIRECT_TRANS = "계좌로 직접이체";
    static public final String PAY_METHOD_POINT = "(포인트)";

    // 결제 상태 표기 문구
    static public final String ORDER_STATUS_NAME_ORDER = "주문완료";
    static public final String ORDER_STATUS_NAME_PAY = "결제완료";
    static public final String ORDER_STATUS_NAME_PAY_ROUTINE = "정기결제주문완료";
    static public final String ORDER_STATUS_NAME_PAY_FAIL = "결제실패";
    static public final String ORDER_STATUS_NAME_PAY_CANCEL = "결제완료취소";
    static public final String ORDER_STATUS_NAME_PAY_ROUTINE_CANCEL = "정기결제취소";

    // 결제 구분 표기 문구
    static public final String PAYMENT_TYPE_NAME_NORMAL = "일시";
    static public final String PAYMENT_TYPE_NAME_ROUTINE = "정기";


    // PMS
    public static final String PMS_PARTNER_MANAGER = "파트너 관리자";

    // 단체(agency)
    static public final Integer AGENCY_ORDER_TYPE_RANDOM = 18;
}
