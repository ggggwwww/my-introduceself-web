package website.introduceweb.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @PostMapping("/administrator")
    String admin(){


        return "admin/Administrator.html";
    }

    @GetMapping("/administratorValidate")
    String adminValidatePageForm(){

        return "admin/AdministratorValidatePage.html";
    }

    @PostMapping("/administratorValidate")
    String validateIsAdmin(@RequestBody String validString){
        if(!validString.equals("gw@serisz!@Toal"))
            return "redirect:/administratorValidate";

        return "redirect:/administrator";
    }
}
