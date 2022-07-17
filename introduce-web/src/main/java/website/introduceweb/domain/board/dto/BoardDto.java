package website.introduceweb.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import website.introduceweb.domain.board.entity.Board;

@Getter
@Setter
public class BoardDto {
    private String board_title;

    public Board toEntity(String title){

        return Board.builder()
                .board_title(title)
                .build();
    }
}
