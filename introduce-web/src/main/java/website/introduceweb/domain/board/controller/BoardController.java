package website.introduceweb.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import website.introduceweb.domain.board.dto.BoardDto;
import website.introduceweb.domain.board.service.BoardService;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.post.repository.PostRepositoryImpl;
import website.introduceweb.domain.board.repository.BoardRepositoryImpl;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepositoryImpl repository;
    private final PostRepositoryImpl postRepository;
    private final BoardService service;

    @GetMapping("/registerBoard")
    String createBoardForm(){

        return "board/form/createBoardForm";
    }

    @PostMapping("/registerBoard")
    String createBoard(@ModelAttribute BoardDto boardDto){
        Board board = boardDto.toEntity(boardDto.getBoard_title());

        service.join(board);
        return "redirect:/";
    }

    @GetMapping("/")
    String boardList(Model model){

        model.addAttribute("boardList", service.showAll());
        return "main";
    }

    @GetMapping("/{boardTitle}")
    String boardForm(@PathVariable("boardTitle") String boardTitle,
                     Model model){
        model.addAttribute("boardTitle", boardTitle);
        if(!postRepository.findByName(boardTitle).isEmpty())
            model.addAttribute("posts", postRepository.findByName(boardTitle));

        return "board/board";
    }

}
