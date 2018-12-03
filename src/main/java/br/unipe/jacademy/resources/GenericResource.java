package br.unipe.jacademy.resources;

import br.unipe.jacademy.entities.GenericEntity;
import br.unipe.jacademy.services.GenericService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericResource<S extends GenericService, E extends GenericEntity> {

    protected ModelAndView view(String path, String name, Object object) {
        ModelAndView modelAndView = new ModelAndView(path);
        modelAndView.addAllObjects(model(name, object));
        return modelAndView;
    }

    protected Map model(String key, Object value) {
        Map<String, Object> model = new HashMap();
        model.put(key, value);
        return model;
    }

    protected ModelAndView modelAndView(String path, String name1, Object object1, String name2, Object object2) {
        ModelAndView modelAndView = new ModelAndView(path);
        modelAndView.addAllObjects(model(name1, object1));
        modelAndView.addAllObjects(model(name2, object2));
        return modelAndView;
    }
}
