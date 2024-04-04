package com.ccsw.tutorial.loan;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    @Override
    public Loan get(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Loan> find(LoanSearchDto dto) {
        LoanSpecification gameSpec = new LoanSpecification(new SearchCriteria("game.id", ":", dto.getGameId()));
        LoanSpecification clientSpec = new LoanSpecification(new SearchCriteria("client.id", ":", dto.getClientId()));
        LoanSpecification startDateSpec = new LoanSpecification(new SearchCriteria("initDate", ">=", dto.getDate()));
        LoanSpecification endDateSpec = new LoanSpecification(new SearchCriteria("endDate", "<=", dto.getDate()));

        Specification<Loan> spec = Specification.where(gameSpec).and(clientSpec).and(startDateSpec).and(endDateSpec);
        return loanRepository.findAll(spec, dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, LoanDto dto) throws Exception {
        try {
            validateLoan(dto);
            Loan loan = new Loan();
            BeanUtils.copyProperties(dto, loan, "id", "game", "client");
            loan.setGame(gameService.getGame(dto.getGame().getId()));
            loan.setClient(clientService.get(dto.getClient().getId()));
            loanRepository.save(loan);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    private void validateLoan(LoanDto dto) throws Exception {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new Exception();
        }
        long daysBetween = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (daysBetween > 14) {
            throw new Exception();
        }
        if (loanRepository.existsByGameIdAndEndDateBetween(dto.getGame().getId(), dto.getStartDate(),
                dto.getEndDate())) {
            throw new Exception();
        }
        long loanCount = loanRepository.countByClientIdAndEndDateBetween(dto.getClient().getId(), dto.getStartDate(),
                dto.getEndDate());
        if (loanCount >= 2) {
            throw new Exception();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }
}
