// Copyright 2011 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.adwords.jaxws.extensions.report.model.entities;

import com.google.api.ads.adwords.jaxws.extensions.report.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.jaxws.extensions.report.model.csv.annotation.CsvReport;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.lib.jaxb.v201309.ReportDefinitionReportType;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Specific report class for ReportBudget
 *
 * @author tarjei@google.com (Tarjei Vassbotn)
 */
@Entity
@Table(name = "AW_ReportBudget")
@CsvReport(value = ReportDefinitionReportType.BUDGET_PERFORMANCE_REPORT)
public class ReportBudget extends ReportBase {

  @Column(name = "AMOUNT")
  @CsvField(value = "Budget", reportField = "Amount")
  private BigDecimal amount;

  @Column(name = "BUDGET_ID")
  @CsvField(value = "Budget ID", reportField = "BudgetId")
  private Long budgetId;

  @Column(name = "BUDGET_NAME")
  @CsvField(value = "Budget Name", reportField = "BudgetName")
  private String budgetName;

  @Column(name = "BUDGET_STATUS")
  @CsvField(value = "Budget state", reportField = "BudgetStatus")
  private String budgetStatus;

  @Column(name = "BUDGET_REFERENCE_COUNT")
  @CsvField(value = "# Campaigns", reportField = "BudgetReferenceCount")
  private Long budgetReferenceCount;

  @Column(name = "IS_BUDGET_EXPLICITLY_SHARED")
  @CsvField(value = "Explicitly shared", reportField = "IsBudgetExplicitlyShared")
  private boolean budgetExplicitlyShared;

  @Column(name = "PERIOD")
  @CsvField(value = "Budget period", reportField = "Period")
  private String period;

  /**
   * Hibernate needs an empty constructor
   */
  public ReportBudget() {}

  public ReportBudget(Long topAccountId, Long accountId) {
    this.topAccountId = topAccountId;
    this.accountId = accountId;
  }

  @Override
  public void setId() {
    // Generating unique _id after having date and accountId
    this._id = this.getBudgetId().toString();

    // Adding extra fields for unique ID
    if (this.getPeriod() != null && this.getPeriod().length() > 0) {
      this._id += "-" + this.getPeriod();
    }
  }

  /**
   * @return the amount
   */
  public String getAmount() {
    return BigDecimalUtil.formatAsReadable(amount);
  }

  public BigDecimal getAmountBigDecimal() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(String amount) {
    amount = amount.replaceAll("--", "0");
    this.amount = new BigDecimal(amount.replaceAll("\\s|%|>|<", ""));
  }

  /**
   * @return the budgetId
   */
  public Long getBudgetId() {
    return budgetId;
  }

  /**
   * @param budgetId the budgetId to set
   */
  public void setBudgetId(Long budgetId) {
    this.budgetId = budgetId;
  }

  /**
   * @return the budgetName
   */
  public String getBudgetName() {
    return budgetName;
  }

  /**
   * @param budgetName the budgetName to set
   */
  public void setBudgetName(String budgetName) {
    this.budgetName = budgetName;
  }

  /**
   * @return the budgetStatus
   */
  public String getBudgetStatus() {
    return budgetStatus;
  }

  /**
   * @param budgetStatus the budgetStatus to set
   */
  public void setBudgetStatus(String budgetStatus) {
    this.budgetStatus = budgetStatus;
  }

  /**
   * @return the budgetReferenceCount
   */
  public Long getBudgetReferenceCount() {
    return budgetReferenceCount;
  }

  /**
   * @param budgetReferenceCount the budgetReferenceCount to set
   */
  public void setBudgetReferenceCount(Long budgetReferenceCount) {
    this.budgetReferenceCount = budgetReferenceCount;
  }

  /**
   * @return the budgetExplicitlyShared
   */
  public boolean isBudgetExplicitlyShared() {
    return budgetExplicitlyShared;
  }

  /**
   * @param budgetExplicitlyShared set the budgetExplicitlyShared
   */
  public void setBudgetExplicitlyShared(boolean budgetExplicitlyShared) {
    this.budgetExplicitlyShared = budgetExplicitlyShared;
  }

  public void setBudgetExplicitlyShared(String budgetExplicitlyShared) {
    this.budgetExplicitlyShared = Boolean.parseBoolean(budgetExplicitlyShared);
  }

  /**
   * @return the period
   */
  public String getPeriod() {
    return period;
  }

  /**
   * @param period the period to set
   */
  public void setPeriod(String period) {
    this.period = period;
  }
}
