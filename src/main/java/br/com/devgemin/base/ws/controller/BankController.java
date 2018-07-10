package br.com.devgemin.base.ws.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devgemin.base.ws.model.Bank;

@RestController
@RequestMapping("/api/bank")
public class BankController extends GenericController<Bank> {

}
