package testbase.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class RouteModel extends AbstractModel{
    public String id;
    public int departureStationId;
    public Date departureTime;
    public Object arrivalStationId;
    public Date arrivalTime;
    public ArrayList<String> vehicleTypes;
    public int transfersCount;
    public int freeSeatsCount;
    public double priceFrom;
    public double priceTo;
    public double creditPriceFrom;
    public double creditPriceTo;
    public int pricesCount;
    public boolean actionPrice;
    public boolean surcharge;
    public boolean notices;
    public boolean support;
    public boolean nationalTrip;
    public boolean bookable;
    public Object delay;
    public String travelTime;
    public String vehicleStandardKey;
}

