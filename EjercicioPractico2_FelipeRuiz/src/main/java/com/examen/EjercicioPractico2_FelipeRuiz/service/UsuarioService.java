package com.examen.EjercicioPractico2_FelipeRuiz.service;

import com.examen.EjercicioPractico2_FelipeRuiz.domain.Usuario;
import com.examen.EjercicioPractico2_FelipeRuiz.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> buscarPorRol(String rolNombre) {
        return usuarioRepository.findByRolNombre(rolNombre);
    }

    public Usuario crear(Usuario usuario) {
        Usuario guardado = usuarioRepository.save(usuario);
        try {
            emailService.enviarCorreoBienvenida(guardado.getEmail(), guardado.getNombre());
        } catch (Exception e) {
        }
        return guardado;
    }

    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
