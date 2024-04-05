package com.example.profile.profile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/profile")
class ProfileController {
    @Autowired
    private ProfileRepository repository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Profile n = new Profile();
        n.setName(name);
        n.setEmail(email);
        repository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Profile> getAllUsers() {
        // This returns a JSON or XML with the users
        return repository.findAll();
    }

    @GetMapping(path = "/{userID}")
    public @ResponseBody Optional<Profile> getProfileById(@PathVariable Integer userID)
            throws IllegalArgumentException {
        // This returns a JSON or XML with the users
        return repository.findById(Long.valueOf(userID));
    }
}