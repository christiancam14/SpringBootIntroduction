package com.codigomorsa.Tutorial;

import com.codigomorsa.Tutorial.models.Libro;
import com.codigomorsa.Tutorial.models.Producto;
import com.codigomorsa.Tutorial.models.UserData;
import com.codigomorsa.Tutorial.myBeans.MiBean;
import com.codigomorsa.Tutorial.myBeans.MiComponente;
import com.codigomorsa.Tutorial.servicios.IOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Rutas {

    // private OrderService orderservice = new OrderService();
    private IOrderService orderservice;
    private MiBean miBean;

    // Constructor /* Util para unit test */ Inyección de depdencias por declaración
    @Autowired
    private MiComponente miComponente;

    // Constructor /* Util para unit test */ Inyección de depdencias por constructor
    public Rutas(IOrderService orderService, MiBean miBean, MiComponente miComponente) {
        this.orderservice = orderService;
        this.miBean = miBean;
        this.miComponente = miComponente;
    }

    private Logger logger = LoggerFactory.getLogger(TutorialApplication.class);

    @GetMapping("/hola")
    String miPrimeraRuta() {
        return "Hola mundo desde spring controller :)";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Leyendo el libro id:  " + id + ", editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}")
    String leerLibro2(@PathVariable int id, @RequestParam String params) {
        return "Leyendo el libro id:  " + id + ", params: " + params;
    }

    @GetMapping("/libro3/{id}")
    String leerLibro3(@PathVariable int id, @RequestParam Map<String, String> allParams) {
        return "Leyendo el libro id: " + id + ", params: " + allParams.toString();
    }

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Map<String, Object> libro) {
        libro.keySet().forEach(llave -> {
            logger.debug("Llave {} valor {}", llave, libro.get(llave));
        });

        return "Libro guardado: " + libro.toString();
    }

    @PostMapping("/libro2")
    String guardarLibro2(@RequestBody(required = true) Libro libro) {
        logger.debug("Llave {} valor {}", libro.nombre, libro.editorial);

        return "Libro guardado: ";
    }

    @GetMapping("/saludar")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Fue movida a otra dependencia")
    String miSegundaRutaConStatus() {
        return "Validar status";
    }

    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimales(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, vaca, oveja, gallina");
        } else if (lugar.equals("selva")) {
            return ResponseEntity.status(HttpStatus.OK).body("Mono, gorilla, puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lugar no válido");
        }
    }

    @GetMapping("/calcular/{numero}")
    public int getCalculo(@PathVariable int numero) {
        throw new NullPointerException("Esta excepción ocurrió porque #### no relevante para el cliente");
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"Mary\"}");
    }

    @GetMapping("/userData/v2")
    public Map<String, Map<String, Object>> getUserDataV2() {
        return Map.of("user", Map.of("name", "mary", "age", 25));
    }

    @GetMapping("/userData/v3")
    public UserData getUserDataV3() {
        var userData = new UserData("Mary", 25, "Calle 123");
        return userData;
    }

    @PostMapping("/order")
    public String crearOrden(@RequestBody List<Producto> products) {
        orderservice.saveOrder(products);
        return "Productos guardados";
    }

    @GetMapping("/mibean")
    public String saludarDesdeBean() {
        miBean.saludar();
        return "Completado";
    }

    @GetMapping("/micomponente")
    public String saludarDesdeComponente() {
        miComponente.saludarDesdeComponente();
        return "Completado";
    }

}
