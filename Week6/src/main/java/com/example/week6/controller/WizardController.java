package com.example.week6.controller;

import com.example.week6.pojo.Wizard;
import com.example.week6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;


    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizard() {
        List<Wizard> wizards = wizardService.retrieveWizards();
        return ResponseEntity.ok(wizards);
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> createWizard(@RequestBody Wizard wizard) {
        return ResponseEntity.ok(wizardService.createWizard(wizard));
    }

    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public ResponseEntity<?> updateWizard(@RequestBody Wizard wizard) {
        return ResponseEntity.ok(wizardService.updateWizard(wizard));
    }

    @RequestMapping(value ="/deleteWizard", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWizard(@RequestBody Wizard wizard) {
        return ResponseEntity.ok(wizardService.deleteWizard(wizard));
    }
}
