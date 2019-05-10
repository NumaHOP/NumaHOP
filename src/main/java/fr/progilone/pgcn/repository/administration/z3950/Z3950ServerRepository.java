package fr.progilone.pgcn.repository.administration.z3950;

import fr.progilone.pgcn.domain.administration.exchange.z3950.Z3950Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Sébastien on 21/12/2016.
 */
public interface Z3950ServerRepository extends JpaRepository<Z3950Server, String> {

    List<Z3950Server> findByActive(boolean active);

    Z3950Server findOneByNameAndIdentifierNot(String name, String id);

    Z3950Server findOneByName(String name);
}
