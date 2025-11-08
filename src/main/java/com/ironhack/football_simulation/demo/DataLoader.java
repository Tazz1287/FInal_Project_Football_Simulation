package com.ironhack.football_simulation.demo;


import com.ironhack.football_simulation.model.Role;
import com.ironhack.football_simulation.model.User;
import com.ironhack.football_simulation.service.RoleService;
import com.ironhack.football_simulation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));

        userService.saveUser(new User("Murad Ibrahim", "murad", "1234"));
        userService.saveUser(new User("Oskar Baeckstroem", "oskar", "1234"));
        userService.saveUser(new User("Karolina", "karolina", "1234"));
        userService.saveUser(new User("Antonio", "antonio", "1234"));

        roleService.addRoleToUser("murad", "ROLE_USER");
        roleService.addRoleToUser("oskar", "ROLE_ADMIN");
        roleService.addRoleToUser("karolina", "ROLE_USER");
        roleService.addRoleToUser("antonio", "ROLE_ADMIN");

    }
}
