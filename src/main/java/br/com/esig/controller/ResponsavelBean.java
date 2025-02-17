package br.com.esig.controller;

import br.com.esig.model.Responsavel;
import br.com.esig.repository.ResponsaveisRepository;
import br.com.esig.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ResponsavelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    
    public List<SelectItem> getComboResponsavel() {
        List<Responsavel> responsaveis =  responsavelRepository.findAll();
        List<SelectItem> items = new ArrayList<>();
        for (Responsavel r : responsaveis) {
            items.add(new SelectItem(r.getId(), r.getNome()));
        }
        return items;
    }
}