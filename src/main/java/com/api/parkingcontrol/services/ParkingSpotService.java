package com.api.parkingcontrol.services;

import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    // Service é uma camada INTERMEDIARIA entre o CONTROLER e o REPOSITORIE
    // Quando add algo no BD -> CONTROLLER aciona SERVICE que aciona REPOSITORIE

    // criando ponto de injecao
    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }



}
