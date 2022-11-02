package net.idrok.travel_logs_service.domain.dto;

import net.idrok.travel_logs_service.domain.TravelLog;

import java.time.LocalDate;
import java.util.List;

public class TravelsOnDay {
    private LocalDate travelDate;
    private Integer count;
    private Long totalDistance;

    private List<TravelLog> travelLogs;



    public TravelsOnDay() {
    }


    public TravelsOnDay(LocalDate travelDate, List<TravelLog> travelLogs) {
        this.travelDate = travelDate;
        this.travelLogs = travelLogs;
        this.count = travelLogs.size();
        this.totalDistance = travelLogs.stream().mapToLong(tr->tr.getEndingOdometer() - tr.getStartingOdometer()).sum();
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<TravelLog> getTravelLogs() {
        return travelLogs;
    }

    public void setTravelLogs(List<TravelLog> travelLogs) {
        this.travelLogs = travelLogs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
