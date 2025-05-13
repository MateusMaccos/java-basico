package dio.boards.ui;

import dio.boards.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardMenu {
    private final BoardEntity board;

    public void execute(){}
}
