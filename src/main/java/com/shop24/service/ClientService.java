package com.shop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.shop24.model.Client;
import com.shop24.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    

	public Client createClient(Client client) {
		
        if (clientRepository.existsByEmail(client.getEmail())){
            throw new IllegalArgumentException("A client with the same email already exists.");
        }
		return clientRepository.save(client);
		
	}

    // Add other methods for the required functionalities
}