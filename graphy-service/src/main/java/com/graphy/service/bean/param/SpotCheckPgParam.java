package com.graphy.service.bean.param;
import com.graphy.service.bean.OwnPageParam;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;

import java.sql.Date;

/**
* @auther 张小昌
* @create 2021-12-13 16:52:21
* @describe 请求参数
*/
@Data
@ApiModel(value = "SpotCheckPgParam对象", description = "请求参数")
public class SpotCheckPgParam extends OwnPageParam {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String id;
    /**
     * 抽查状态0=未抽查,1=已抽查
     */
    @ApiModelProperty(value="抽查状态",required = false)
    private String spotCheckStatus;
    /**
     * 抽查意见
     */
    @ApiModelProperty(value="抽查意见",required = false)
    private String checkRemark;
    /**
     * 抽查结果
     */
    @ApiModelProperty(value="抽查结果",required = false)
    private String judge;
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID",required = false)
    private String userId;

    /**
    * 医保类型 关联字典信息表TYPE_=graphy-type
    */
    @ApiModelProperty(value="医保类型 关联字典信息表TYPE_=graphy-type ",required = false)
    private String graphyType;

    /**
    * 病种ID
    */
    @ApiModelProperty(value="病种ID",required = false)
    private String illnessId;

    /**
    * 病种名称
    */
    @ApiModelProperty(value="病种名称",required = false)
    private String illnessName;

    /**
    * 签章合同号
    */
    @ApiModelProperty(value="签章合同号",required = false)
    private String singOrderNo;

    /**
    * 申请合同号
    */
    @ApiModelProperty(value="申请合同号",required = false)
    private String applyOrderNo;

    /**
    * 关联tb_bus_archive表id_
    */
    @ApiModelProperty(value="关联tb_bus_archive表id_",required = false)
    private String archiveId;

    /**
    * 姓名
    */
    @ApiModelProperty(value="姓名",required = false)
    private String name;

    /**
    * 性别 1=男 2=女
    */
    @ApiModelProperty(value="性别 1=男 2=女",required = false)
    private String gender;

    /**
    * 出生-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="出生-开始",required = false)
    private Date birthStart;

    /**
    * 出生-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="出生-截止",required = false)
    private Date birthEnd;

    /**
    * 省
    */
    @ApiModelProperty(value="省",required = false)
    private String prov;

    /**
    * 市
    */
    @ApiModelProperty(value="市",required = false)
    private String city;

    /**
    * 县/区
    */
    @ApiModelProperty(value="县/区",required = false)
    private String county;

    /**
    * 镇/街道
    */
    @ApiModelProperty(value="镇/街道",required = false)
    private String town;

    /**
    * 社区
    */
    @ApiModelProperty(value="社区",required = false)
    private String community;

    /**
    * 社会保障卡
    */
    @ApiModelProperty(value="社会保障卡",required = false)
    private String socialCard;

    /**
    * 身份证
    */
    @ApiModelProperty(value="身份证",required = false)
    private String idCard;

    /**
    * 联系手机
    */
    @ApiModelProperty(value="联系手机",required = false)
    private String mobile;

    /**
    * 签名图片
    */
    @ApiModelProperty(value="签名图片",required = false)
    private String signPic;

    /**
    * 定点医院 关联tb_bus_unit表id
    */
    @ApiModelProperty(value="定点医院 关联tb_bus_unit表id",required = false)
    private String unitFixed;

    /**
    * 申请时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请时间-开始",required = false)
    private Date applyTimeStart;

    /**
    * 申请时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请时间-截止",required = false)
    private Date applyTimeEnd;

    /**
    * 申请状态 0=已取消 1=已经申请
    */
    @ApiModelProperty(value="申请状态 0=已取消 1=已经申请",required = false)
    private String applyStatus;

    /**
    * 申请取消时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请取消时间-开始",required = false)
    private Date applyCancelTimeStart;

    /**
    * 申请取消时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请取消时间-截止",required = false)
    private Date applyCancelTimeEnd;

    /**
    * 用户最后一次修改时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="用户最后一次修改时间-开始",required = false)
    private Date userLastUpTimeStart;

    /**
    * 用户最后一次修改时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="用户最后一次修改时间-截止",required = false)
    private Date userLastUpTimeEnd;

    /**
    * 审批状态 0=待审批 1=审批中 2=审批通过 3=退回
    */
    @ApiModelProperty(value="审批状态 0=待审批 1=审批中 2=审批通过 3=退回",required = false)
    private String checkStatus;

