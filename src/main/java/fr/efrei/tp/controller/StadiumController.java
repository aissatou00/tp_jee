package fr.efrei.tp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import fr.efrei.tp.service.StadiumService;
import fr.efrei.tp.dto.StadiumDto;
import fr.efrei.tp.model.Stadium;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {
        private final StadiumService stadiumService;
    
        @Autowired
        public StadiumController(StadiumService stadiumService) {
            this.stadiumService = stadiumService;
        }
    
        @PostMapping
        public ResponseEntity<Stadium> createStadium(@RequestBody StadiumDto stadiumDto) {
            Stadium stadium = stadiumService.createStadium(stadiumDto);
            return new ResponseEntity<>(stadium, HttpStatus.CREATED);
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<Stadium> getStadiumById(@PathVariable Long id) {
            Stadium stadium = stadiumService.getStadiumById(id);
            if (stadium != null) {
                return new ResponseEntity<>(stadium, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<Stadium> updateStadium(@PathVariable Long id, @RequestBody StadiumDto stadiumDto) {
            Stadium updatedStadium = stadiumService.updateStadium(id, stadiumDto);
            if (updatedStadium != null) {
                return new ResponseEntity<>(updatedStadium, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteStadium(@PathVariable Long id) {
            stadiumService.deleteStadium(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
        @GetMapping
        public ResponseEntity<List<Stadium>> getAllStadiums() {
            List<Stadium> stadiums = stadiumService.getAllStadiums();
            return new ResponseEntity<>(stadiums, HttpStatus.OK);
        }

    
}
