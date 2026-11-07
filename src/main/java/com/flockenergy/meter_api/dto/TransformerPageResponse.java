
package com.flockenergy.meter_api.dto;

import java.util.List;

public class TransformerPageResponse {

    private List<TransformerDto> data;
    private int total;
    private int page;
    private int pageSize;

    public List<TransformerDto> getData() {
        return data;
    }

    public void setData(List<TransformerDto> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
