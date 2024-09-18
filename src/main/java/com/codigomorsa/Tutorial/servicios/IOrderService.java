package com.codigomorsa.Tutorial.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codigomorsa.Tutorial.models.Producto;

@Service
public interface IOrderService {

    public void saveOrder(List<Producto> productos);

}
