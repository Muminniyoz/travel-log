package net.idrok.travel_logs_service.service;


import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.domain.dto.TravelLogCriteria;
import net.idrok.travel_logs_service.domain.dto.TravelReportInPeriod;
import net.idrok.travel_logs_service.service.util.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelLogService extends CrudService<TravelLog, Long> {
    public Page<TravelLog> getAll(Pageable pageable);

    TravelReportInPeriod generateReport(TravelLogCriteria criteria);
}

