package com.parking.estacionamiento.service;

import com.parking.estacionamiento.dto.EstacionamientoDto;
import com.parking.estacionamiento.exception.EntityYaRegistradaException;
import com.parking.estacionamiento.modelo.Estacionamiento;
import com.parking.estacionamiento.modelo.TipoVehiculo;
import com.parking.estacionamiento.repository.EstacionamientoRepository;
import com.parking.estacionamiento.repository.TipoVehiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service

public class EstacionamientoService {
    private static final Logger LOG = LoggerFactory.getLogger(EstacionamientoService.class);

    private EstacionamientoRepository estacionamientoRepository;

    private TipoVehiculoRepository tipoVehiculoRepository;

    @Autowired
    public EstacionamientoService(EstacionamientoRepository estacionamientoRepository, TipoVehiculoRepository tipoVehiculoRepository) {
        this.estacionamientoRepository = estacionamientoRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    private Estacionamiento convertirDtoAEntity(EstacionamientoDto dto) {
        Estacionamiento entity = new Estacionamiento();
        entity.setPlaca(dto.getPlaca());
        return entity;
    }

    private Optional<Estacionamiento> estaRegistradoLaPlaca(String placa) {
        return estacionamientoRepository.findByPlaca(placa);
    }

    /**
     * crear la entrada de un tipo de vehiculo moto,camion,carro anotar la placa y la fecha y hora de entrada al parqueadero,
     * crear la salida de un tipo de vehiculo moto,camion,carro anotar la placa y la fecha y hora de salida del parqueadero,
     * calcular el valor a pagar por el tiempo que estuvo en el parqueadero,
     * validar que no se pueda registrar la misma placa dos veces,
     * validar que no se pueda registrar la salida de un vehiculo que no se encuentra en el parqueadero,
     * si el tipo de vehiculo no se encuentra registrado no se puede registrar
     */

    public void registrar(EstacionamientoDto request) {

        Optional<TipoVehiculo> optionalTipoVehiculo = tipoVehiculoRepository
                .findByTipoAutomovil(request.getTipoAutomovil());

        if (optionalTipoVehiculo.isPresent()) {
            int idTipoVehiculo = optionalTipoVehiculo.get().getId();
            Estacionamiento entity = new Estacionamiento();
            entity.setIdTipoVehiculo(idTipoVehiculo);
            entity.setPlaca(request.getPlaca());
            entity.setFechaHoraEntrada(new Date());
            estacionamientoRepository.save(entity);
        } else {
            throw new EntityYaRegistradaException("Error: No existe el " + request.getTipoAutomovil() + " en la base de datos");
        }

    }

    //registrar salida del vehiculo y mostrar el monto a cancelar por el tiempo que duro en el estacionamiento
    public String registrarSalida(EstacionamientoDto request) {
        String mensajeDelMontoAPagar = "";
        Optional<Estacionamiento> optionalEstacionamiento = estacionamientoRepository.findByPlacaAndFechaHoraSalidaIsNull(request.getPlaca());
        if (optionalEstacionamiento.isPresent()) {
            Estacionamiento entity = optionalEstacionamiento.get();
            entity.setFechaHoraSalida(new Date());

            Date fechaHoraEntrada = entity.getFechaHoraEntrada();
            Date fechaHoraSalida = entity.getFechaHoraSalida();
            estacionamientoRepository.save(entity);
            long milisegundos = fechaHoraSalida.getTime() - fechaHoraEntrada.getTime();
            long minutos = milisegundos / 60000;

            double monto = 0;
            if (minutos <= 60) {
                monto = 5;
            } else if (minutos > 60) {

                monto = 5 + ((minutos - 60) * 0.10);
            }

            mensajeDelMontoAPagar = "Se ha registrado la salidad del vehiculo con placa: " + request.getPlaca().toUpperCase() + ". Su monto a pagar es de: " + monto + "$";
        } else {
            throw new EntityYaRegistradaException("No se encuentra el vehiculo con la placa: " + request.getPlaca().toUpperCase());
        }

        return mensajeDelMontoAPagar;
    }

    //si se encuentra la misma placa en la base de datos no se puede registrar
    public Estacionamiento validarPlaca(EstacionamientoDto request) {

        // Optional<Estacionamiento> optionalEstacionamiento = estaRegistradoLaPlaca(request.getPlaca());
        Optional<Estacionamiento> optionalEstacionamiento = estacionamientoRepository.findByPlacaAndFechaHoraSalidaIsNull(request.getPlaca());
        if (optionalEstacionamiento.isPresent()) {
            LOG.error("La placa ya se encuentra registrada");
            // throw new EntityExistsException("Ya se encuentra registrado el vehiculo con la placa: " + request.getPlaca());
            throw new EntityYaRegistradaException("Ya se encuentra registrado el vehiculo con la placa: " + request.getPlaca().toUpperCase());
        }
        return convertirDtoAEntity(request);

    }


    //validar placa dentro del metodo registrar entrada

    public void registrarEntrada(EstacionamientoDto request) {

        //Estacionamiento estacionamiento =  validarPlaca(request);
        validarPlaca(request);
        registrar(request);//meter contenido de registrar en registrar entrada

        //return placa
    }


}


