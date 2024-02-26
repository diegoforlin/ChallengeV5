package com.challengescrd.challenge.service;

import com.challengescrd.challenge.address.Address;
import com.challengescrd.challenge.entities.AddressRepository;
import com.challengescrd.challenge.entities.User;
import com.challengescrd.challenge.entities.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }
    @Transactional
    public ResponseEntity<User> saveUser(User user) {
        User newUser = userRepository.save(user);
        user.getAddresses().get(0).setUser(newUser);
        Address newAddress = addressRepository.save(user.getAddresses().get(0));
        return ResponseEntity.ok(newUser);
    }

    @Transactional
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @Transactional
    public ResponseEntity<User> updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        existingUser.setName(updatedUser.getName());
        existingUser.setCellphone(updatedUser.getCellphone());
        existingUser.setAddresses(updatedUser.getAddresses());
        User savedEntity = userRepository.save(existingUser);
        return ResponseEntity.ok(savedEntity);
    }
}
