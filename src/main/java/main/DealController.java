package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Deal;

import java.util.List;

@RestController
public class DealController
{
    @GetMapping("/deals/")
    public List<Deal> list()
    {
        return Storage.getAllDeals();
    }

    @PostMapping("/deals/")
    public int add (Deal deal)
    {
        return Storage.addDeal(deal);
    }

    @GetMapping("/deals/{id}")
    public static ResponseEntity get(@PathVariable int id)
    {
        Deal deal = Storage.getDeal(id);
        if (deal == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(deal, HttpStatus.OK);
    }

    @DeleteMapping ("/deals/{id}")
    public static ResponseEntity delete(@PathVariable int id)
    {
        Deal deal = Storage.getDeal(id);
        if (deal == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.deleteDeal(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/deals/all")
    public ResponseEntity clearList () {
        Storage.deleteAllDeals();
        return new ResponseEntity(true, HttpStatus.OK);

    }

    @PutMapping ("/deals/{id}")
    public ResponseEntity changeDeal(@PathVariable int id, String name, String date)
    {
        Deal deal = Storage.getDeal(id);
        if (deal == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.changeDeal(id, name, date);
        return new ResponseEntity(deal, HttpStatus.OK);
    }
}
