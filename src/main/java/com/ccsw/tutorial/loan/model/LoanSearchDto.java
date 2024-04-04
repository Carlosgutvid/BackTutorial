package com.ccsw.tutorial.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author CGV
 *
 */

public class LoanSearchDto {

    private PageableRequest pageable;
    private Long gameId;
    private Long clientId;
    private LocalDate date;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LoanSearchDto [pageable=" + pageable + ", gameId=" + gameId + ", clientId=" + clientId + ", date="
                + date + "]";
    }

}
