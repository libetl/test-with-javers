package org.toilelibre.libe.person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.javers.core.metamodel.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Person {
    
    enum PhoneKind {
        WORK, HOME, MOBILE, FAX, OTHER
    }
    
    enum AddressKind {
        HOME, WORK, OTHER
    }
    
    enum KindOfRoute {
        STREET, ROAD, AVENUE, BOULEVARD, PLAZA, ALLEY, VILLA
    }
    
    static class Telephone {
        @JsonProperty private final PhoneKind phoneKind;
        @JsonProperty private final int intCode;
        @JsonProperty private final int prefix;
        @JsonProperty private final long number;

        @JsonCreator
        Telephone (@JsonProperty("phoneKind") PhoneKind phoneKind, 
                @JsonProperty("intCode") int intCode, @JsonProperty("prefix") int prefix, 
                @JsonProperty("number") long number) {
            this.phoneKind = phoneKind;
            this.intCode = intCode;
            this.prefix = prefix;
            this.number = number;
        }

        public PhoneKind getPhoneKind () {
            return phoneKind;
        }

        public int getIntCode () {
            return intCode;
        }

        public int getPrefix () {
            return prefix;
        }

        public long getNumber () {
            return number;
        }
        
    }
    
    static class Address {
        @JsonProperty private final AddressKind addressKind;
        @JsonProperty private final String name;
        @JsonProperty private final int number;
        @JsonProperty private final KindOfRoute kindOfRoute;
        @JsonProperty private final String steetName;
        @JsonProperty private final String postalCode;
        @JsonProperty private final String city;
        @JsonProperty private final String regionOrState;
        @JsonProperty private final String country;
        
        @JsonCreator
        Address (@JsonProperty("addressKind") AddressKind addressKind, @JsonProperty("name") String name, 
                @JsonProperty("number") int number, @JsonProperty("kindOfRoute") KindOfRoute kindOfRoute, 
                @JsonProperty("streetName") String steetName, @JsonProperty("postalCode") String postalCode, 
                @JsonProperty("city") String city, @JsonProperty("regionOrState") String regionOrState, 
                @JsonProperty("country") String country) {
            this.addressKind = addressKind;
            this.name = name;
            this.number = number;
            this.kindOfRoute = kindOfRoute;
            this.steetName = steetName;
            this.postalCode = postalCode;
            this.city = city;
            this.regionOrState = regionOrState;
            this.country = country;
        }

        public AddressKind getAddressKind () {
            return addressKind;
        }

        public String getName () {
            return name;
        }

        public int getNumber () {
            return number;
        }

        public KindOfRoute getKindOfRoute () {
            return kindOfRoute;
        }

        public String getSteetName () {
            return steetName;
        }

        public String getPostalCode () {
            return postalCode;
        }

        public String getCity () {
            return city;
        }

        public String getRegionOrState () {
            return regionOrState;
        }

        public String getCountry () {
            return country;
        }
    }

    @Id
    private final UUID id;
    @JsonProperty private final String firstName;
    @JsonProperty private final String lastName;
    @JsonProperty private final LocalDate birthDate;
    @JsonProperty private final String birthCity;
    @JsonProperty private final String birthCountry;
    @JsonProperty private final String nationality;
    @JsonProperty private final List<Telephone> phoneNumbers;
    @JsonProperty private final List<Address> addresses;

    @JsonCreator
    Person (@JsonProperty("id") UUID id, @JsonProperty("firstName") String firstName, 
            @JsonProperty("lastName") String lastName, @JsonProperty("birthDate") LocalDate birthDate, 
            @JsonProperty("birthCity") String birthCity, @JsonProperty("birthCountry") String birthCountry, 
            @JsonProperty("nationality") String nationality, @JsonProperty("phoneNumbers") List<Telephone> phoneNumbers, 
            @JsonProperty("addresses") List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthCity = birthCity;
        this.birthCountry = birthCountry;
        this.nationality = nationality;
        this.phoneNumbers = phoneNumbers;
        this.addresses = addresses;
    }
    
    public Person withNewId () {
        return new Person (UUID.randomUUID (), firstName, lastName, birthDate, birthCity,
                birthCountry, nationality, 
                phoneNumbers == null ? null : new ArrayList<>(phoneNumbers),
                        addresses == null ? null : new ArrayList<>(addresses));
    }

    public UUID getId () {
        return this.id;
    }
    
}
