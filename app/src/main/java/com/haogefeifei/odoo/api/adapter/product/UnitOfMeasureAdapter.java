package com.haogefeifei.odoo.api.adapter.product;

import com.haogefeifei.odoo.api.adapter.EntityAdapter;
import com.haogefeifei.odoo.api.core.EntityParams;
import com.haogefeifei.odoo.api.core.OdooAdapter;
import com.haogefeifei.odoo.api.core.Row;
import com.haogefeifei.odoo.api.entity.product.UnitOfMeasure;
import com.haogefeifei.odoo.api.entity.product.UnitOfMeasureCategory;

public class UnitOfMeasureAdapter extends EntityAdapter<UnitOfMeasure> {

	public UnitOfMeasureAdapter(OdooAdapter odooAdapter) {
		super(odooAdapter);
	}

	@Override
	protected String getKey() {
		return "product.uom";
	}

	@Override
	protected EntityParams buildEntityParameters(UnitOfMeasure entity) {
		throw new RuntimeException("Not supported yet");
	}

	@Override
	protected String[] buildFieldsList() {
		return new String[]{
			"name",
			"category_id",
			"factor",
			"rounding",
			"active",
			"uom_type",
		};
	}

	@Override
	protected UnitOfMeasure transcodeRowToEntity(Row row) {
		Object[] category = (Object[])row.get("category_id");
		return new UnitOfMeasure(
			row.getID(), 
			(String)row.get("name"), 
			new UnitOfMeasureCategory((Integer)category[0], (String)category[1]), 
			(Double)row.get("factor"), 
			(Double)row.get("rounding"), 
			(Boolean)row.get("active"), 
			UnitOfMeasure.Type.valueOf((String)row.get("uom_type"))
		);
	}
}
