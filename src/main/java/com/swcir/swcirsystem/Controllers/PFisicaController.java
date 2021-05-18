package com.swcir.swcirsystem.Controllers;

import java.util.Date;
import java.util.Optional;

import com.swcir.swcirsystem.Models.PFisica;
import com.swcir.swcirsystem.Models.User;
import com.swcir.swcirsystem.Repositories.PFisicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/pfisica")
public class PFisicaController {
    
    @Autowired
    private PFisicaRepository pFisicaRepository;

    @GetMapping(path="/find/all")
    public Iterable<PFisica> getAllPFisica(){
            return pFisicaRepository.findAll();
    }

    @GetMapping(path="/find/{userId}")
    public Optional<PFisica> getPFisicaById(@PathVariable int pfisId){
        return pFisicaRepository.findById(pfisId);
    }

    @PostMapping(path="/add")
    public String addNewPfis(@RequestParam User userId, @RequestParam String name, @RequestParam Long cpf, @RequestParam Date dataNasc, @RequestParam String nitPisPasep, @RequestParam int tituloEleitoral){


        PFisica pfis = new PFisica();
        pfis.setUser(userId);
        pfis.setName(name);
        pfis.setCpf(cpf);
        pfis.setDataNasc(dataNasc);
        pfis.setNitPisPasep(nitPisPasep);
        pfis.setTituloEleitoral(tituloEleitoral);
        pFisicaRepository.save(pfis);

        return "Saved";
    }

}
