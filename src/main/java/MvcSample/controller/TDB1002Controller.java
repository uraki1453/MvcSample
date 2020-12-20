package MvcSample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import MvcSample.domain.form.StaffForm;
import MvcSample.mapper.StaffMapper;



@Controller
@RequestMapping("TDB1002")
public class TDB1002Controller {
    private static final Logger logger = LoggerFactory.getLogger(TDB1002Controller.class);

    @Autowired
    private StaffMapper mapper;

    @GetMapping
    public String newForm(Model model) {
        model.addAttribute("staffForm", new StaffForm());
        return "TDB1002";
    }

    @PostMapping
    public String create(@Validated StaffForm staffForm, BindingResult bindingResult) {
        logger.info(staffForm.getName());
        if(bindingResult.hasErrors()){
            logger.info("aaaa");
            logger.info(bindingResult.toString());
            return "TDB1002";
        }
        mapper.insertStaff(staffForm);
        return "redirect:/TDB1001";
    }
}