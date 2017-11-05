package com.menglang.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.menglang.crm.service.ICustomerLossService;
import com.menglang.crm.service.ICustomerService;

@Component
public class FindLossCustomerJob {

	@Autowired
	private ICustomerService customerService;
	 /**
     * 每天凌晨2点定期执行
     */
	@Scheduled(cron = "0 0 2 * * ?")
	/*@Scheduled(cron = "0/10 * * * * ?")*/
	public void work() {
		customerService.checkCustomerLoss();
	}

	
}
