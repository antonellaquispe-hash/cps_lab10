package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
class OwnerServiceTest {
    @Autowired OwnerService ownerService;
    @Autowired OwnerRepository ownerRepository;
    private Owner fixture;

    @BeforeEach void setUp() {
        fixture = ownerRepository.save(new Owner("Ana", "Quispe", "Av. Lima 123", "Lima", "999111222"));
    }

    @Test void createOwner() {
        Owner created = ownerService.create(new Owner("Luis", "Rojas", "Calle Sol 45", "Lima", "988777666"));
        assertNotNull(created.getId());
        assertEquals("Luis", created.getFirstName());
    }

    @Test void updateOwner() throws OwnerNotFoundException {
        Owner changed = ownerService.update(fixture.getId(), new Owner("Ana María", "Quispe", "Jr. Norte 9", "Callao", "955444333"));
        assertEquals("Ana María", changed.getFirstName());
        assertEquals("Callao", changed.getCity());
        assertEquals("955444333", changed.getTelephone());
    }

    @Test void findOwnerById() throws OwnerNotFoundException {
        assertEquals("Quispe", ownerService.findById(fixture.getId()).getLastName());
    }

    @Test void deleteOwner() throws OwnerNotFoundException {
        Integer id = fixture.getId();
        ownerService.delete(id);
        assertFalse(ownerRepository.existsById(id));
        assertThrows(OwnerNotFoundException.class, () -> ownerService.findById(id));
    }
}
