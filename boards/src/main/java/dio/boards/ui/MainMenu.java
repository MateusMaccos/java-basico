package dio.boards.ui;

import static dio.boards.persistence.config.ConnectionConfig.getConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dio.boards.persistence.entity.BoardColumnEntity;
import dio.boards.persistence.entity.BoardColumnKindEnum;
import dio.boards.persistence.entity.BoardEntity;
import dio.boards.service.BoardQueryService;
import dio.boards.service.BoardService;

import static dio.boards.persistence.entity.BoardColumnKindEnum.*;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void execute() throws SQLException {
        System.out.println("Bem vindo ao gerenciamento de boards da DIO");
        var option =-1;
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar board");
            System.out.println("2 - Selecionar board");
            System.out.println("3 - Deletar board");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                createBoard();
                    break;
                case 2:
                selectBoard();
                    break;
                case 3:
                deleteBoard();
                    break;
                case 4:
                    break;
            
                default:
                System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private void deleteBoard() throws SQLException{
        System.out.println("Digite o id do board que será criado: ");
        var id = scanner.nextLong();
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            if(service.delete(id)){
                System.out.println("Board deletado com sucesso!");
            }else{
                System.out.println("Board não encontrado!");
            }
            
        }
    }

    private void selectBoard() throws SQLException{
        System.out.println("Informe o id do board");
        var id = scanner.nextLong();
        try(var connection = getConnection()){
            var queryService = new BoardQueryService(connection);
            var optional = queryService.findById(id);
            optional.ifPresentOrElse(b-> new BoardMenu(b).execute(),()->System.out.println("Board nao encontrado"));
        }
    }

    private void createBoard() throws SQLException{
        var entity = new BoardEntity();
        System.out.println("Informe o nome do board");
        entity.setName(scanner.next());

        System.out.println("Seu board terá colunas além das três padrões? Se sim, digite quantas, se não digite 0");
        var additionalColumns = scanner.nextInt();

        List<BoardColumnEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna de tarefa inicial");
        var columnName = scanner.next();
        var column = createColumn(columnName,INITIAL,0);
        columns.add(column);

        for(int i =0; i < additionalColumns; i++){
            System.out.println("Informe o nome da coluna de tarefa pendente");
            var pendingColumnName = scanner.next();
            var pendingColumn = createColumn(pendingColumnName,PENDING,i+1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna de tarefa final");
        var finalColumnName = scanner.next();
        var finalColumn = createColumn(finalColumnName,FINAL,additionalColumns+1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de tarefa final");
        var cancelColumnName = scanner.next();
        var cancelColumn = createColumn(cancelColumnName,CANCEL,additionalColumns+1);
        columns.add(cancelColumn);

        entity.setBoardColumns(columns);
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            service.insert(entity);
            System.out.println("Board criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar board");
            e.printStackTrace();
        }
    }

    private BoardColumnEntity createColumn(final String name,final BoardColumnKindEnum kind, final int order) {
        var column = new BoardColumnEntity();
        column.setName(name);
        column.setKind(kind);
        column.setOrder(order);
        return column;
    }
}
