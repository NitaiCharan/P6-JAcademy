package br.unipe.jacademy.resources;


import br.unipe.jacademy.entities.ProfessorEntity;
import br.unipe.jacademy.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("professor")
public class ProfessorResource extends GenericResource<ProfessorService, ProfessorEntity> {

    @Autowired
    private ProfessorService service;

    @GetMapping("/listar")
    public ModelAndView inicio() {
        ModelAndView modelAndView = view("professor/adicionar", "professores", service.getAll());
        return modelAndView.addAllObjects(novo());
    }

    @GetMapping("/cadastrar")
    public ModelAndView salvar(ProfessorEntity entity) {
        service.salvar(entity);
        return inicio();
    }


    @GetMapping("/editar/{idprofessor}")
    public ModelAndView editar(@PathVariable("idprofessor") Long idprofessor) {
        Optional<ProfessorEntity> optional = service.getPorId(idprofessor);
        ModelAndView modelAndView = view("professor/editar", "professor", optional.get());
        return modelAndView.addAllObjects(model("professores", service.getAll()));
    }

    @GetMapping("/excluir/{idprofessor}")
    public ModelAndView excluir(@PathVariable("idprofessor") Long idprofessor) {
        service.excluirPorId(idprofessor);
        return inicio();
    }

    @GetMapping("**/pesquisar")
    public ModelAndView pesquisar(@RequestParam("nome") String nome) {
        ModelAndView modelAndView = view("professor/listar","professores", service.getPorNome(nome));
        return modelAndView.addAllObjects(novo());
    }

    private Map novo(){
        return model("professor", new ProfessorEntity());
    }
}
