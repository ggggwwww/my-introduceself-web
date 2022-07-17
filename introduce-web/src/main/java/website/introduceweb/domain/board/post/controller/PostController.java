package website.introduceweb.domain.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.dto.BoardDto;
import website.introduceweb.domain.board.post.dto.PostDTO;
import website.introduceweb.domain.board.post.entity.Post;
import website.introduceweb.domain.board.post.repository.PostRepositoryImpl;
import website.introduceweb.domain.board.repository.BoardRepositoryImpl;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardRepositoryImpl boardRepository;
    private final PostRepositoryImpl repository;

    @GetMapping("/{boardTitle}/postRegister")
    String createPostForm(@PathVariable("boardTitle") String boardTitle,
                          Model model) {
        model.addAttribute("boardTitle", boardTitle);

        return "board/post/form/createPostForm";
    }

    @PostMapping("/{boardTitle}/postRegister")
    String createPost(@PathVariable("boardTitle") String boardTitle
            , @ModelAttribute PostDTO postDto
            , @ModelAttribute BoardDto boardDto
    ) {
        boardRepository.findByTitle(boardTitle);

        Board board = boardDto.toEntity(boardTitle);
        Post post = postDto.toEntity(postDto.getPost_title(), postDto.getPost_contents(), board);

        repository.save(post);

        return "redirect:/" + boardTitle + "/";
    }

    @GetMapping("/{boardTitle}/{postNumber}")
    String postPageForm(@PathVariable("boardTitle") String boardTitle,
                @PathVariable("postNumber") Long postNumber,
                Model model) {

        model.addAttribute("posts", repository.findByNumber(postNumber));
        return "board/post/post";
    }

}
