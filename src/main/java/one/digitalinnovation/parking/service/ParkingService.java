package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
//import java.util.stream.Collectors;

@Service
public class ParkingService {
    //private static Map<String, Parking> parkingMap = new HashMap<>();

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    /*
    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "USD-6349", "RJ", "DUSTER", "PRETO");
        Parking parking1 = new Parking(id1, "NFA-2687", "SP", "PALIO ADVENTURE", "VERMELHO");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
     }*/

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
        //return parkingMap.values().stream().collect(Collectors.toList());
    }
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow( () ->
                new ParkingNotFoundException(id));
        /*Parking parking = parkingMap.get(id);
        if(parking == null) {
            throw new ParkingNotFoundException(id);
        }

        return parking;*/
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        //spring does this automatically for us
        //try
            //open connection
            //transaction.begin

        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        //parkingMap.put(uuid, parkingCreate);

        //transaction.commit
        //catch
            //transaction;rollback
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
        //parkingMap.remove(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking byIdParking = findById(id);
        byIdParking.setColor(parkingCreate.getColor());
        byIdParking.setState(parkingCreate.getState());
        byIdParking.setModel(parkingCreate.getModel());
        byIdParking.setLicense(parkingCreate.getLicense());

        parkingRepository.save(byIdParking);
        //parkingMap.replace(id, byIdParking);

        return byIdParking;
    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
