package com.haogefeifei.odoo.api.adapter;

import com.haogefeifei.odoo.api.core.EntityParams;
import com.haogefeifei.odoo.api.core.FilterCollection;
import com.haogefeifei.odoo.api.core.OdooAdapter;
import com.haogefeifei.odoo.api.core.Row;
import com.haogefeifei.odoo.api.core.RowCollection;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityAdapter<T> {
    private OdooAdapter odooAdapter;

    public EntityAdapter(OdooAdapter odooAdapter) {
        this.odooAdapter = odooAdapter;
    }

    protected abstract String getKey();

    protected abstract EntityParams buildEntityParameters(T entity);

    protected abstract String[] buildFieldsList();

    protected abstract T transcodeRowToEntity(Row row);

    public Integer createEntity(T entity) {
        return this.odooAdapter.createObject(this.buildEntityParameters(entity).getParams(), this.getKey());
    }

    public T findByField(String properyName, Object value) {
        FilterCollection filter = new FilterCollection();
        filter.add(properyName, "=", value);

        return this.findOne(filter);
    }

    public T findById(int id) {
        return this.findByField("id", id);
    }

    public T findOne(FilterCollection filters) {
        RowCollection results = this.odooAdapter.searchAndReadObject(filters, this.buildFieldsList(), this.getKey());

        if (results.size() == 0) {
            return null;
        } else if (results.size() > 1) {
            throw new RuntimeException("Invalid resultset : expected 1, retreived " + results.size());
        } else {
            return this.transcodeRowToEntity(results.get(0));
        }
    }

    public List<T> find(FilterCollection filters) {
        RowCollection results = this.odooAdapter.searchAndReadObject(filters, this.buildFieldsList(), this.getKey());

        ArrayList<T> entities = new ArrayList<T>();
        for (Row row : results) {
            entities.add(this.transcodeRowToEntity(row));
        }

        return entities;
    }
}
