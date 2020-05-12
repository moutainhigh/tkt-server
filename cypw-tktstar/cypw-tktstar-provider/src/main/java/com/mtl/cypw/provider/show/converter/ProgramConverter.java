package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.common.dto.EntityMapper;
import com.mtl.cypw.domain.show.dto.ProgramDTO;
import com.mtl.cypw.show.pojo.Program;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/20.
 */
@Component
public class ProgramConverter implements EntityMapper<ProgramDTO, Program> {

    @Override
    public Program toEntity(ProgramDTO programDTO) {
        return null;
    }

    @Override
    public ProgramDTO toDto(Program program) {
        if (program == null) {
            return null;
        }
        ProgramDTO dto = new ProgramDTO();
        dto.setProgramId(program.getProgramId());
        dto.setProgramNo(program.getProgramNo());
        dto.setProgramTitle(program.getProgramTitle());
        dto.setProgramTitleBrief(program.getProgramTitleBrief());
        dto.setVenueId(program.getVenueId());
        dto.setProgramTypeId(program.getProgramTypeId());
        dto.setProgramTime(program.getProgramTime());
        dto.setProgramPrice(program.getProgramPrice());
        dto.setProgramBrief(program.getProgramBrief());
        dto.setSaleDateBegin(program.getSaleDateBegin());
        dto.setSaleDateEnd(program.getSaleDateEnd());
        dto.setIsPublish(program.getIsPublish());
        dto.setSaleStatus(program.getSaleStatus());
        dto.setIsDelete(program.getIsDelete());
        dto.setAppSort(program.getAppSort());
        dto.setWechatSort(program.getWechatSort());
        dto.setPcSort(program.getPcSort());
        dto.setAppRecommend(program.getAppRecommend());
        dto.setWechatRecommend(program.getWechatRecommend());
        dto.setPcRecommend(program.getPcRecommend());
        dto.setAddDate(program.getAddDate());
        dto.setAddUser(program.getAddUser());
        dto.setUpdateDate(program.getUpdateDate());
        dto.setUpdateUser(program.getUpdateUser());
        dto.setListImage(program.getListImage());
        dto.setDetailImage(program.getDetailImage());
        dto.setProgramNotice(program.getProgramNotice());
        dto.setAppShow(program.getAppShow());
        dto.setWechatShow(program.getWechatShow());
        dto.setPcShow(program.getPcShow());
        dto.setDeliveryRestrict(program.getDeliveryRestrict());
        dto.setAppPaymentRestrict(program.getAppPaymentRestrict());
        dto.setWechatPaymentRestrict(program.getWechatPaymentRestrict());
        dto.setPcPaymentRestrict(program.getPcPaymentRestrict());
        dto.setMemberRestrict(program.getMemberRestrict());
        dto.setShowTimeImage(program.getShowTimeImage());
        dto.setShowOtherImages(program.getShowOtherImages());
        dto.setEnterpriseId(program.getEnterpriseId());
        dto.setProgramContent(program.getProgramContent());
        dto.setSupportSeatFlag(program.getSupportSeatFlag());
        dto.setSupportETicket(program.getSupportETicket());
        dto.setIsOfficial(program.getIsOfficial());
        dto.setSponserTitle(program.getSponserTitle());
        dto.setSponserIntroduce(program.getSponserIntroduce());
        dto.setLocationName(program.getLocationName());
        dto.setLocationAddress(program.getLocationAddress());
        dto.setServiceMobile(program.getServiceMobile());
        dto.setKeyValue(program.getKeyValue());
        dto.setTags(program.getTags());
        return dto;
    }

    @Override
    public List<Program> toEntity(Collection<ProgramDTO> collection) {
        return null;
    }

    @Override
    public List<ProgramDTO> toDto(Collection<Program> collection) {
        if (collection == null) {
            return null;
        }
        List<ProgramDTO> list = new ArrayList<>();
        collection.forEach(n -> list.add(toDto(n)));
        return list;
    }
}
