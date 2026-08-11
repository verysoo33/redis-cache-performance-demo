package soo.demo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import soo.demo.constant.Const;
import soo.demo.domain.Story;
import soo.demo.domain.StoryDonationTarget;
import soo.demo.dto.RestPage;
import soo.demo.dto.story.StoryDto;
import soo.demo.util.DateUtil;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static soo.demo.domain.QAgency.agency;
import static soo.demo.domain.QStory.story;

@Repository
@Transactional
public class StoryRepoCommonImpl implements StoryRepoCommon {
    private final JPAQueryFactory queryFactory;

    private final ModelMapper modelMapper;

    private EntityManager em;

    public StoryRepoCommonImpl(EntityManager em, ModelMapper modelMapper) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestPage<StoryDto> search(PageRequest pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        JPAQuery<Integer> countQuery = queryFactory
                .select(story.seq)
                .from(story)
                .where(booleanBuilder)
                .leftJoin(agency).on(agency.seq.eq(story.agencySeq));

        // 조회된 story가 없을 경우, 랜덤으로 2개 조회
        boolean isRandomRecommend = false;

        List<Tuple> results = queryFactory
                .select(story.seq, story.insertUser, story.updateUser, story.useYn, story.orderValue,
                        story.typeCode, story.fundTypeCode, story.agencySeq, story.title, story.content,
                        story.businessStartDt, story.businessEndDt, story.fundStartDt, story.fundEndDt,
                        story.targetFundAmount, story.currentFundAmount, story.imageSeq, story.imagePath,
                        story.imageFile, story.linkUri, story.agencyTypeOrder, story.pickCount,
                        story.fundRate, story.benefitTargetContent, story.personFundAmount,
                        story.personFundCount, story.companyFundAmount, story.companyFundCount,
                        story.actionFundAmount, story.actionFundCount, story.currentProductAmount,
                        story.personProductAmount, story.companyProductAmount, story.actionProductAmount,
                        story.storyTypeCode, story.commentCount, story.productActionValue,
                        story.productActionYn, story.relationStorySeq, story.receiptPath,
                        story.receiptFile, story.mainSectionYn, agency.name, story.fundMapFundAmount, story.fundMapFundCount,
                        story.state, story.regularStopYn, story.originFundStartDt, story.originFundEndDt, story.targetFundAmountDispYn,
                        story.donationTarget, story.fundEndDt, story.tags)
                .from(story)
                .where(booleanBuilder)
                .leftJoin(agency).on(agency.seq.eq(story.agencySeq))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<StoryDto> storyDtoList = results.stream().map(it -> {
            // 말머리 세팅
            String preface = "";
            if (it.get(story.donationTarget) == null) {
                preface = (it.get(story.fundTypeCode) != null && it.get(story.fundTypeCode) == Const.STORY_FUND_TYPE_ROUTINE) ? "정기모금" : "일시모금";
            } else if (it.get(story.donationTarget).equals(StoryDonationTarget.EMPLOYEE)) {
                preface = "나눔파트너";
            } else if (it.get(story.donationTarget).equals(StoryDonationTarget.WITH_USERS)) {
                preface = "나눔파트너 투게더";
            }

            // dday 계산
            String dday = DateUtil.getDday(LocalDateTime.now(), it.get(story.fundEndDt));

            // tags null 처리
            String tagString = it.get(story.tags) == null ? "" : it.get(story.tags);

            return StoryDto.builder()
                    .seq(it.get(story.seq))
                    .insertUser(it.get(story.insertUser))
                    .updateUser(it.get(story.updateUser))
                    .useYn(it.get(story.useYn))
                    .orderValue(it.get(story.orderValue))
                    .typeCode(it.get(story.typeCode))
                    .fundTypeCode(it.get(story.fundTypeCode))
                    .agencySeq(it.get(story.agencySeq))
                    .title(it.get(story.title))
                    .content(it.get(story.content))
                    .businessStartDt(it.get(story.businessStartDt))
                    .businessEndDt(it.get(story.businessEndDt))
                    .fundStartDt(it.get(story.fundStartDt))
                    .fundEndDt(it.get(story.fundEndDt))
                    .targetFundAmount(it.get(story.targetFundAmount))
                    .currentFundAmount(it.get(story.currentFundAmount))
                    .imageSeq(it.get(story.imageSeq))
                    .imagePath(it.get(story.imagePath))
                    .imageFile(it.get(story.imageFile))
                    .linkUri(it.get(story.linkUri))
                    .agencyTypeOrder(it.get(story.agencyTypeOrder))
                    .pickCount(it.get(story.pickCount))
                    .fundRate(it.get(story.fundRate))
                    .benefitTargetContent(it.get(story.benefitTargetContent))
                    .personFundAmount(it.get(story.personFundAmount))
                    .personFundCount(it.get(story.personFundCount))
                    .companyFundAmount(it.get(story.companyFundAmount))
                    .companyFundCount(it.get(story.companyFundCount))
                    .actionFundAmount(it.get(story.actionFundAmount))
                    .actionFundCount(it.get(story.actionFundCount))
                    .currentProductAmount(it.get(story.currentProductAmount))
                    .personProductAmount(it.get(story.personProductAmount))
                    .companyProductAmount(it.get(story.companyProductAmount))
                    .actionProductAmount(it.get(story.actionProductAmount))
                    .storyTypeCode(it.get(story.storyTypeCode))
                    .commentCount(it.get(story.commentCount))
                    .productActionValue(it.get(story.productActionValue))
                    .productActionYn(it.get(story.productActionYn))
                    .relationStorySeq(it.get(story.relationStorySeq))
                    .receiptPath(it.get(story.receiptPath))
                    .receiptFile(it.get(story.receiptFile))
                    // .mainSectionYn(it.get(story.mainSectionYn))
                    .agency_name(it.get(agency.name))
                    .fundMapFundAmount(it.get(story.fundMapFundAmount))
                    .fundMapFundCount(it.get(story.fundMapFundCount))
                    .state(it.get(story.state))
                    .regularStopYn(it.get(story.regularStopYn))
                    .originFundStartDt(it.get(story.originFundStartDt))
                    .originFundEndDt(it.get(story.originFundEndDt))
                    .targetFundAmountDispYn(it.get(story.targetFundAmountDispYn))
                    .tags(tagString)
                    .preface(preface)
                    .dday(dday)
                    .isRandomRecommend(isRandomRecommend)
                    .build();
        }).collect(Collectors.toList());

        return new RestPage<>(PageableExecutionUtils.getPage(storyDtoList, pageable, countQuery::fetchCount));
    }

