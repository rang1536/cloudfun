package com.cloudfun.www.pay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayRestController {

	/*
	@RequestMapping(value="/verified-product", method = RequestMethod.POST)
	public Map<String, Object> verifiedProduct(){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> purchase_units = new HashMap<String, Object>();
		Map<String, Object> amount = new HashMap<String, Object>();

		amount.put("value", "0.01");
		amount.put("currency", "USD");

		purchase_units.put("description", "paypal test product");
		purchase_units.put("amount", amount);

		map.put("valid", true);
		map.put("purchase_units", purchase_units);

        return map;
    }
	*/

	private static final String BASE_URL ="https://api-m.sandbox.paypal.com"; //https://api-m.paypal.com
	private static final String APP_SECRET = "";
	private static final String CLIENT_ID = "";
	private static final String ACCESS_TOKEN = "";

	@RequestMapping(value="/verified-product", method = RequestMethod.POST)
	public Map<String, Object> verifiedProduct(){
		String orderUrl = BASE_URL+"/v2/checkout/orders";


        return null;
    }
}
