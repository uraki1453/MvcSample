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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import MvcSample.domain.form.StaffForm;
import MvcSample.mapper.StaffMapper;
import MvcSample.validation.MyGroupSequence;

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
    public String create(@Validated(MyGroupSequence.class) StaffForm staffForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        logger.info(staffForm.getName());
        if (bindingResult.hasErrors()) {
            return "TDB1002";
        }
        mapper.insertStaff(staffForm);
        redirectAttributes.addFlashAttribute("message", "職員を作成しました。");
        return "redirect:/TDB1000";
    }
}
