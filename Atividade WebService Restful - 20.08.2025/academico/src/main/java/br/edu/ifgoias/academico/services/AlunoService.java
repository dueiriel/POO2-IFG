package br.edu.ifgoias.academico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.dto.AlunoMapper;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Transactional
    public List<AlunoDTO> findAll() {
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDTO findById(Integer id) {
        return alunoRepository.findById(id)
                .map(alunoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    public AlunoDTO update(Integer id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        aluno.setNome(dto.getNome());
        aluno.setSexo(dto.getSexo());
        aluno.setDt_nasc(dto.getDt_nasc());
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toDTO(aluno);
    }

    public void delete(Integer id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        alunoRepository.delete(aluno);
    }
}