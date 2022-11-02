package net.idrok.travel_logs_service.domain.dto;

import java.time.LocalDate;
import java.util.List;

public class TravelReportInPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    private Long totalDistance;
    private List<TravelsOnDay> travelsOnDays;


    public TravelReportInPeriod(LocalDate startDate, LocalDate endDate, List<TravelsOnDay> travelsOnDays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.travelsOnDays = travelsOnDays;
        this.totalDistance = travelsOnDays.stream().mapToLong(TravelsOnDay::getTotalDistance).sum();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TravelsOnDay> getTravelsOnDays() {
        return travelsOnDays;
    }

    public void setTravelsOnDays(List<TravelsOnDay> travelsOnDays) {
        this.travelsOnDays = travelsOnDays;
    }

    public Long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
    }
}
