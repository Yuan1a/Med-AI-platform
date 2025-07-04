package com.graphy.service.mapper;
import com.graphy.service.bean.dto.ApprovalNumberDto;

import com.graphy.service.bean.dto.LicensingImagesDto;
import com.graphy.service.bean.param.LicensingImagesParam;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * @auther qwt
 * @create 2022-12-08 08:38:40
 * @describe 影像报告授权申请管理
 */
@Repository("com.graphy.service.mapper.buslicensingapplymapper")
public interface BusLicensingApplyMapper {

    /**
     * auther： qwt
     * create： 2022-12-08 08:42:48
     * describe： 获取影像报告申请
     * @param param   参数
     * @return
     */
    IPage<LicensingImagesDto> getLicensingApply(Page<?> page, @Param("param") LicensingImagesParam param) throws Exception;

    /**
    * auther： qwt
    * create： 2022-12-12 11:16:05
    * describe： 获取该机构的需要审批的授权申请数
    * @param approvalUnit   审批机构
    * @return
    */
    ApprovalNumberDto getApprovalNumber(@Param("approvalUnit") String approvalUnit) throws Exception;

}