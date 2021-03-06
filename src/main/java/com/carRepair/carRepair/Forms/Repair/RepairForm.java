package com.carRepair.carRepair.Forms.Repair;

import com.carRepair.carRepair.Domain.Repair;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class RepairForm {

    private static final String PLATE_PATTERN = "^[a-zA-Z]{3}-[1-9]{4}$";
    private static final String STATUS_PATTERN = "^[012]$";
   // private static final String TYPE_PATTERN = "^[\btrue\b\bfalse\b]$";
    private static final String COST_PATTERN = "^[0-9]+[.]{1}[0-9]+$";
    private static final String DESC_PATTERN = "^.+$";
    private static final String TYPE_PATTERN = "^.+$";

    private String repairId;

    @NotNull(message = "{create-repair.plate.null}")
    @Pattern(regexp = PLATE_PATTERN, message = "{create-repair.plate.invalid}")
    private String plate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairDate;

    @NotNull(message = "{create-repair.status.null}")
    @Pattern(regexp = STATUS_PATTERN, message = "{create-repair.status.invalid}")
    private String status;

    @NotNull(message = "{create-repair.description.null}")
    @Pattern(regexp = DESC_PATTERN, message = "{create-repair.description.invalid}")
    @Size(max = 255, message = "more than 255 chars")
    private String description;

    @NotNull(message = "{create-repair.type.null}")
    @Pattern(regexp = TYPE_PATTERN, message = "{create-repair.type.invalid}")
    private String type;

    @NotNull(message = "{create-repair.cost.null}")
    @Pattern(regexp = COST_PATTERN, message = "{create-repair.cost.invalid}")
    private String cost;

    public RepairForm() {
    }

    //TODO repairform converter

    public RepairForm(Repair repair) {
        this.repairId = String.valueOf(repair.getRepairId());
        this.plate = repair.getVehicle().getPlate();
        this.repairDate = repair.getrepairDate();
        this.status = String.valueOf(repair.getStatus());
        this.description = repair.getDescription();
        this.type = String.valueOf(repair.getType());
        this.cost = String.valueOf(repair.getCost());
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String vat) {
        this.plate = vat;
    }

    public LocalDateTime getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDateTime date) {
        this.repairDate = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
