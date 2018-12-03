package br.unipe.jacademy.resources;

import br.unipe.jacademy.entities.AlunoEntity;
import br.unipe.jacademy.entities.EnderecoEntity;
import br.unipe.jacademy.services.AlunoService;
import br.unipe.jacademy.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("aluno")
public class AlunoResource extends GenericResource<AlunoService, AlunoEntity> {

    @Autowired
    private AlunoService service;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/listar")
    public ModelAndView inicio() {
        ModelAndView modelAndView = view("aluno/adicionar", "alunos", service.getAll());
        return modelAndView.addAllObjects(novo());
    }

    @GetMapping("/cadastrar")
    public ModelAndView salvar(AlunoEntity entity) {
        service.salvar(entity);
        return inicio();
    }


    @GetMapping("/editar/{idaluno}")
    public ModelAndView editar(@PathVariable("idaluno") Long idaluno) {
        Optional<AlunoEntity> optional = service.getPorId(idaluno);
        ModelAndView modelAndView = view("aluno/editar", "aluno", optional.get());
        return modelAndView.addAllObjects(model("alunos", service.getAll()));
    }

    @GetMapping("/excluir/{idaluno}")
    public ModelAndView excluir(@PathVariable("idaluno") Long idaluno) {
        service.excluirPorId(idaluno);
        return inicio();
    }

    @GetMapping("**/pesquisar")
    public ModelAndView pesquisar(@RequestParam("nome") String nome) {
        ModelAndView modelAndView = view("aluno/listar","alunos", service.getPorNome(nome));
        return modelAndView.addAllObjects(novo());
    }

    private Map novo(){
        return model("aluno", new AlunoEntity());
    }

    @GetMapping("/listar/endereco/{id}")
    public ModelAndView listarEndereco(@PathVariable("id") Long id) {
        return modelAndView("aluno/relacionar1", "aluno", service.getPorId(id).get(), "enderecos", enderecoService.getEnderecoId(id));
    }

    @PostMapping("/cadastrar/endereco/{id}")
    public ModelAndView cadastarEndereco(EnderecoEntity endereco, @PathVariable("id") Long id) {
        AlunoEntity aluno = service.getPorId(id).get();
        endereco.setPessoa(aluno);
        enderecoService.salvar(endereco);
        return modelAndView("aluno/relacionar1", "aluno", aluno, "enderecos", enderecoService.getEnderecoId(id));
    }

    @GetMapping("/excluir/endereco/{id}")
    public ModelAndView excluirTurma(@PathVariable("id") Long id) {
        AlunoEntity aluno = (AlunoEntity) enderecoService.getPorId(id).get().getPessoa();
        enderecoService.excluirPorId(id);
        return modelAndView("sala/relacionar1", "sala", aluno, "turmas", enderecoService.getEnderecoId(aluno.getId()));
    }

}
