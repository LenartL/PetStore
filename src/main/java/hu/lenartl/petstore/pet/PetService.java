package hu.lenartl.petstore.pet;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet findById(Long id) throws NoSuchElementException {
      return petRepository.findById(id).orElseThrow();
    }
}