    /**
    * 最后一次审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="最后一次审批时间-开始",required = false)
    private Date checkLastTimeStart;

    /**
    * 最后一次审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="最后一次审批时间-截止",required = false)
    private Date checkLastTimeEnd;

    /**
    * 定点医疗机构医保办审批状态 0=待审批 1=通过 2=退回
    */
    @ApiModelProperty(value="定点医疗机构医保办审批状态 0=待审批 1=通过 2=退回",required = false)
    private String checkUnitFixedStatus;

    /**
    * 定点医疗机构医保办审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="定点医疗机构医保办审批时间-开始",required = false)
    private Date checkUnitFixedTimeStart;

    /**
    * 定点医疗机构医保办审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="定点医疗机构医保办审批时间-截止",required = false)
    private Date checkUnitFixedTimeEnd;

    /**
    * 定点医疗机构医保办审批人
    */
    @ApiModelProperty(value="定点医疗机构医保办审批人",required = false)
    private String checkUnitFixedUser;

    /**
    * 定点医疗机构医保办审批人姓名
    */
    @ApiModelProperty(value="定点医疗机构医保办审批人姓名",required = false)
    private String checkUnitFixedUserName;

    /**
    * 定点医疗机构医保办审批意见
    */
    @ApiModelProperty(value="定点医疗机构医保办审批意见",required = false)
    private String checkUnitFixedUserRemark;

    /**
    * 县（区）医保经办机构专家审批状态 0=待审批 1=通过 2=退回
    */
    @ApiModelProperty(value="县（区）医保经办机构专家审批状态 0=待审批 1=通过 2=退回",required = false)
    private String checkSavantCountyStatus;

    /**
    * 县（区）医保经办机构专家审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="县（区）医保经办机构专家审批时间-开始",required = false)
    private Date checkSavantCountyTimeStart;

    /**
    * 县（区）医保经办机构专家审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="县（区）医保经办机构专家审批时间-截止",required = false)
    private Date checkSavantCountyTimeEnd;

    /**
    * 县（区）医保经办机构专家审批人
    */
    @ApiModelProperty(value="县（区）医保经办机构专家审批人",required = false)
    private String checkSavantCountyUser;

    /**
    * 县（区）医保经办机构专家审批人姓名
    */
    @ApiModelProperty(value="县（区）医保经办机构专家审批人姓名",required = false)
    private String checkSavantCountyUserName;

    /**
    * 县（区）医保经办机构专家审批意见
    */
    @ApiModelProperty(value="县（区）医保经办机构专家审批意见",required = false)
    private String checkSavantCountyUserRemark;

    /**
    * 县（区）医保经办机构审批状态 0=待审批 1=通过 2=退回
    */
    @ApiModelProperty(value="县（区）医保经办机构审批状态 0=待审批 1=通过 2=退回",required = false)
    private String checkUnitCountyStatus;

    /**
    * 县（区）医保经办机构审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="县（区）医保经办机构审批时间-开始",required = false)
    private Date checkUnitCountyTimeStart;

    /**
    * 县（区）医保经办机构审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="县（区）医保经办机构审批时间-截止",required = false)
    private Date checkUnitCountyTimeEnd;

    /**
    * 县（区）医保经办机构审批人
    */
    @ApiModelProperty(value="县（区）医保经办机构审批人",required = false)
    private String checkUnitCountyUser;

    /**
    * 县（区）医保经办机构审批人姓名
    */
    @ApiModelProperty(value="县（区）医保经办机构审批人姓名",required = false)
    private String checkUnitCountyUserName;

    /**
    * 县（区）医保经办机构审批意见
    */
    @ApiModelProperty(value="县（区）医保经办机构审批意见",required = false)
    private String checkUnitCountyUserRemark;

    /**
    * 市医保经办机构专家审批状态 0=待审批 1=通过 2=退回
    */
    @ApiModelProperty(value="市医保经办机构专家审批状态 0=待审批 1=通过 2=退回",required = false)
    private String checkSavantCityStatus;

