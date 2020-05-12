package com.mtl.cypw.ticket.repository;

import com.google.common.collect.Lists;
import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.domain.ticket.enums.FetchMethodEnum;
import com.mtl.cypw.domain.ticket.enums.HandleTypeEnum;
import com.mtl.cypw.domain.ticket.enums.VoucherStatusEnum;
import com.mtl.cypw.domain.ticket.enums.VoucherTypeEnum;
import com.mtl.cypw.ticket.mapper.FetchVoucherDetailMapper;
import com.mtl.cypw.ticket.mapper.FetchVoucherMapper;
import com.mtl.cypw.ticket.model.FetchVoucher;
import com.mtl.cypw.ticket.model.FetchVoucherDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 16:47
 */
@Slf4j
@Component
public class FetchVoucherRepository implements GenericRepository<Integer, FetchVoucher> {

    @Autowired
    private FetchVoucherMapper fetchVoucherMapper;

    @Autowired
    private FetchVoucherDetailMapper fetchVoucherDetailMapper;

    @Override
    public Integer save(FetchVoucher model) {
        Assert.notNull(model, "CheckRecord is null");
        if (model.brandNew()) {
            fetchVoucherMapper.insertSelective(model);

        } else {
            fetchVoucherMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public FetchVoucher issue(FetchVoucher voucher, List<Integer> ticketIds) {
        if (!voucher.brandNew() || CollectionUtils.isEmpty(ticketIds)) {
            return voucher;
        }
        voucher.setStatus(VoucherStatusEnum.LOCKED.getCode());
        voucher.setVoucherType(VoucherTypeEnum.TICKET.getCode());
        Integer voucherId = this.save(voucher);
        List<FetchVoucherDetail> voucherDetails = Lists.newArrayListWithCapacity(ticketIds.size());
        for (Integer ticketId : ticketIds) {
            FetchVoucherDetail detail = new FetchVoucherDetail();
            detail.setVoucherId(voucherId);
            detail.setTicketId(ticketId);
            detail.setEnterpriseId(voucher.getEnterpriseId());
            voucherDetails.add(detail);
        }
        fetchVoucherDetailMapper.insertList(voucherDetails);
        return voucher;
    }

    public FetchVoucher ack(FetchVoucher voucher) {
        voucher.setStatus(VoucherStatusEnum.COMPLETED.getCode());
        voucher.setVoucherType(VoucherTypeEnum.TICKET.getCode());
        this.save(voucher);
        return voucher;
    }

    public FetchVoucher ackByGoods(FetchVoucher voucher, List<Integer> ticketIds) {
        if (!voucher.brandNew() || CollectionUtils.isEmpty(ticketIds)) {
            return voucher;
        }
        voucher.setHandleType(HandleTypeEnum.NORMAL.getCode());
        voucher.setVoucherType(VoucherTypeEnum.GOODS.getCode());
        voucher.setStatus(VoucherStatusEnum.COMPLETED.getCode());
        voucher.setFetchMethod(FetchMethodEnum.APPLET.getCode());
        Integer voucherId = this.save(voucher);
        List<FetchVoucherDetail> voucherDetails = Lists.newArrayListWithCapacity(ticketIds.size());
        for (Integer ticketId : ticketIds) {
            FetchVoucherDetail detail = new FetchVoucherDetail();
            detail.setVoucherId(voucherId);
            detail.setTicketId(ticketId);
            detail.setEnterpriseId(voucher.getEnterpriseId());
            voucherDetails.add(detail);
        }
        fetchVoucherDetailMapper.insertList(voucherDetails);
        return voucher;
    }
}
