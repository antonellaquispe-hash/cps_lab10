package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OwnerService {
    private final OwnerRepository ownerRepository;
    public OwnerService(OwnerRepository ownerRepository) { this.ownerRepository = ownerRepository; }
    public Owner create(Owner owner) { owner.setId(null); return ownerRepository.save(owner); }
    public Owner update(Integer id, Owner changes) throws OwnerNotFoundException {
        Owner owner = findById(id);
        owner.setFirstName(changes.getFirstName()); owner.setLastName(changes.getLastName());
        owner.setAddress(changes.getAddress()); owner.setCity(changes.getCity()); owner.setTelephone(changes.getTelephone());
        return ownerRepository.save(owner);
    }
    @Transactional(readOnly = true)
    public Owner findById(Integer id) throws OwnerNotFoundException {
        return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Propietario no encontrado: " + id));
    }
    @Transactional(readOnly = true)
    public List<Owner> findAll() { return ownerRepository.findAll(); }
    public void delete(Integer id) throws OwnerNotFoundException { ownerRepository.delete(findById(id)); }
}