    /**
    * 市医保经办机构专家审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="市医保经办机构专家审批时间-开始",required = false)
    private Date checkSavantCityTimeStart;

    /**
    * 市医保经办机构专家审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="市医保经办机构专家审批时间-截止",required = false)
    private Date checkSavantCityTimeEnd;

    /**
    * 市医保经办机构专家审批人
    */
    @ApiModelProperty(value="市医保经办机构专家审批人",required = false)
    private String checkSavantCityUser;

    /**
    * 市医保经办机构专家审批人姓名
    */
    @ApiModelProperty(value="市医保经办机构专家审批人姓名",required = false)
    private String checkSavantCityUserName;

    /**
    * 市医保经办机构专家审批意见
    */
    @ApiModelProperty(value="市医保经办机构专家审批意见",required = false)
    private String checkSavantCityUserRemark;

    /**
    * 市医保经办机构审批状态 0=待审批 1=通过 2=退回
    */
    @ApiModelProperty(value="市医保经办机构审批状态 0=待审批 1=通过 2=退回",required = false)
    private String checkUnitCityStatus;

    /**
    * 市医保经办机构审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="市医保经办机构审批时间-开始",required = false)
    private Date checkUnitCityTimeStart;

    /**
    * 市医保经办机构审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="市医保经办机构审批时间-截止",required = false)
    private Date checkUnitCityTimeEnd;

    /**
    * 市医保经办机构审批人
    */
    @ApiModelProperty(value="市医保经办机构审批人",required = false)
    private String checkUnitCityUser;

    /**
    * 市医保经办机构审批人姓名
    */
    @ApiModelProperty(value="市医保经办机构审批人姓名",required = false)
    private String checkUnitCityUserName;

    /**
    * 市医保经办机构审批意见
    */
    @ApiModelProperty(value="市医保经办机构审批意见",required = false)
    private String checkUnitCityUserRemark;

    /**
    * 最后一个审批单ID
    */
    @ApiModelProperty(value="最后一个审批单ID",required = false)
    private String applyCheckId;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态",required = false)
    private String status;

    /**
    * 创建时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间-开始",required = false)
    private Date ctimeStart;

    /**
    * 创建时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间-截止",required = false)
    private Date ctimeEnd;

    /**
    * 创建人
    */
    @ApiModelProperty(value="创建人",required = false)
    private String cuser;

    /**
    * 创建人名称
    */
    @ApiModelProperty(value="创建人名称",required = false)
    private String cuserName;

    /**
    * 修改时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="修改时间-开始",required = false)
    private Date utimeStart;

    /**
    * 修改时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="修改时间-截止",required = false)
    private Date utimeEnd;

    /**
    * 修改人
    */
    @ApiModelProperty(value="修改人",required = false)
    private String uuser;

    /**
    * 修改人名称
    */
    @ApiModelProperty(value="修改人名称",required = false)
    private String uuserName;

    /**
    * 删除时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="删除时间-开始",required = false)
    private Date dtimeStart;

    /**
    * 删除时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="删除时间-截止",required = false)
    private Date dtimeEnd;

    /**
    * 删除人
    */
    @ApiModelProperty(value="删除人",required = false)
    private String duser;

    /**
    * 删除人名称
    */
    @ApiModelProperty(value="删除人名称",required = false)
    private String duserName;

    /**
    * 机构名称
    */
    @ApiModelProperty(value="机构名称",required = false)
    private String unitFixedName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String unitFixedCountyName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private Long age;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String genderName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String provName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String cityName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String countyName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String townName;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String communityName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String address;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String graphyTypeName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkStatusName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkUnitFixedStatusName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkSavantCountyStatusName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkUnitCountyStatusName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkSavantCityStatusName;

    /**
    * null
    */
    @ApiModelProperty(value="null",required = false)
    private String checkUnitCityStatusName;
    /**
     * 定点医院所属行政区划
     */
    @ApiModelProperty(value = "定点医院所属行政区划", required = false)
    private String unitCounty;

    /**
     * 患者地址区划
     */
    @ApiModelProperty(value = "患者地址区划", required = false)
    private String userCounty;
}