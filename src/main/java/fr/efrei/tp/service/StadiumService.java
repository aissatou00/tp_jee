package fr.efrei.tp.service;
import fr.efrei.tp.dto.StadiumDto;
import fr.efrei.tp.model.Stadium;
import fr.efrei.tp.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }

    public Stadium getStadiumById(Long id) {
        return stadiumRepository.findById(id).orElse(null);
    }

    public Stadium createStadium(StadiumDto stadiumDto) {
        Stadium stadium = new Stadium();
        stadium.setName(stadiumDto.getName());
        stadium.setLocation(stadiumDto.getLocation());
        stadium.setCapacity(stadiumDto.getCapacity());
        return stadiumRepository.save(stadium);
    }

    @Transactional
    public boolean deleteStadium(Long id) {
        if (stadiumRepository.existsById(id)) {
            stadiumRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Stadium updateStadium(Long id, StadiumDto stadiumDto) {
        Stadium stadium = getStadiumById(id);
        if (stadium != null) {
            stadium.setName(stadiumDto.getName());
            stadium.setLocation(stadiumDto.getLocation());
            stadium.setCapacity(stadiumDto.getCapacity());
            return stadiumRepository.save(stadium);
        }
        return null;
    }

}
