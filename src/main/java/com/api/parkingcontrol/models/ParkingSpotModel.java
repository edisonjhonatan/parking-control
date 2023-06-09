package com.api.parkingcontrol.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // UUID -> evita IDs sequenciais, são identificadores distribuidos proprios para arquiteturas distribuidas

    // A fim de estudar os relacionamentos JPA
    // criar no futuro uma modelo carro e separar
    // desta classe, criando no BD uma relacao 1:1

    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;

    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;

    @Column(nullable = false, length = 70)
    private String brandCar;

    @Column(nullable = false, length = 70)
    private String modelCar;

    @Column(nullable = false, length = 70)
    private String colorCar;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 130)
    private String responsibleName;

    @Column(nullable = false, length = 30)
    private String apartment;

    @Column(nullable = false, length = 30)
    private String block;

    public UUID getId() {
        return id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
