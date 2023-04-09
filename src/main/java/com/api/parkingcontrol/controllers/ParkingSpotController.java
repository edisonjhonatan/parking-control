package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import java.awt.List;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;
    // criando ponto de injecao -----------------

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }
    // ------------------------------------------


    // criando método POST ----------------------
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){

        // verifica se a placa do carro já existe
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        // verifica se a vaga já esta em uso
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        // verifica se já há uma vaga registrada para este apartamento/bloco
        if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
            return status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for shit apartmente/block!");
        }

        var parkingSpotModel = new ParkingSpotModel(); // antigamente seria: ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // convertendo Dot em Model
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); // setando a data automaticamente

        return status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }
    // ------------------------------------------

    // criando método GET ALL --------------------
    @GetMapping
    public ResponseEntity<java.util.List<ParkingSpotModel>> getAllParkingSpots() {
        return status(HttpStatus.OK).body(parkingSpotService.findAll());
    }
    // ------------------------------------------

    // criando método GET ONE -------------------
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()) {
            return status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        return status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
    // ------------------------------------------

    // criando método DELETE- -------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }

    // ------------------------------------------

    // criando método PUT -----------------------
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }
    // ------------------------------------------
}

