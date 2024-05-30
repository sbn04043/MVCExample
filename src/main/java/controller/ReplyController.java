package controller;

import model.ReplyDTO;

import java.util.ArrayList;

public class ReplyController {
    ArrayList<ReplyDTO> list;
    int nextId;

    public ReplyController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(ReplyDTO replyDTO) {
        replyDTO.setId(nextId++);
        list.add(replyDTO);
    }

    public ReplyDTO selectOne(int id) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(id);
        return list.get(list.indexOf(replyDTO));
    }

    public ArrayList<ReplyDTO> selectAll() {
        return list;
    }

    public void update(ReplyDTO replyDTO) {
        list.set(replyDTO.getId(), replyDTO);
    }

    public void delete(int id) {
        ReplyDTO replyDTO = selectOne(id);

        list.remove(replyDTO);
    }

    public ArrayList<ReplyDTO> selectByBoardId(int boardId) {
        ArrayList<ReplyDTO> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ReplyDTO replyDTO = list.get(i);
            if (boardId == replyDTO.getBoardId())
                tempList.add(replyDTO);
        }
        return tempList;
    }

    public boolean isValidId(int id) {
        if (id == 0) return true;

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(id);
        return list.contains(replyDTO);
    }
}
