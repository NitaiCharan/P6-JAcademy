package br.unipe.jacademy.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unipe.jacademy.entities.SalaEntity;
import br.unipe.jacademy.entities.TurmaEntity;
import br.unipe.jacademy.services.SalaService;
import br.unipe.jacademy.services.TurmaService;

@Controller
@RequestMapping("sala")
public class SalaResource extends GenericResource<SalaService, SalaEntity> {

	@Autowired
	private SalaService service;
	@Autowired
	private TurmaService turmaService;

	@GetMapping("/listar")
	public ModelAndView inicio() {
		return modelAndView("sala/adicionar", "salas", service.getAll(), "sala", new SalaEntity());
	}

	@GetMapping("/cadastrar")
	public ModelAndView salvar(@Valid SalaEntity entity, BindingResult bindingResult) {
		/*
		if (bindingResult.hasErrors()) {
			List<String> msg = new ArrayList<>();
			bindingResult.getAllErrors().forEach(erro -> msg.add(erro.getDefaultMessage()) );
			return modelAndView("sala/adicionar", "salas", service.getAll(), "sala", entity).addObject("mensagens", msg);
		}*/
		service.salvar(entity);
		return inicio();
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		return modelAndView("sala/editar", "sala", service.getPorId(id).get(), "salas", service.getAll());
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		service.excluirPorId(id);
		return inicio();
	}

	@GetMapping("**/pesquisar")
	public ModelAndView pesquisar(@RequestParam("nome") String nome) {
		return modelAndView("sala/listar", "salas", service.getPorNome(nome), "sala", new SalaEntity());
	}

	@GetMapping("/listar/turma/{id}")
	public ModelAndView listarTurma(@PathVariable("id") Long id) {
		return modelAndView("sala/relacionar1", "sala", service.getPorId(id).get(), "turmas", turmaService.getTurmasPorSala(id));
	}

	@PostMapping("/cadastrar/turma/{id}")
	public ModelAndView cadastarTurma(TurmaEntity turma, @PathVariable("id") Long id) {
		SalaEntity sala = service.getPorId(id).get();
		turma.setSala(sala);
		turmaService.salvar(turma);
		return modelAndView("sala/relacionar1", "sala", sala, "turmas", turmaService.getTurmasPorSala(id));
	}

	@GetMapping("/excluir/turma/{id}")
	public ModelAndView excluirTurma(@PathVariable("id") Long id) {
		SalaEntity sala = turmaService.getPorId(id).get().getSala();
		turmaService.excluirPorId(id);
		return modelAndView("sala/relacionar1", "sala", sala, "turmas", turmaService.getTurmasPorSala(sala.getId()));
	}
}
