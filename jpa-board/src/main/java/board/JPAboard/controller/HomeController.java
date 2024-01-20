package board.JPAboard.controller;

import board.JPAboard.service.BoardService;
import board.JPAboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping(value={"","/"})
    public String home(Model model){
        model.addAttribute("userCntDto",userService.getUserCnt());
        model.addAttribute("boardCntDto",boardService.getBoardCnt());
        return "home";
    }
}
