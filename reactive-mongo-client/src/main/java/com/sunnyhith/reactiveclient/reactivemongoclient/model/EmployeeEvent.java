package com.sunnyhith.reactiveclient.reactivemongoclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class EmployeeEvent {
    @Override
    public String toString() {
        return "EmployeeEvent{" +
                "employee=" + employee +
                ", date=" + date +
                '}';
    }

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public EmployeeEvent(Employee employee, Date date) {
        this.employee = employee;
        this.date = date;
    }
}
