package com.examen.EjercicioPractico2_FelipeRuiz.config;

import com.examen.EjercicioPractico2_FelipeRuiz.domain.Rol;
import com.examen.EjercicioPractico2_FelipeRuiz.domain.Usuario;
import com.examen.EjercicioPractico2_FelipeRuiz.repository.RolRepository;
import com.examen.EjercicioPractico2_FelipeRuiz.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    public DataInitializer(RolRepository rolRepository, UsuarioRepository usuarioRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        Rol admin = rolRepository.findByNombre("ADMIN").orElseGet(() -> rolRepository.save(new Rol("ADMIN")));
        Rol organizador = rolRepository.findByNombre("ORGANIZADOR").orElseGet(() -> rolRepository.save(new Rol("ORGANIZADOR")));
        Rol cliente = rolRepository.findByNombre("CLIENTE").orElseGet(() -> rolRepository.save(new Rol("CLIENTE")));

        crearOActualizarUsuario("Admin", "admin@email.com", "12345", admin);
        crearOActualizarUsuario("Organizador", "org@email.com", "12345", organizador);
        crearOActualizarUsuario("Cliente", "cliente@email.com", "12345", cliente);
    }

    private void crearOActualizarUsuario(String nombre, String email, String rawPassword, Rol rol) {
        var optional = usuarioRepository.findByEmail(email);
        if (optional.isEmpty()) {
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setEmail(email);
            u.setPassword(rawPassword);
            u.setRol(rol);
            usuarioRepository.save(u);
        }
    }
}
