package com.codigomorsa.Tutorial.servicios;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.codigomorsa.Tutorial.TutorialApplication;
import com.codigomorsa.Tutorial.models.Producto;

// Anotación para convertir en servicio singleton inyectable de SpringBoot
// @Service
public class OrderService implements IOrderService {

    @Value("${misurls.database}")
    private String databaseUrl;

    private Logger logger = LoggerFactory.getLogger(TutorialApplication.class);

    public void saveOrder(List<Producto> productos) {
        System.out.println("Guardando en la base de datos..., la url: " + databaseUrl);

        productos.forEach(producto -> logger.debug("El nombre del producto: {}", producto.nombre));

    }

}