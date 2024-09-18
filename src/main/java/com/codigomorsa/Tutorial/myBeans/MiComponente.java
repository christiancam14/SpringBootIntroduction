package com.codigomorsa.Tutorial.myBeans;

import org.springframework.stereotype.Component;

@Component
public class MiComponente {
    public void saludarDesdeComponente() {
        System.out.println("Hola desde el componente");
    }
}
