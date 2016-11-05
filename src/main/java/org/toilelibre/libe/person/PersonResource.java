package org.toilelibre.libe.person;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.CdoSnapshotState;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
class PersonResource {

    private final Javers javers;
    private final PersonRepository personRepository;

    @Autowired
    PersonResource (Javers javers1, PersonRepository personRepository1) {
        this.javers = javers1;
        this.personRepository = personRepository1;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    Map<String, Object> add(@RequestBody Person person) throws JsonProcessingException {
        return Collections.singletonMap ("id", personRepository.save(person.withNewId ()).getId ());
    }

    @RequestMapping(value="/", method = RequestMethod.PUT)
    void set(@RequestBody Person person) throws JsonProcessingException {
       personRepository.save(person);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    Person get(@PathVariable UUID id) throws JsonProcessingException {
        return personRepository.findOne (id);
    }
    
    @RequestMapping("/changes/{id}")
    String getPersonChanges(@PathVariable UUID id) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId (id, Person.class);

        List<Change> changes = javers.findChanges(jqlQuery.build());

        return javers.getJsonConverter().toJson(changes);
    }
    
    @RequestMapping("/snapshots/{id}")
    String getPersonSnapshots(@PathVariable UUID id) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId (id, Person.class);

        List<CdoSnapshot> changes = javers.findSnapshots (jqlQuery.build());

        return javers.getJsonConverter().toJson(changes);
    }
    
    @RequestMapping("/snapshots/{id}/number/{number}")
    String getOnePersonSnapshot(@PathVariable UUID id, @PathVariable int number) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId (id, Person.class);

        List<CdoSnapshot> changes = javers.findSnapshots (jqlQuery.build());
        CdoSnapshotState version = changes.get (changes.size () - number).getState ();

        return javers.getJsonConverter().toJson(version);
    }

}