    @Override
    public Page<StoryDto> findListBySort(PageRequest pageable, Integer orderType, Integer typeCode, Integer fundTypeCode,
                                         Integer categorySeq, Integer agencySeq, Integer storyTypeCode, String keyword,
                                         Integer mainSectionYn, Integer useYn, String benefitTargetCode, Integer[] storyTypeCodeArr,
                                         String state, Integer cmsYn, String groupId) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (cmsYn != null && cmsYn.equals(1)) {
            // groupId 별로 가장 최신 seq 조회 (=정기사연의 현재 진행중인 사연)
            booleanBuilder.and(
                    story.seq.in(
                            queryFactory
                                    .select(story.seq.max())
                                    .from(story)
                                    .groupBy(story.groupId)
                    )
            );
        }
        if (!StringUtils.isEmpty(groupId)) {
            booleanBuilder.and(
                    story.groupId.eq(groupId)
            );
        }

        // group_id 가 null 인 경우 조회대상에서 제거
        booleanBuilder.and(story.groupId.isNotNull());

        QueryResults<Tuple> results = queryFactory
                .select(
                        story.seq, story.insertDate, story.insertUser, story.updateUser, story.updateDate
                        , story.useYn, story.orderValue, story.typeCode, story.fundTypeCode, story.agencySeq
                        , story.title, story.content, story.businessStartDt
                        , story.businessEndDt, story.fundStartDt, story.fundEndDt, story.targetFundAmount, story.currentFundAmount, story.currentFundCount
                        , story.imageSeq, story.imagePath, story.imageFile, story.linkUri, story.mainSectionYn
                        , story.agencyTypeOrder, story.pickCount, story.fundRate, story.benefitTargetContent
                        , story.personFundAmount, story.personFundCount, story.companyFundAmount, story.companyFundCount
                        , story.actionFundAmount, story.actionFundCount, story.currentProductAmount, story.personProductAmount
                        , story.companyProductAmount, story.actionProductAmount, story.storyTypeCode, story.commentCount
                        , story.productActionValue, story.productActionYn, story.relationStorySeq, story.parentSeq, story.groupId, story.storyRound
                        , story.fundMapFundAmount, story.fundMapFundCount, story.state, story.regularStopYn, story.originFundStartDt, story.originFundEndDt
                )
                .from(story)
                .leftJoin(agency).on(agency.seq.eq(story.agencySeq))
                .where(
                        eqFieldValue("useYn", useYn)
                        , (eqFieldValue("agencySeq", agencySeq))
                        , (eqFieldValue("typeCode", typeCode))
                        , (eqFieldValue("fundTypeCode", fundTypeCode))
                        , (eqFieldValue("storyTypeCode", storyTypeCodeArr))
                        , (eqFieldValue("categorySeq", categorySeq))
                        , (containsKeyword(keyword))
                        //,( eqFieldValue("fundEndDt", orderType) )
                        , checkFundEndDate(orderType)
                        , (eqFieldValue("mainSectionYn", mainSectionYn))
                        , (eqFieldValue("benefitTargetCode", benefitTargetCode))
                        , stateSearch(state)
                        , booleanBuilder
                )
                .orderBy(sort(orderType, state).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        JPAQuery<Integer> countQuery = queryFactory
                .select(story.seq)
                .from(story)
                .leftJoin(agency).on(agency.seq.eq(story.agencySeq))
                .where(
                        eqFieldValue("useYn", useYn)
                        , (eqFieldValue("agencySeq", agencySeq))
                        , (eqFieldValue("typeCode", typeCode))
                        , (eqFieldValue("fundTypeCode", fundTypeCode))
                        , (eqFieldValue("storyTypeCode", storyTypeCodeArr))
                        , (eqFieldValue("categorySeq", categorySeq))
                        , (containsKeyword(keyword))
                        //,( eqFieldValue("fundEndDt", orderType) )
                        , checkFundEndDate(orderType)
                        , (eqFieldValue("mainSectionYn", mainSectionYn))
                        , (eqFieldValue("benefitTargetCode", benefitTargetCode))
                        , stateSearch(state)
                        , booleanBuilder
                );

        // 현재 메인노출인 사연 개수
        long currentMainCnt = queryFactory
                .select(story.seq)
                .from(story)
                .where(
                        story.mainSectionYn.isNotNull(),
                        story.mainSectionYn.eq(Const.USE_Y),
                        story.useYn.eq(Const.USE_Y),
                        story.state.eq(Const.STORY_STATE_ING)
                )
                .fetchCount();

        List<StoryDto> storyDtoList = new ArrayList<>();
        for (Tuple it : results.getResults()) {

            // 모금 % 계산
            double cFA = it.get(story.currentFundAmount);
            double tFA = it.get(story.targetFundAmount);
            double a = (cFA / tFA) * 100;
            int percent = (int) a;

            storyDtoList.add(StoryDto.builder()
                    .seq(it.get(story.seq))
                    .insertUser(it.get(story.insertUser))
                    .insertDate(it.get(story.insertDate))
                    .updateUser(it.get(story.updateUser))
                    .useYn(it.get(story.useYn))
                    .orderValue(it.get(story.orderValue))
                    .typeCode(it.get(story.typeCode))
                    .fundTypeCode(it.get(story.fundTypeCode))
                    .agencySeq(it.get(story.agencySeq))
                    .title(it.get(story.title))
                    .content(it.get(story.content))
                    .businessStartDt(it.get(story.businessStartDt))
                    .businessEndDt(it.get(story.businessEndDt))
                    .fundStartDt(it.get(story.fundStartDt))
                    .fundEndDt(it.get(story.fundEndDt))
                    .targetFundAmount(it.get(story.targetFundAmount))
                    .currentFundAmount(it.get(story.currentFundAmount))
                    .imageSeq(it.get(story.imageSeq))
                    .imagePath(it.get(story.imagePath))
                    .imageFile(it.get(story.imageFile))
                    .linkUri(it.get(story.linkUri))
                    .agencyTypeOrder(it.get(story.agencyTypeOrder))
                    .pickCount(it.get(story.pickCount))
                    .fundRate(it.get(story.fundRate))
                    .benefitTargetContent(it.get(story.benefitTargetContent))
                    .personFundAmount(it.get(story.personFundAmount))
                    .personFundCount(it.get(story.personFundCount))
                    .companyFundAmount(it.get(story.companyFundAmount))
                    .companyFundCount(it.get(story.companyFundCount))
                    .actionFundAmount(it.get(story.actionFundAmount))
                    .actionFundCount(it.get(story.actionFundCount))
                    .currentProductAmount(it.get(story.currentProductAmount))
                    .personProductAmount(it.get(story.personProductAmount))
                    .companyProductAmount(it.get(story.companyProductAmount))
                    .actionProductAmount(it.get(story.actionProductAmount))
                    .storyTypeCode(it.get(story.storyTypeCode))
                    .commentCount(it.get(story.commentCount))
                    .productActionValue(it.get(story.productActionValue))
                    .productActionYn(it.get(story.productActionYn))
                    .relationStorySeq(it.get(story.relationStorySeq))
                    .receiptPath(it.get(story.receiptPath))
                    .receiptFile(it.get(story.receiptFile))
                    // .fundPercent(percent)
                    .parentSeq(it.get(story.parentSeq))
                    .groupId(it.get(story.groupId))
                    .storyRound(it.get(story.storyRound))
                    .fundMapFundAmount(it.get(story.fundMapFundAmount))
                    .fundMapFundCount(it.get(story.fundMapFundCount))
                    .state(it.get(story.state))
                    .regularStopYn(it.get(story.regularStopYn))
                    .originFundStartDt(it.get(story.originFundStartDt))
                    .originFundEndDt(it.get(story.originFundEndDt))
                    .mainSectionYn(it.get(story.mainSectionYn))
                    .currentMainCnt(currentMainCnt)
                    .build());
        }

        return PageableExecutionUtils.getPage(storyDtoList, pageable, countQuery::fetchCount);
    }

