package MvcSample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MvcSample.domain.LoginUserDetails;
import MvcSample.domain.Staff;
import MvcSample.mapper.StaffMapper;

@Controller
@RequestMapping("TDB1000")
public class TDB1000Controller {
    //private static final Logger logger = LoggerFactory.getLogger(TDB1000Controller.class);

    @Autowired
    private StaffMapper mapper;


    @GetMapping
    public ModelAndView index(@AuthenticationPrincipal LoginUserDetails userDetail, ModelAndView mv) {
        List<Staff> staffs = mapper.selectAll();
        mv.addObject("staffs", staffs);
        mv.addObject("user", userDetail.getStaff());
        mv.setViewName("TDB1000");
        return mv;
    }
}