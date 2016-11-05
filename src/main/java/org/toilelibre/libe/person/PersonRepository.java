package org.toilelibre.libe.person;

import java.util.UUID;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

@JaversSpringDataAuditable
interface PersonRepository extends MongoRepository<Person, UUID> {}
