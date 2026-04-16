package com.examen.EjercicioPractico2_FelipeRuiz.controllers;

import com.examen.EjercicioPractico2_FelipeRuiz.domain.Usuario;
import com.examen.EjercicioPractico2_FelipeRuiz.service.RolService;
import com.examen.EjercicioPractico2_FelipeRuiz.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarTodos());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.listarTodos());
        return "usuarios/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Usuario usuario) {
        Usuario existente = usuarioService.buscarPorId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            usuario.setPassword(existente.getPassword());
        }
        usuarioService.actualizar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "usuarios/detalle";
    }
}
