package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.Message;

import java.util.UUID;

public interface IMessageRepository extends JpaRepository<Message, UUID> {
}