package com.examen.EjercicioPractico2_FelipeRuiz.controllers;

import com.examen.EjercicioPractico2_FelipeRuiz.service.EventoService;
import com.examen.EjercicioPractico2_FelipeRuiz.service.UsuarioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final EventoService eventoService;
    private final UsuarioService usuarioService;

    public ConsultaController(EventoService eventoService, UsuarioService usuarioService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String consultas(Model model) {
        model.addAttribute("eventosActivos", eventoService.contarEventosActivos());
        return "consultas/index";
    }

    @GetMapping("/eventos-por-estado")
    public String eventosPorEstado(@RequestParam(required = false) Boolean activo, Model model) {
        if (activo != null) {
            model.addAttribute("eventos", eventoService.buscarPorEstado(activo));
            model.addAttribute("filtroActivo", activo);
        }
        model.addAttribute("eventosActivos", eventoService.contarEventosActivos());
        return "consultas/eventos-estado";
    }

    @GetMapping("/eventos-por-fechas")
    public String eventosPorFechas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            Model model) {
        if (inicio != null && fin != null) {
            model.addAttribute("eventos", eventoService.buscarPorRangoFechas(inicio, fin));
            model.addAttribute("inicio", inicio);
            model.addAttribute("fin", fin);
        }
        model.addAttribute("eventosActivos", eventoService.contarEventosActivos());
        return "consultas/eventos-fechas";
    }

    @GetMapping("/eventos-por-nombre")
    public String eventosPorNombre(@RequestParam(required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("eventos", eventoService.buscarPorNombre(nombre));
            model.addAttribute("nombreBusqueda", nombre);
        }
        model.addAttribute("eventosActivos", eventoService.contarEventosActivos());
        return "consultas/eventos-nombre";
    }

    @GetMapping("/usuarios-por-rol")
    public String usuariosPorRol(@RequestParam(required = false) String rol, Model model) {
        if (rol != null && !rol.isEmpty()) {
            model.addAttribute("usuarios", usuarioService.buscarPorRol(rol));
            model.addAttribute("rolBusqueda", rol);
        }
        model.addAttribute("eventosActivos", eventoService.contarEventosActivos());
        return "consultas/usuarios-rol";
    }
}
