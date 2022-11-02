package net.idrok.travel_logs_service.repository;

import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.domain.dto.TravelsOnDay;
import net.idrok.travel_logs_service.repository.util.PageableRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelLogRepository extends PageableRepository<TravelLog, Long> {

    List<TravelsOnDay> generateReport(LocalDate startDate, LocalDate endDate, String vehicleRegNum, String vehicleOwner);

}

