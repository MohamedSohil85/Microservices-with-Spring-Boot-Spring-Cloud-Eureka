package com.example.doctorservice.doctorservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String doctorName;
    private String address;
    private String hospitalName;
    private String field;

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Doctor{");
        sb.append("id=").append(id);
        sb.append(", doctorName='").append(doctorName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", hospitalName='").append(hospitalName).append('\'');
        sb.append(", field='").append(field).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
