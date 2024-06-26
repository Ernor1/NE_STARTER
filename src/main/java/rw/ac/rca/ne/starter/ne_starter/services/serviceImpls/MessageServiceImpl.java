package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.Message;
import rw.ac.rca.ne.starter.ne_starter.repositories.IMessageRepository;
import rw.ac.rca.ne.starter.ne_starter.services.IMessageService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository messageRepository;


    @Override
    public List<Message> findAll() {
        try{
            return messageRepository.findAll();

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
    }
    }

    @Override
    public List<Message> findAllByCustomer(UUID customerId) {
        try {
            return messageRepository.findAllByCustomer_Id(customerId);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
    }
    }

    @Override
    public Message findById(UUID id) {
        try{
            return messageRepository.findById(id).orElseThrow(()-> new NotFoundException("Message not found"));

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