    @Override
    public long count(String typeCode, String search) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(search)) {
            booleanBuilder.and(
                    story.title.containsIgnoreCase(search)
                            .or(story.content.containsIgnoreCase(search))
            );
        }
        return queryFactory
                .select(story)
                .from(story)
                .fetchCount();
    }

    @Override
    public List<Story> findListBySegs(Integer[] segs, Integer useYn) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (useYn != null && !useYn.equals(-1)) {
            booleanBuilder.and(
                    story.useYn.eq(useYn)
            );
        }

        List<Story> results = queryFactory
                .select(story)
                .from(story)
                .where(
                        booleanBuilder
                        , (story.seq.in(segs))
                )
                .fetch();

        if (results == null)
            return new ArrayList<>();

        return results;
    }

    @Override
    public List<StoryDto> listStory() {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            now = dateFormat.parse(dateFormat.format(now));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        QueryResults<Tuple> results = queryFactory
                .select(
                        story.seq
                        , story.title
                        , story.fundStartDt
                        , story.fundEndDt
                        , story.currentFundAmount
                        , story.targetFundAmount
                        , story.imagePath
                        , story.imageFile
                        , story.fundRate
                        , agency.name
                        , story.state
                        , story.regularStopYn
                        , story.targetFundAmountDispYn
                        , story.donationTarget
                        , story.tags
                        , story.fundTypeCode
                        , story.insertDate
                        , story.mainSectionYn
                        , story.mainSectionUpdateDate.coalesce(oneYearAgo)
                )
                .from(story)
                .leftJoin(agency).on(agency.seq.eq(story.agencySeq))
                .where(
                        story.useYn.eq(1)
                        // , (story.fundTypeCode.eq(2))
                        // 마감여부 먼저 체크
                        , (story.state.isEmpty().or(story.state.ne(Const.STORY_STATE_END)))
                        // 마감날짜 체크
                        , ((story.fundEndDt.isNull().and(story.fundRate.lt(100)))
                                .or(story.fundEndDt.isNotNull().and(story.fundEndDt.after(now))))
                )
                .orderBy(
                        story.mainSectionYn.desc(),
                        story.mainSectionUpdateDate.desc()
                )
                .fetchResults();

        List<StoryDto> content = new ArrayList<>();
        for (Tuple item : results.getResults()) {

            // 모금 % 계산
            double cFA = item.get(story.currentFundAmount);
            double tFA = item.get(story.targetFundAmount);
            double a = (cFA / tFA) * 100;
            int percent = (int) a;

            // 말머리 세팅
            String preface = "";
            if (item.get(story.donationTarget) == null) {
                preface = (item.get(story.fundTypeCode) != null && item.get(story.fundTypeCode) == Const.STORY_FUND_TYPE_ROUTINE) ? "정기모금" : "일시모금";
            } else if (item.get(story.donationTarget).equals(StoryDonationTarget.EMPLOYEE)) {
                preface = "나눔파트너";
            } else if (item.get(story.donationTarget).equals(StoryDonationTarget.WITH_USERS)) {
                preface = "나눔파트너 투게더";
            }

            // dday 계산
            String dday = DateUtil.getDday(LocalDateTime.now(), item.get(story.fundEndDt));

            content.add(StoryDto.builder()
                    .seq(item.get(story.seq))
                    .title(item.get(story.title))
                    .fundStartDt(item.get(story.fundStartDt))
                    .fundEndDt(item.get(story.fundEndDt))
                    .currentFundAmount(item.get(story.currentFundAmount))
                    .targetFundAmount(item.get(story.targetFundAmount))
                    .imagePath(item.get(story.imagePath))
                    .imageFile(item.get(story.imageFile))
                    .agency_name(item.get(agency.name))
                    // .fundPercent(percent)
                    .fundRate(item.get(story.fundRate))
                    .state(item.get(story.state))
                    .regularStopYn(item.get(story.regularStopYn))
                    .targetFundAmountDispYn(item.get(story.targetFundAmountDispYn))
                    .tags(item.get(story.tags))
                    .preface(preface)
                    .dday(dday)
                    .fundTypeCode(item.get(story.fundTypeCode))
                    .insertDate(item.get(story.insertDate))
                    .mainSectionYn(item.get(story.mainSectionYn))
                    .mainSectionUpdateDate(item.get(story.mainSectionUpdateDate))
                    .build());
        }

        return content;
    }

    // 정렬
    private List<OrderSpecifier> sort(Integer sort, String state) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (StringUtils.isEmpty(sort)) {
            OrderSpecifier<?> emptyOrder = getSortedColumn(Order.DESC, story.seq, "seq");
            ORDERS.add(emptyOrder);
            return ORDERS;
        }

        switch (sort) {
            case -1:
                OrderSpecifier<?> order = getSortedColumn(Order.ASC, story.state, "state");
                ORDERS.add(order);
                order = getSortedColumn(Order.DESC, story.realEndDt, "realEndDt"); // 모금종료일 최신순
                ORDERS.add(order);
                order = getSortedColumn(Order.DESC, story.insertDate, "insertDate");
                ORDERS.add(order);
                break;
            case 1:
                OrderSpecifier<?> order1 = getSortedColumn(Order.ASC, story.state, "state");
                order1 = getSortedColumn(Order.DESC, story.orderValue, "orderValue");
                ORDERS.add(order1);
                order1 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order1);
                break;
            case 2:
                OrderSpecifier<?> order2 = getSortedColumn(Order.ASC, story.state, "state");
                order2 = getSortedColumn(Order.DESC, story.insertDate, "insertDate");
                ORDERS.add(order2);
                order2 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order2);
                break;
            case 3:
                OrderSpecifier<?> order3 = getSortedColumn(Order.ASC, story.state, "state");
                order3 = getSortedColumn(Order.ASC, story.realEndDt, "realEndDt");
                ORDERS.add(order3);
                order3 = getSortedColumn(Order.ASC, story.seq, "seq");
                ORDERS.add(order3);
                break;
            case 4:
                OrderSpecifier<?> order4 = getSortedColumn(Order.ASC, story.state, "state");
                order4 = getSortedColumn(Order.DESC, story.orderValue, "orderValue");
                ORDERS.add(order4);
                order4 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order4);
                break;
            case 10: // 전체 조회
                OrderSpecifier<?> order10 = getSortedColumn(Order.ASC, story.state, "state"); // 모금중(=fundraising)이 상단으로
                ORDERS.add(order10);
                order10 = getSortedColumn(Order.DESC, story.insertDate, "insertDate");
                ORDERS.add(order10);
                break;
            case 11:
                OrderSpecifier<?> order11 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder");
                ORDERS.add(order11);
                order11 = getSortedColumn(Order.DESC, story.pickCount, "pickCount");
                ORDERS.add(order11);
                order11 = getSortedColumn(Order.DESC, story.fundRate, "fundRate");
                ORDERS.add(order11);
                order11 = getSortedColumn(Order.DESC, story.businessEndDt, "businessEndDt");
                ORDERS.add(order11);
                order11 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order11);
                break;
            case 12:
                OrderSpecifier<?> order12 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder");
                ORDERS.add(order12);
                order12 = getSortedColumn(Order.DESC, story.pickCount, "pickCount");
                ORDERS.add(order12);
                order12 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order12);
                break;
            case 13:
                OrderSpecifier<?> order13 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder");
                ORDERS.add(order13);
                order13 = getSortedColumn(Order.DESC, story.fundRate, "fundRate");
                ORDERS.add(order13);
                order13 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order13);
                break;
            case 14:// 모금중(=fundraising) 은 랜덤 노출로 변경
                ORDERS.add(Expressions.numberTemplate(Double.class, "function('rand')").asc());  // -->> 중복제거 안됨... 페이징 로직에서는 사용하기 어려움
				/*OrderSpecifier<?> order14 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder"); // 종료임박순 + 최신순
				ORDERS.add(order14);
				order14 = getSortedColumn(Order.ASC, story.realEndDt, "realEndDt");
				ORDERS.add(order14);
				order14 = getSortedColumn(Order.DESC, story.insertDate, "insertDate");
				ORDERS.add(order14);*/
                break;
            case 15:
                OrderSpecifier<?> order15 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder");
                ORDERS.add(order15);
                order15 = getSortedColumn(Order.DESC, story.insertDate, "insertDate");
                ORDERS.add(order15);
                order15 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order15);
                break;
            case 16:
                OrderSpecifier<?> order16 = getSortedColumn(Order.DESC, story.agencyTypeOrder, "agencyTypeOrder");
                ORDERS.add(order16);
                order16 = getSortedColumn(Order.DESC, story.pickCount, "pickCount");
                ORDERS.add(order16);
                order16 = getSortedColumn(Order.DESC, story.fundRate, "fundRate");
                ORDERS.add(order16);
                order16 = getSortedColumn(Order.DESC, story.businessEndDt, "businessEndDt");
                ORDERS.add(order16);
                order16 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order16);
                break;
            case 17: // 마감일 임박순 : 진행중+마감일임박순 > 진행중+마감일없는사연 > 진행종료
                OrderSpecifier<?> order17 = getSortedColumn(Order.ASC, story.realEndDt, "realEndDt");
                ORDERS.add(order17);
                order17 = getSortedColumnNullHandling(Order.ASC, story.fundEndDt, "fundEndDt", OrderSpecifier.NullHandling.NullsLast);
                ORDERS.add(order17);
                break;
            case 18: // 모금액 많은 순
                OrderSpecifier<?> order18 = getSortedColumn(Order.DESC, story.currentFundAmount, "currentFundAmount");
                ORDERS.add(order18);
                break;
            case 19: // 모금액 적은 순
                OrderSpecifier<?> order19 = getSortedColumn(Order.ASC, story.currentFundAmount, "currentFundAmount");
                ORDERS.add(order19);
                break;
            case 100:
                OrderSpecifier<?> order100 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order100);
                break;
            case 101:
                OrderSpecifier<?> order101 = getSortedColumnNullHandling(Order.DESC, story.mainSectionYn, "mainSectionYn", OrderSpecifier.NullHandling.NullsLast);
                ORDERS.add(order101);
                order101 = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(order101);
                break;
            default:
                OrderSpecifier<?> defaultOrder = getSortedColumn(Order.DESC, story.seq, "seq");
                ORDERS.add(defaultOrder);
                break;
        }

        return ORDERS;
    }

    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    public static OrderSpecifier<?> getSortedColumnNullHandling(Order order, Path<?> parent, String fieldName, OrderSpecifier.NullHandling nullHandling) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath, nullHandling);
    }

    private BooleanExpression eqFieldValue(String fieldType, Integer value) {
        if (fieldType == null || fieldType.equals("")) return null;
        // fundEndDt 일 때는 -1 도 가능
        if (value == null || value.equals("") || (!fieldType.equalsIgnoreCase("fundEndDt") && value.equals(-1)))
            return null;

        if (fieldType.equals("useYn")) {
            return story.useYn.eq(value);
        } else if (fieldType.equals("agencySeq")) {
            return story.agencySeq.eq(value);
        } else if (fieldType.equals("typeCode")) {
            return story.typeCode.eq(value);
        } else if (fieldType.equals("fundTypeCode")) {
            return story.fundTypeCode.eq(value);
        } else if (fieldType.equals("mainSectionYn")) {
            return story.mainSectionYn.eq(value);
        } else if (fieldType.equals("fundEndDt")) {
            if (value.equals(4) || value.equals(10) || value.equals(16) || value.equals(100) || value.equals(101)) return null;
            String dateFormatType = "%Y-%m-%d";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());

            String today = dateFormat.format(cal.getTime());
            DateTemplate<String> fundEndDt = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType);
            DateTemplate<String> realEndDt = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.realEndDt, dateFormatType);

            String dateFormatType2 = "%Y-%m-%d %H:%i:%s";
            String defaultDate = "0000-00-00 00:00:00";
            DateTemplate<String> fundEndDt2 = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType2);

            if (value.equals(-1)) { // 모금종료 = -1. 종료된 사연을 조회하려면 realEndDt로 사용해야 함
                return story.realEndDt.isNotNull().and(realEndDt.loe(today));
            } else {
                return fundEndDt.goe(today).or(fundEndDt2.eq(defaultDate)).or(story.fundEndDt.isNull());
            }
        }

        return null;
    }

    // fundEndDt 체크 (현재와 비교, null값인 경우도 포함)
    public BooleanBuilder checkFundEndDate(Integer orderValue) {
        if (orderValue.equals(4) || orderValue.equals(16) || orderValue.equals(100) || orderValue.equals(101)) return null;

        String dateFormatType = "%Y-%m-%d";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String today = dateFormat.format(cal.getTime());

        String dateFormatType2 = "%Y-%m-%d %H:%i:%s";
        String defaultDate = "0000-00-00 00:00:00";

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(
                Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType)
                        .goe(today)
        );
        booleanBuilder.or(
                story.fundEndDt.isNull()
        );
        booleanBuilder.or(
                Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType2)
                        .eq(defaultDate)
        );
        return booleanBuilder;
    }

    private BooleanExpression eqFieldValue(String fieldType, Integer[] value) {
        if (fieldType == null || fieldType.equals("")) return null;
        if (value == null || value.equals("")) return null;

        if (fieldType.equals("storyTypeCode")) {
            return story.storyTypeCode.in(value);
        }

        return null;
    }

    // storyTypeCodeArray 비교 (null이 아니면 in 비교)
    public BooleanExpression compareStoryTypeCodeArr(List<Integer> storyTypeCodeArr) {
        if (storyTypeCodeArr == null || storyTypeCodeArr.size() <= 0)
            return null;

        return story.storyTypeCode.in(storyTypeCodeArr);
    }

    private BooleanExpression eqFieldValue(String fieldType, String value) {
        if (fieldType == null || fieldType.equals("")) return null;
        if (value == null || value.equals("")) return null;

        if (fieldType.equals("benefitTargetCode")) {
            return story.benefitTargetCode.in(value);
        } else if (fieldType.equals("state")) {
            return story.state.eq(value);
        }

        return null;
    }

    private BooleanExpression containsKeyword(String search) {
        if (search == null || search.equals("")) return null;

        return story.title.containsIgnoreCase(search)
                .or(agency.name.containsIgnoreCase(search))
                .or(story.benefitTargetContent.containsIgnoreCase(search));
    }

    private BooleanExpression containsSearch(String fieldType, String search) {
        if (fieldType == null || fieldType.equals("")) return null;
        if (search == null || search.equals("")) return null;

        if (fieldType.equals("title")) {
            return story.title.containsIgnoreCase(search);
        } else if (fieldType.equals("benefitTargetContent")) {
            return story.benefitTargetContent.containsIgnoreCase(search);
        }

        return null;
    }


    /**
     * 정기사연 + 모금종료 + 강제종료가 아님 -> 회차가 지나서 종료된 사연이므로 조건에서 제외
     * 정기사연 + 모금종료 + 강제종료 -> 진행중이던 사연이 강제종료된 경우이므로 조건에 포함. (이전에 종료된 회차는 강제종료 처리 X)
     * >> 정기사연 + 모금종료 + 강제종료가 아닌 사연만 제외 !!!
     */
    private BooleanBuilder exceptEndRegularRound() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

		/*// 정기사연이 아닌 데이터
		booleanBuilder.or(story.fundTypeCode.ne(Const.STORY_FUND_TYPE_ROUTINE));
		// or 정기사연이 + 종료되지 않은 데이터
		booleanBuilder.or(story.fundTypeCode.eq(Const.STORY_FUND_TYPE_ROUTINE).and(story.state.ne("fundraisingEnds")));
		// or 정기사연이 + 종료된 사연 + 강제종료된 데이터 ( -> 강제종료가 아닌 회차가 지나면서 종료된 정기사연은 조건에서 제외하기 위해 )
		booleanBuilder.or(story.fundTypeCode.eq(Const.STORY_FUND_TYPE_ROUTINE).and(story.state.eq("fundraisingEnds")).and(story.regularStopYn.eq(1)));*/


        booleanBuilder.andNot(story.fundTypeCode.eq(Const.STORY_FUND_TYPE_ROUTINE).and(story.state.eq(Const.STORY_STATE_END)).and(story.regularStopYn.eq(0)));

        return booleanBuilder;
    }

    // 모금중인 사연 중 seqList는 제외하도록 (중복제거가 안되는 random 을 위해 이미 조회된 seq 는 제외....)
    private BooleanExpression exceptFundraisingSeq(String state, List<Integer> seqList) {
        if (ObjectUtils.isEmpty(state) || !state.equals(Const.STORY_STATE_ING))
            return null;

        if (seqList == null || seqList.size() <= 0)
            return null;

        return story.seq.notIn(seqList);
    }

    private BooleanExpression stateSearch(String state) {
        if (state == null || state.equals("") || state.equals("all"))
            return null;

        if (state.equals(Const.STORY_STATE_EXCEPT_END)) {
            return story.state.ne(Const.STORY_STATE_END);
        } else {
            return story.state.eq(state);
        }

		/*
		String dateFormatType = "%Y-%m-%d %H:%i:%s";
		String defaultDate = "0000-00-00 00:00:00";

		DateTemplate<String> fundEndDt = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType);
		// if( story.fundEndDt == null || fundEndDt.equals(defaultDate)) return null;

		Date toDay = new Date();
		// 모금중(fundraising), 모금종료(fundraisingEnds), 모금대기(fundwaiting)
		if ( state.equals("fundraising") ) {
			*//**
         *  1. 모금종료일 > 오늘날짜 일 때
         *  2. 모금종료일이 정해져 있지 않을 때 (null), 달성률이 100% 미만 일 때
         *//*
			return story.fundEndDt.gt(toDay)
					.or(
						(story.fundEndDt.isNull().and(story.fundRate.lt(100)))
						.or(fundEndDt.eq(defaultDate).and(story.fundRate.lt(100)))
					);
		} else if ( state.equals("fundraisingEnds") ) {
			*//**
         *  1. 모금종료일 < 오늘날짜 일 때
         *  2. 모금종료일이 정해져 있지 않을 때 (null), 달성률이 100% 이상 일 때
         *//*
			return story.fundEndDt.lt(toDay)
					.or(
						(story.fundEndDt.isNull().and(story.fundRate.goe(100)))
						.or(fundEndDt.eq(defaultDate).and(story.fundRate.goe(100)))
					);
		} else if ( state.equals("fundwaiting") ) {
			*//**
         *  1. 모금시작일 > 오늘날짜 일 때
         *//*
			return story.fundStartDt.gt(toDay);
		}

		return null;
		*/
    }

    // 사연 필터 검색 조건
    private BooleanBuilder storyFilterSearch(List<Integer> benefitTargetSeqs, List<String> states, List<Integer> fundTypeCodes, List<String> tags){
        BooleanBuilder builder = new BooleanBuilder();

        // 대상 검색 - 배분모금함
        if (benefitTargetSeqs != null && !benefitTargetSeqs.isEmpty()) {
            builder.and(story.benefitTargetSeq.in(benefitTargetSeqs));
        }

        // 분야 - 태그 검색
        if (tags != null && !tags.isEmpty()) {
            BooleanBuilder tagBuilder = new BooleanBuilder();
            for (String tag : tags) {
                if (!ObjectUtils.isEmpty(tag)) {
                    tagBuilder.or(story.tags.contains("#" + tag));
                }
            }
            builder.and(tagBuilder);
        }

        // 종료/진행 여부
        if (states!= null && !states.isEmpty()) {
            // 전체보기 포함 > 전체 조회 (대기중을 제외한 진행중, 종료 사연만 조회)
            if (states.contains(Const.STORY_STATE_ALL)) {
                builder.and(story.state.in(Const.STORY_STATE_ING, Const.STORY_STATE_END));
            } else {
                // 전체보기 미포함 > 있는 필터로만 확인
                String dateFormatType = "%Y-%m-%d";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                String today = dateFormat.format(cal.getTime());
                DateTemplate<String> fundEndDt = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType);
                DateTemplate<String> realEndDt = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.realEndDt, dateFormatType);

                String dateFormatType2 = "%Y-%m-%d %H:%i:%s";
                String defaultDate = "0000-00-00 00:00:00";
                DateTemplate<String> fundEndDt2 = Expressions.dateTemplate(String.class, "DATE_FORMAT({0}, {1})", story.fundEndDt, dateFormatType2);
                // + 날짜 조건 추가 (종료된 사연을 조회하려면 realEndDt로 사용)
                if (states.contains(Const.STORY_STATE_END)) {
                    builder.and(story.realEndDt.isNotNull().and(realEndDt.loe(today)));
                    builder.and(story.state.in(states));
                } else {
                    builder.and(fundEndDt.goe(today).or(fundEndDt2.eq(defaultDate)).or(story.fundEndDt.isNull()));
                    builder.and(story.state.in(states));
                }
            }
        }

        // 일시/정기 유형
        if (fundTypeCodes != null && !fundTypeCodes.isEmpty()) {
            builder.and(story.fundTypeCode.in(fundTypeCodes));
        }

        return builder;
    }

    private BooleanExpression eqIntegerColumn(NumberPath<Integer> column, Integer value) {
        if (column == null || value == null || value < 0) {
            return null;
        }

        return column.eq(value);
    }
}
