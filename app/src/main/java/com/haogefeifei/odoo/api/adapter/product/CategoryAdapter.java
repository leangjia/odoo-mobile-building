package com.haogefeifei.odoo.api.adapter.product;

import com.haogefeifei.odoo.api.adapter.EntityAdapter;
import com.haogefeifei.odoo.api.core.EntityParams;
import com.haogefeifei.odoo.api.core.OdooAdapter;
import com.haogefeifei.odoo.api.core.Row;
import com.haogefeifei.odoo.api.entity.product.Category;

public class CategoryAdapter extends EntityAdapter<Category> {

	public CategoryAdapter(OdooAdapter odooAdapter) {
		super(odooAdapter);
	}

	@Override
	protected String getKey() {
		return "product.category";
	}

	@Override
	protected EntityParams buildEntityParameters(Category entity) {
		throw new RuntimeException("Not supported yet.");
	}

	@Override
	protected String[] buildFieldsList() {
		return new String[]{
			"name",
			"complete_name",
			"parent_id",
			"sequence",
			"type"
		};
	}

	@Override
	protected Category transcodeRowToEntity(Row row) {
		return new Category(
			row.getID(), 
			(String)row.get("name"), 
			(String)row.get("complete_name"), 
			new Category((Integer)row.get("parent_id"), null, null, null, null, null), 
			(Integer)row.get("sequence"), 
			Category.Type.valueOf((String)row.get("type"))
		);
	}
}
