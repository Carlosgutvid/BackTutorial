package com.ccsw.tutorial.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author CGV
 *
 */

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "initDate", nullable = false)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    /**
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return startDate
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * @param startDate new value of {@link #getStartDate}.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return endDate
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * @param endDate new value of {@link #getEndDate}.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
