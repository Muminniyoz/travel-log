package net.idrok.travel_logs_service.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class TravelLog {
    private Long id;
    @NotNull
    private LocalDate travelDate;
    @NotNull
    @Size(min=4, max = 30)
    private String vehicleRegNum;
    @NotNull
    private String vehicleOwner;
    @NotNull
    private Integer startingOdometer;
    @NotNull
    private Integer endingOdometer;
    @NotNull
    private String travelRoute;

    private String description;



    public TravelLog() {
    }

    public TravelLog(Long id) {
        this.id = id;
    }

    public TravelLog(Long id, LocalDate travelDate, String vehicleRegNum, String vehicleOwner, Integer startingOdometer,
                     Integer endingOdometer, String travelRoute, String description) {
        this.id = id;
        this.travelDate = travelDate;
        this.vehicleRegNum = vehicleRegNum;
        this.vehicleOwner = vehicleOwner;
        this.startingOdometer = startingOdometer;
        this.endingOdometer = endingOdometer;
        this.travelRoute = travelRoute;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getVehicleRegNum() {
        return vehicleRegNum;
    }

    public void setVehicleRegNum(String vehicleRegNum) {
        this.vehicleRegNum = vehicleRegNum;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public Integer getStartingOdometer() {
        return startingOdometer;
    }

    public void setStartingOdometer(Integer startingOdometer) {
        this.startingOdometer = startingOdometer;
    }

    public Integer getEndingOdometer() {
        return endingOdometer;
    }

    public void setEndingOdometer(Integer endingOdometer) {
        this.endingOdometer = endingOdometer;
    }

    public String getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(String travelRoute) {
        this.travelRoute = travelRoute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelLog travelLog = (TravelLog) o;
        return Objects.equals(id, travelLog.id) && Objects.equals(travelDate, travelLog.travelDate) && Objects.equals(vehicleRegNum, travelLog.vehicleRegNum) && Objects.equals(vehicleOwner, travelLog.vehicleOwner) && Objects.equals(startingOdometer, travelLog.startingOdometer) && Objects.equals(endingOdometer, travelLog.endingOdometer) && Objects.equals(travelRoute, travelLog.travelRoute) && Objects.equals(description, travelLog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travelDate, vehicleRegNum, vehicleOwner, startingOdometer, endingOdometer, travelRoute, description);
    }

}