package com.mtl.cypw.ticket.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.juqitech.response.paging.Pagination;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.util.BusinessIDGenerator;
import com.mtl.cypw.common.util.GeneratorModule;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.domain.ticket.dto.OrderPaperDTO;
import com.mtl.cypw.domain.ticket.enums.FetchStatusEnum;
import com.mtl.cypw.domain.ticket.enums.VoucherStatusEnum;
import com.mtl.cypw.domain.ticket.enums.VoucherTypeEnum;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import com.mtl.cypw.order.service.OrderDeliveryService;
import com.mtl.cypw.order.service.OrderTicketService;
import com.mtl.cypw.ticket.exception.fetch.FetchPermissionDeniedException;
import com.mtl.cypw.ticket.mapper.FetchVoucherDetailMapper;
import com.mtl.cypw.ticket.mapper.FetchVoucherMapper;
import com.mtl.cypw.ticket.model.FetchVoucher;
import com.mtl.cypw.ticket.model.FetchVoucherDetail;
import com.mtl.cypw.ticket.repository.FetchVoucherRepository;
import com.mtl.cypw.ticket.service.FetchVoucherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-20 15:22
 */
@Slf4j
@Service
public class FetchVoucherServiceImpl implements FetchVoucherService {
    @Autowired
    private OrderTicketService orderTicketServiceImpl;

    @Autowired
    private OrderDeliveryService orderDeliveryServiceImpl;

    @Autowired
    private FetchVoucherRepository fetchVoucherRepository;

    @Autowired
    private FetchVoucherMapper fetchVoucherMapper;

    @Autowired
    private FetchVoucherDetailMapper fetchVoucherDetailMapper;

    @Override
    public FetchVoucher issue() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FetchVoucher fetchAck(OrderPaperDTO paper) {
        FetchVoucher voucher = new FetchVoucher();
        return voucher;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FetchVoucher fetchGoodsAck(OrderPaperDTO paper, FetchCommandParam param) {
        if (paper == null || CollectionUtils.isEmpty(paper.getTicketPapers())) {
            throw new FetchPermissionDeniedException(ErrorCode.ERROR_TICKET_FETCH_PERMISSION_DENIED.getMsg());
        }
        FetchVoucher voucher = this.assembleVoucher(paper, VoucherStatusEnum.COMPLETED);
        voucher.setVoucherType(VoucherTypeEnum.GOODS.getCode());
        voucher.setVoucherDesc(VoucherTypeEnum.GOODS.getName() + "凭证");
        voucher.setHandleType(param.getHandleType());
        voucher.setFetchMethod(param.getFetchMethod());
        List<Integer> ticketIds = Lists.transform(paper.getTicketPapers(), ticketPaper -> ticketPaper.getTicketId());
        fetchVoucherRepository.ackByGoods(voucher, ticketIds);
        orderTicketServiceImpl.updateFetchStatus(FetchStatusEnum.FETCHED.getCode(), voucher.getFetchCompleteTime(), ticketIds, Lists.newArrayList(FetchStatusEnum.NOT_FETCHED.getCode()), voucher.getOrderId());
        orderDeliveryServiceImpl.updateFetchStatusByOrderId(FetchStatusEnum.FETCHED.getCode(), voucher.getFetchCompleteTime(), voucher.getOrderId(), FetchStatusEnum.NOT_FETCHED.getCode());
        return voucher;
    }

    private FetchVoucher assembleVoucher(OrderPaperDTO paper, VoucherStatusEnum status) {
        FetchVoucher voucher = new FetchVoucher();
        Date current = DateUtils.now();
        voucher.setOrderId(paper.getOrderId());
        voucher.setVoucherNo(BusinessIDGenerator.genID(GeneratorModule.BIZ_FETCH));
        voucher.setFetchLockTime(current);
        voucher.setFetchCompleteTime(current);
        voucher.setFetchCode(paper.getFetchCode());
        voucher.setQuantity(paper.getTicketPapers().size());
        voucher.setStatus(status.getCode());
        voucher.setFetchUserId(paper.getFetchUserId());
        voucher.setFetchUserName(paper.getFetchUserName());
        voucher.setEnterpriseId(paper.getEnterpriseId());
        return voucher;
    }


    @Override
    public FetchVoucher findVoucherByVoucherNo(String voucherNo, Integer enterpriseId) {
        return null;
    }

    @Override
    public List<FetchVoucher> findVoucherByQuery(FetchQueryParam param, Pagination pagination) {
        Example example = new Example(FetchVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        if (param.getVoucherType() != null) {
            criteria.andEqualTo("voucherType", param.getVoucherType());
        }
        if (param.getFetchMethod() != null) {
            criteria.andEqualTo("fetchMethod", param.getFetchMethod());
        }
        if (StringUtils.isNotBlank(param.getFetchCode())) {
            criteria.andEqualTo("fetchCode", param.getFetchCode());
        }
        if (StringUtils.isNotBlank(param.getIdCard())) {
            criteria.andEqualTo("idCard", param.getIdCard());
        }
        if (param.getFetchUserId() != null) {
            criteria.andEqualTo("fetchUserId", param.getFetchUserId());
        }
        if (param.getFetchDate() != null) {
            criteria.andGreaterThanOrEqualTo("fetchCompleteTime", DateUtil.getDayBeginTime(param.getFetchDate()));
            criteria.andLessThanOrEqualTo("fetchCompleteTime", DateUtil.getDayEndTime(param.getFetchDate()));
        }
        if (param.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        }
        example.orderBy("id").desc();
        Page page = null;
        if (pagination != null) {
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        }
        List<FetchVoucher> records = fetchVoucherMapper.selectByExample(example);
        if (page != null) {
            pagination.setCount(page.getTotal());
        }
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records;
    }

    @Override
    public List<Integer> findTicketIdListByVoucherId(Integer voucherId) {
        Example example = new Example(FetchVoucherDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("voucherId", voucherId);
        List<FetchVoucherDetail> details = fetchVoucherDetailMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(details)) {
            return Collections.emptyList();
        }
        return Lists.transform(details, detail -> detail.getTicketId());
    }
}
