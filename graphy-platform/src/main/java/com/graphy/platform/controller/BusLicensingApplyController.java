package com.graphy.platform.controller;
import com.graphy.service.bean.dto.ApprovalNumberDto;

import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.param.SaveApprovalParam;
import com.graphy.service.bean.param.SavaLicensingApplyParam;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.service.TbBusPatientService;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.service.bean.dto.LicensingImagesDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.LicensingImagesParam;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnValueText;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.ui.Model;
import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbBusLicensingApplyEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusLicensingApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * @auther qwt
 * @create 2022-12-08 08:38:40
 * @describe 影像报告授权申请管理
 */
@Data
@Controller("com.graphy.platform.controller.buslicensingapplycontroller")
@RequiredArgsConstructor
@Api(tags = "影像报告授权申请管理")
@RequestMapping("/pf/BusLicensingApply")
@Slf4j
public class BusLicensingApplyController {

    private final TbBusPatientService tbBusPatientService;

    private final BusLicensingApplyService busLicensingApplyService;

    private final TbBusUnitService tbBusUnitService;

    private final PlatformCommonService platformCommonService;

    private final TbPlatformUserService tbPlatformUserService;

    /**
     * 申请页面
     *
     * @return
     */
    @RequestMapping(value = "/applyindex", method = RequestMethod.GET)
    @ApiOperation(value = "列表页面", notes = "列表页面")
    private String applyindex(Model model) throws Exception {
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>().eq(TbBusUnitEntity::getStatus, "1")), "id", "name"));
        model.addAttribute("unit", unit);
        return "/bus/buslicensingapply/applyindex";
    }

    /**
     * 审批页面
     *
     * @return
     */
    @RequestMapping(value = "/approvalindex", method = RequestMethod.GET)
    @ApiOperation(value = "列表页面", notes = "列表页面")
    private String approvalindex(Model model) throws Exception {
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>().eq(TbBusUnitEntity::getStatus, "1")), "id", "name"));
        model.addAttribute("unit", unit);
        return "/bus/buslicensingapply/approvalindex";
    }

    /**
     * 申请编辑页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/applyedit", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "编辑页面", notes = "编辑页面")
    public String applyedit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbBusLicensingApplyEntity orm = new TbBusLicensingApplyEntity();
        String radiationId = "";
        String approvalUnitName = "";
        if (!StrUtil.hasEmpty(id)) {
            orm = busLicensingApplyService.getById(id);
            TbBusPatientEntity patientServiceById = tbBusPatientService.getById(orm.getPatientId());
            radiationId = patientServiceById.getRadiationId();
            approvalUnitName = tbBusUnitService.getById(orm.getApprovalUnit()).getName();
        } else {
            // 此处编写初始化值
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>().eq(TbBusUnitEntity::getStatus, "1")), "id", "name"));
        model.addAttribute("unit", unit);
        model.addAttribute("radiationId", radiationId);
        model.addAttribute("orm", orm);
        model.addAttribute("approvalUnitName", approvalUnitName);
        return "/bus/buslicensingapply/applyedit";
    }

    /**
     * 审批编辑页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/approvaledit", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "编辑页面", notes = "编辑页面")
    public String approvaledit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbBusLicensingApplyEntity orm = new TbBusLicensingApplyEntity();
        String radiationId = "";
        String approvalUnitName = "";
        if (!StrUtil.hasEmpty(id)) {
            orm = busLicensingApplyService.getById(id);
            TbBusPatientEntity patientServiceById = tbBusPatientService.getById(orm.getPatientId());
            radiationId = patientServiceById.getRadiationId();
            approvalUnitName = tbBusUnitService.getById(orm.getApprovalUnit()).getName();
        } else {
            // 此处编写初始化值
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>().eq(TbBusUnitEntity::getStatus, "1")), "id", "name"));
        model.addAttribute("unit", unit);
        model.addAttribute("radiationId", radiationId);
        model.addAttribute("orm", orm);
        model.addAttribute("approvalUnitName", approvalUnitName);
        return "/bus/buslicensingapply/approvaledit";
    }

    /**
     * auther： qwt
     * create： 2022-12-08 08:42:47
     * describe： 申请机构获取影像报告申请
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getLicensingApply")
    @ApiOperation(value = "获取影像报告申请", notes = "获取影像报告申请")
    @ResponseBody
    private OwnResult<OwnPageResult<LicensingImagesDto>> getLicensingApply(@RequestBody LicensingImagesParam param) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
        param.setApplyUnit(unitEntity.getId());
        return busLicensingApplyService.getLicensingApply(param);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 08:42:47
     * describe： 授权机构获取影像报告申请
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getLicensingApproval")
    @ApiOperation(value = "获取影像报告申请", notes = "获取影像报告申请")
    @ResponseBody
    private OwnResult<OwnPageResult<LicensingImagesDto>> getLicensingApproval(@RequestBody LicensingImagesParam param) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
        param.setApprovalUnit(unitEntity.getId());
        return busLicensingApplyService.getLicensingApply(param);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 09:31:12
     * describe： 保存影像授权申请
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveApply")
    @ApiOperation(value = "保存影像授权申请", notes = "保存影像授权申请")
    @ResponseBody
    private OwnResult<Boolean> saveApply(@RequestBody SavaLicensingApplyParam param) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        param.setApplyUser(userEntity.getName());
        return busLicensingApplyService.saveApply(param);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 10:04:19
     * describe： 删除影像授权申请
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delApply")
    @ApiOperation(value = "删除影像授权申请", notes = "删除影像授权申请")
    @ResponseBody
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", dataType = "String") })
    private OwnResult<Boolean> delApply(String id) throws Exception {
        return busLicensingApplyService.delApply(id);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 14:37:38
     * describe： 保存影像授权审批
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveApproval")
    @ApiOperation(value = "保存影像授权审批", notes = "保存影像shou'q审批")
    @ResponseBody
    private OwnResult<Boolean> saveApproval(@RequestBody SaveApprovalParam param) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        param.setApprovalUser(userEntity.getName());
        return busLicensingApplyService.saveApproval(param);
    }

    /**
    * auther： qwt
    * create： 2022-12-12 11:16:05
    * describe： 获取该机构的需要审批的授权申请数
    */
    @RequestMapping(method = RequestMethod.POST, value = "/getApprovalNumber")
    @ApiOperation(value="获取该机构的需要审批的授权申请数", notes="获取该机构的需要审批的授权申请数")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approvalUnit", value = "审批机构", dataType = "String")
    })
    private OwnResult<ApprovalNumberDto> getApprovalNumber(String approvalUnit) throws Exception {
        return busLicensingApplyService.getApprovalNumber(approvalUnit);
    }
}