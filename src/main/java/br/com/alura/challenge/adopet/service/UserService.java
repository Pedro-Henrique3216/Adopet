package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.model.User;
import br.com.alura.challenge.adopet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }
}
