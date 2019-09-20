package com.cms.manager.model.vo;

import java.io.Serializable;
import java.util.List;

public class EUDataGridResult<T> implements Serializable {

	private static final long serialVersionUID = 3642397246238416093L;

	private long total;

	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}