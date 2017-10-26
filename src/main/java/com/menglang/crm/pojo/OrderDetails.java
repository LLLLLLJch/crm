package com.menglang.crm.pojo;

public class OrderDetails {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.order_id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private Integer orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.goods_name
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private String goodsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.goods_num
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private Integer goodsNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.unit
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.price
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private Float price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.sum
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    private Float sum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.id
     *
     * @return the value of order_details.id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.id
     *
     * @param id the value for order_details.id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.order_id
     *
     * @return the value of order_details.order_id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.order_id
     *
     * @param orderId the value for order_details.order_id
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.goods_name
     *
     * @return the value of order_details.goods_name
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.goods_name
     *
     * @param goodsName the value for order_details.goods_name
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.goods_num
     *
     * @return the value of order_details.goods_num
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.goods_num
     *
     * @param goodsNum the value for order_details.goods_num
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.unit
     *
     * @return the value of order_details.unit
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.unit
     *
     * @param unit the value for order_details.unit
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.price
     *
     * @return the value of order_details.price
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.price
     *
     * @param price the value for order_details.price
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.sum
     *
     * @return the value of order_details.sum
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public Float getSum() {
        return sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.sum
     *
     * @param sum the value for order_details.sum
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    public void setSum(Float sum) {
        this.sum = sum;
    }
}