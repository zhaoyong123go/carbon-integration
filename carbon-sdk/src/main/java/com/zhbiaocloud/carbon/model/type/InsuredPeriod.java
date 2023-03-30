/*
 * Copyright (c) 2018-2023. 成都市维斯凡科技有限公司 All rights reserved.
 */

package com.zhbiaocloud.carbon.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保险期间
 *
 * @author jun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuredPeriod {

  /**
   * 保终身
   */
  public static final InsuredPeriod LIFE_LONG = new InsuredPeriod("O");
  /**
   * 无关保险期间
   */
  public static final InsuredPeriod NONE = new InsuredPeriod("N");
  private int value = 0;
  private InsuredPeriodUnit unit = InsuredPeriodUnit.Y;

  @JsonCreator
  public InsuredPeriod(String period) {
    if ("N".equals(period)) {
      this.unit = InsuredPeriodUnit.N;
    } else if ("O".equals(period)) {
      this.unit = InsuredPeriodUnit.O;
      this.value = 106;
    } else {
      this.unit = InsuredPeriodUnit.valueOf(period.substring(period.length() - 1));
      this.value = Integer.parseInt(period.substring(0, period.length() - 1));
    }
  }

  public static InsuredPeriod of(String period) {
    return new InsuredPeriod(period);
  }

  @JsonValue
  @Override
  public String toString() {
    if (unit == InsuredPeriodUnit.N || unit == InsuredPeriodUnit.O) {
      return unit.name();
    }
    return value + unit.name();
  }
}
