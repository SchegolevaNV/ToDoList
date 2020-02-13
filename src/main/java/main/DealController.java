package main;

import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DealController
{
    @Autowired
    private DealRepository dealRepository;

    @GetMapping("/deals/")
    public List<Deal> list()
    {
        Iterable<Deal> dealIterable = dealRepository.findAll();
        ArrayList<Deal> deals = new ArrayList<>();
        for (Deal deal : dealIterable) {
            deals.add(deal);
        }
        return deals;
    }

    @PostMapping("/deals/")
    public int add (Deal deal)
    {
        Deal newDeal = dealRepository.save(deal);
        return newDeal.getId();

    }

    @GetMapping("/deals/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Deal> dealOptional = dealRepository.findById(id);
        if (!dealOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(dealOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping ("/deals/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        Optional<Deal> dealOptional = dealRepository.findById(id);
        if (!dealOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        dealRepository.delete(dealOptional.get());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/deals/all")
    public ResponseEntity clearList () {
        dealRepository.deleteAll();
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @PutMapping ("/deals/{id}")
    public ResponseEntity changeDeal(@PathVariable int id, String name, String date)
    {
        Optional<Deal> dealOptional = dealRepository.findById(id);
        if (!dealOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        dealOptional.get().setName(name);
        dealOptional.get().setDate(date);
        dealRepository.save(dealOptional.get());
        return new ResponseEntity(dealOptional.get(), HttpStatus.OK);
    }
}
