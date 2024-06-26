package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.models.Message;

import java.util.List;
import java.util.UUID;

public interface IMessageService {
    List<Message> findAll();
    List<Message> findAllByCustomer(UUID customerId);
    Message findById(UUID id);

}
