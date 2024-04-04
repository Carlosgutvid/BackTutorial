package com.ccsw.tutorial.loan;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.loan.model.Loan;

/**
 * @author CGV
 *
 */
public interface LoanRepository extends CrudRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

    /**
     * Método para recuperar un listado paginado de {@link Loan}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Loan}
     */
    Page<Loan> findAll(Pageable pageable);

    /**
     * @param gameId    ID del juego.
     * @param startDate Fecha de inicio del rango.
     * @param endDate   Fecha de finalización del rango.
     * @return True si existe un préstamo, false de lo contrario.
     */
    boolean existsByGameIdAndEndDateBetween(Long gameId, LocalDate startDate, LocalDate endDate);

    /**
     * @param clientId  ID del cliente.
     * @param startDate Fecha de inicio del rango.
     * @param endDate   Fecha de finalización del rango.
     * @return Número de préstamos realizados por el cliente en el rango
     *         especificado.
     */
    long countByClientIdAndEndDateBetween(Long clientId, LocalDate startDate, LocalDate endDate);

}
