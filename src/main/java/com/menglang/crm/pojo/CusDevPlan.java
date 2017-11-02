package com.menglang.crm.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CusDevPlan {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cus_dev_plan.id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cus_dev_plan.sale_chance_id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    private Integer saleChanceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cus_dev_plan.plan_item
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    private String planItem;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cus_dev_plan.plan_date
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")
    private Date planDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cus_dev_plan.exe_affect
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    private String exeAffect;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cus_dev_plan.id
     *
     * @return the value of cus_dev_plan.id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cus_dev_plan.id
     *
     * @param id the value for cus_dev_plan.id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cus_dev_plan.sale_chance_id
     *
     * @return the value of cus_dev_plan.sale_chance_id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public Integer getSaleChanceId() {
        return saleChanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cus_dev_plan.sale_chance_id
     *
     * @param saleChanceId the value for cus_dev_plan.sale_chance_id
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public void setSaleChanceId(Integer saleChanceId) {
        this.saleChanceId = saleChanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cus_dev_plan.plan_item
     *
     * @return the value of cus_dev_plan.plan_item
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public String getPlanItem() {
        return planItem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cus_dev_plan.plan_item
     *
     * @param planItem the value for cus_dev_plan.plan_item
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public void setPlanItem(String planItem) {
        this.planItem = planItem == null ? null : planItem.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cus_dev_plan.plan_date
     *
     * @return the value of cus_dev_plan.plan_date
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cus_dev_plan.plan_date
     *
     * @param planDate the value for cus_dev_plan.plan_date
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cus_dev_plan.exe_affect
     *
     * @return the value of cus_dev_plan.exe_affect
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public String getExeAffect() {
        return exeAffect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cus_dev_plan.exe_affect
     *
     * @param exeAffect the value for cus_dev_plan.exe_affect
     *
     * @mbggenerated Mon Oct 30 13:41:51 CST 2017
     */
    public void setExeAffect(String exeAffect) {
        this.exeAffect = exeAffect == null ? null : exeAffect.trim();
    }

	@Override
	public String toString() {
		return "CusDevPlan [id=" + id + ", saleChanceId=" + saleChanceId + ", planItem=" + planItem + ", planDate="
				+ planDate + ", exeAffect=" + exeAffect + "]";
	}
    
    
}