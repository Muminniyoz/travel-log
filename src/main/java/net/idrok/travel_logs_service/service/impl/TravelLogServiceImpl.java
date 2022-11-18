package net.idrok.travel_logs_service.service.impl;

import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.domain.dto.TravelLogCriteria;
import net.idrok.travel_logs_service.domain.dto.TravelsOnDay;
import net.idrok.travel_logs_service.domain.dto.TravelReportInPeriod;
import net.idrok.travel_logs_service.repository.TravelLogRepository;
import net.idrok.travel_logs_service.service.TravelLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TravelLogServiceImpl implements TravelLogService {
    private final TravelLogRepository repository;

    public TravelLogServiceImpl(TravelLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TravelLog> getAll() {

        return repository.findAll();
    }

    @Override
    public Page<TravelLog> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public TravelReportInPeriod generateReport(TravelLogCriteria criteria) {
            List<TravelsOnDay> travelsOnDays = repository.generateReport(criteria);
            return new TravelReportInPeriod(criteria.getStartDate(), criteria.getEndDate(), travelsOnDays);
    }


    @Override
    public Optional<TravelLog> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public TravelLog create(TravelLog travelLog) {
        return repository.create(travelLog);
    }

    @Override
    public TravelLog update(TravelLog travelLog) {
        return repository.update(travelLog);
    }

    @Override
    public void delete(TravelLog travelLog) {
        repository.delete(travelLog);
    }

    @Override
    public void deleteById(Long id) {
            repository.deleteById(id);
    }


}
