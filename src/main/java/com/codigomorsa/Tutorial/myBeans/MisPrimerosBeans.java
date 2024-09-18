package com.codigomorsa.Tutorial.myBeans;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codigomorsa.Tutorial.models.Producto;
import com.codigomorsa.Tutorial.servicios.IOrderService;
import com.codigomorsa.Tutorial.servicios.OrderService;

@Configuration
public class MisPrimerosBeans {

    @Bean
    public MiBean crearMiBean() {
        return new MiBean();
    }

    @Bean
    public IOrderService instanciarOrderService() {
        boolean esProduccion = true;

        if (esProduccion) {
            return new OrderService();
        }
        {
            return new IOrderService() {
                @Override
                public void saveOrder(List<Producto> productos) {
                    System.out.println("Guardando en base de datos dummy (local)");
                }
            };
        }
    }

}
