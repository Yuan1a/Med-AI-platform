package com.graphy.service.service;
import com.graphy.service.bean.dto.ApprovalNumberDto;

import com.graphy.service.bean.param.SaveApprovalParam;
import com.graphy.service.bean.param.SavaLicensingApplyParam;
import com.graphy.service.bean.dto.LicensingImagesDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.LicensingImagesParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusLicensingApplyEntity;
import java.util.*;

/**
 * @auther qwt
 * @create 2022-12-08 08:38:40
 * @describe 影像报告授权申请管理
 */
public interface BusLicensingApplyService extends IService<TbBusLicensingApplyEntity> {

    /**
     * auther： qwt
     * create： 2022-12-08 08:42:47
     * describe： 获取影像报告申请
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<LicensingImagesDto>> getLicensingApply(LicensingImagesParam param) throws Exception;

    /**
     * auther： qwt
     * create： 2022-12-08 09:31:12
     * describe： 保存影像授权申请
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> saveApply(SavaLicensingApplyParam param) throws Exception;

    /**
     * auther： qwt
     * create： 2022-12-08 10:04:19
     * describe： 删除影像授权申请
     * @param id   主键ID
     * @return
     */
    OwnResult<Boolean> delApply(String id) throws Exception;

    /**
     * auther： qwt
     * create： 2022-12-08 14:37:38
     * describe： 保存影像授权审批
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> saveApproval(SaveApprovalParam param) throws Exception;

    /**
    * auther： qwt
    * create： 2022-12-12 11:16:05
    * describe： 获取该机构的需要审批的授权申请数
    * @param approvalUnit   审批机构
    * @return
    */
    OwnResult<ApprovalNumberDto> getApprovalNumber(String approvalUnit) throws Exception;
}