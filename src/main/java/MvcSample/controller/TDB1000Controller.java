package MvcSample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MvcSample.domain.Staff;
import MvcSample.mapper.StaffMapper;

@Controller
@RequestMapping("TDB1000")
public class TDB1000Controller {

    @Autowired
    private StaffMapper mapper;

    @GetMapping
    public ModelAndView index(ModelAndView mv) {
        List<Staff> staffs = mapper.selectAll();
        mv.addObject("staffs", staffs);
        mv.setViewName("TDB1000");
        return mv;
    }
}