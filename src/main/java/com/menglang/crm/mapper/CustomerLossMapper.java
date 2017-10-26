package com.menglang.crm.mapper;

import com.menglang.crm.pojo.CustomerLoss;
import com.menglang.crm.pojo.CustomerLossExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerLossMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int countByExample(CustomerLossExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int deleteByExample(CustomerLossExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int insert(CustomerLoss record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int insertSelective(CustomerLoss record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    List<CustomerLoss> selectByExample(CustomerLossExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    CustomerLoss selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int updateByExampleSelective(@Param("record") CustomerLoss record, @Param("example") CustomerLossExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int updateByExample(@Param("record") CustomerLoss record, @Param("example") CustomerLossExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int updateByPrimaryKeySelective(CustomerLoss record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_loss
     *
     * @mbggenerated Thu Oct 26 10:53:56 CST 2017
     */
    int updateByPrimaryKey(CustomerLoss record);
}