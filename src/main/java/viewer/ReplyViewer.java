package viewer;

import controller.BoardController;
import controller.ReplyController;
import controller.UserController;
import lombok.Setter;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ReplyViewer {
    @Setter
    private ReplyController replyController;
    @Setter
    private BoardController boardController;
    @Setter
    private UserController userController;
    @Setter
    private Scanner scanner;
    @Setter
    private UserDTO logIn;

    public void showReply(int boardId) {
        ArrayList<ReplyDTO> replyList = new ArrayList<>();
        replyList = replyController.selectByBoardId(boardId);
        if (replyList.isEmpty()) {
            System.out.println("댓글이 아직 없습니다");
            return;
        }
        for (int i = 0; i < replyList.size(); i++) {
            ReplyDTO temp = replyList.get(i);
            System.out.printf("%d. %s : %s\n", temp.getId(), userController.selectNicknameById(boardId), temp.getContent());
        }
    }

    public void showMenu(int boardId) {

        String message = "1. 댓글 작성 2. 댓글 수정/삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

        if (userChoice == 1) {
            leaveComment(boardId);
        } else if (userChoice == 2) {
            editComment();
        } else if (userChoice == 3) {

        }
    }

    private void leaveComment(int boardId) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setWriterId(logIn.getId());
        replyDTO.setBoardId(boardId);

        String message = "댓글을 입력해주세요";
        replyDTO.setContent(ScannerUtil.nextLine(scanner, message));

        replyController.insert(replyDTO);
    }

    private void editComment() {
        String message = "편집할 댓글의 번호를 입력하세요(뒤로가기 0)";
        int num = ScannerUtil.nextInt(scanner, message);
        while (!replyController.isValidId(num)) {
            message = "유효한 번호를 입력해주세요";
            num = ScannerUtil.nextInt(scanner, message);
        }

        ReplyDTO replyDTO = replyController.selectOne(num);
        if (replyDTO.getId() != logIn.getId()) {
            System.out.println("권한이 없습니다");
        } else {
            message = "1. 수정 2. 삭제";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);

            if (userChoice == 1) {
                update(num);
            } else if (userChoice == 2) {
                delete(num);
            }
        }
    }

    private void update(int id) {
        ReplyDTO replyDTO = replyController.selectOne(id);
        String message = "댓글을 입력해주세요";
        replyDTO.setContent(ScannerUtil.nextLine(scanner, message));

        replyController.update(replyDTO);
    }

    private void delete(int id) {
        replyController.delete(id);
    }
}
