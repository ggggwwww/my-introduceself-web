package website.introduceweb.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin/pageForm")
    String adminPageForm(){
        return "admin/Administartor.html";
    }
}
