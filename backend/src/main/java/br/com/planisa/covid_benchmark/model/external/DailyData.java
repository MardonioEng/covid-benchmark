package br.com.planisa.covid_benchmark.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyData {

    private Long total;

    @JsonProperty("new")
    private Long newCount;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getNewCount() {
        return newCount;
    }

    public void setNewCount(Long newCount) {
        this.newCount = newCount;
    }

}
