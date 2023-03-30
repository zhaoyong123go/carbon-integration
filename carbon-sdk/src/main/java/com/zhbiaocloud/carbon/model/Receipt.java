/*
 * Copyright (c) 2018-2023. 成都市维斯凡科技有限公司 All rights reserved.
 */

package com.zhbiaocloud.carbon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 回执数据推送
 *
 * @author jun
 */
@Data
@Schema(title = "回执数据")
public class Receipt {

  @NotBlank
  @Schema(title = "保单号", description = "保单唯一标识符，会用于去重判断", requiredMode = RequiredMode.REQUIRED)
  private String policyNo;

  @Schema(title = "投保单号")
  private String proposalNo;

  @Schema(title = "印刷号")
  private String prtNo;

  @Schema(title = "中介机构编码", description = "一般指中介机构在保险公司销管系统中的编码")
  private String agentCom;

  @Schema(title = "中介机构名称", description = "一般指中介机构在保险公司销管系统中的机构名称")
  private String agentComName;

  @Schema(title = "管理机构", description = "保险公司分公司管理机构、或接单机构编码")
  private String manageCom;

  @Schema(title = "管理机构名称", description = "保险公司分公司管理机构、或接单机构名称")
  private String manageComName;

  @NotNull
  @Schema(title = "回执签收时间", description = "客户（投保人）签收时间", requiredMode = RequiredMode.REQUIRED)
  private LocalDateTime receiptSignTime;

  @NotNull
  @Schema(title = "回执录入时间", description = "回执回销日期，或保险公司收到回执日期", requiredMode = RequiredMode.REQUIRED)
  private LocalDateTime receiptEnteredTime;

}
