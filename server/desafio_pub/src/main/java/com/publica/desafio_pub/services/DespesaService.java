package com.publica.desafio_pub.services;

import com.publica.desafio_pub.dto.get.DespesaDTO;
import com.publica.desafio_pub.models.Conta;
import com.publica.desafio_pub.models.Despesa;
import com.publica.desafio_pub.repositories.ContaRepository;
import com.publica.desafio_pub.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<DespesaDTO> findAll() {
        List<Despesa> despesaList = despesaRepository.findAll();
        return despesaList.stream().map(x -> new DespesaDTO(x)).collect(Collectors.toList());
    }

    public boolean save(Despesa despesa, Conta conta, UriComponentsBuilder uriBuilder) {

        Double saldo = conta.getSaldo();

        if(saldo > despesa.getValor()){

            despesaRepository.save(despesa);
            saldo -= despesa.getValor();
            conta.setSaldo(saldo);
            contaRepository.save(conta);

            return true;

        }

        else{

            System.out.println("valor da despesa maior que o saldo na conta");
            return false;
        }

    }

    public Optional<Despesa> findById(Long id) {

        return despesaRepository.findById(id);
    }


    public void delete(Despesa despesa) {

        despesaRepository.delete(despesa);
    }
}
