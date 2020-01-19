package com.assessment.task.backend.controller;

import com.assessment.task.backend.model.rq.AddFeeRq;
import com.assessment.task.backend.model.rs.FeesRs;
import com.assessment.task.backend.service.IFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fees")
@CrossOrigin(origins = "http://localhost:8080")
public class FeesController {

    private final IFeesService feesService;

    @Autowired
    public FeesController(IFeesService feesService) {
        this.feesService = feesService;
    }

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<FeesRs> getFees(@RequestParam int page,
                                          @RequestParam int count) {
        FeesRs feesRs = feesService.getFees(page, count);
        return new ResponseEntity<>(feesRs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> addFee(@RequestBody @Valid AddFeeRq addFeeRq) {
        feesService.addFee(addFeeRq.getFrom(), addFeeRq.getTo(), addFeeRq.getPercent());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFee(@PathVariable Integer id) {
        feesService.removeFee(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
