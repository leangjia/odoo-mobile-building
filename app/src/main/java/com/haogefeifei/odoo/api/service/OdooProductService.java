package com.haogefeifei.odoo.api.service;

import com.haogefeifei.odoo.api.entity.product.Product;

public interface OdooProductService {

	public Product findProduct(String ean13);
}
