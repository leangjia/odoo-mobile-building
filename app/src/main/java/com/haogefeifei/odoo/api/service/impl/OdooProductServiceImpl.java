package com.haogefeifei.odoo.api.service.impl;

import com.haogefeifei.odoo.api.adapter.product.ProductAdapter;
import com.haogefeifei.odoo.api.entity.product.Product;
import com.haogefeifei.odoo.api.service.OdooProductService;

public class OdooProductServiceImpl implements OdooProductService {
	private ProductAdapter productAdapter;
	
	public OdooProductServiceImpl(ProductAdapter productAdapter) {
		this.productAdapter = productAdapter;
	}
	
	@Override
	public Product findProduct(String ean13) {
		return this.productAdapter.findByField("ean13", ean13);
	}

}
