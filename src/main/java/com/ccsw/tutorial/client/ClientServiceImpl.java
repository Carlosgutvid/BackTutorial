package com.ccsw.tutorial.client;

import java.util.List;

import javax.naming.NameAlreadyBoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import jakarta.transaction.Transactional;

/**
 * @author CGV
 *
 */
@Service
@Transactional

public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     */

    @Override
    public void save(Long id, ClientDto dto) throws NameAlreadyBoundException {
        boolean nameExists = clientRepository.existsByName(dto.getName());
        if (nameExists) {
            throw new NameAlreadyBoundException();
        } else {
            Client client = new Client();
            client.setName(dto.getName());
            clientRepository.save(client);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception();
        }

        this.clientRepository.deleteById(id);
    }

}
