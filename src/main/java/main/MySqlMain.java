package main;

import model.BoardDTO;

import java.sql.*;
import java.util.ArrayList;

//MySQL과 자바 연동
public class MySqlMain {
    public static void main(String[] args) {
        //메소드를 선언할 때, 메소드 이름과 파라미터 뒤에
        //throws Exception이라고 적어준다.
        //이 메소드를 실행할 때, 어떤 오류가 발생할수 있다고
        //미리 경고를 하는 것이다.
        //추후에 실제 해당 메소드를 사용할 때에는
        //아래와 같이 try/catch 구조를 사용하여
        //해당 오류가 발생했을 때 어떻게 처리할지 적는다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/board";
            String username = "root";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection 성공");

            //1. 현재 board 테이블의 내용을 전부 뽑기
            ArrayList<BoardDTO> list = new ArrayList<>();
            String query = "SELECT * FROM board";

            //위에서 만든 Connection 객체를 통해서 쿼리를 보낼 때에는
            //PreparedStatement 객체를 통해 스트링 쿼리를 보낸다.
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //SELECT 쿼리와 같이 PreparedStatement 결과가 존재할 경우
            //그 결과를 ResultSet에 담는다.
            ResultSet resultSet = preparedStatement.executeQuery();

            //resultSet은 결과를 추출하기 위해
            //반드시 resultSet.next()라는 메소드를 통해
            //커서의 위치를 while문을 통해 다음으로 옮겨야 한다.
            //가끔 한개만 있을 경우 if문을 사용하기도 한다.
            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();

                //ResultSet 객체의 현재 위치의 값을 꺼낼 때에는
                //데이터 타입에 맞추어서 다음 메소드를 실행하면 된다.
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                boardDTO.setModifyDate(resultSet.getTimestamp("modify_date"));
                //리스트에 객체 추가
                list.add(boardDTO);
            }

            //리스트 출력
            for (BoardDTO b : list) {
                System.out.println(b);
            }

            //2. 특정 ID 값을 가진 로우 하나 불러오기
            //어떤 값을 쿼리에 넣어야 하는 경우, 해당 자리에 ? 를 넣는다.
            query = "SELECT * FROM board WHERE id = ?";

            //preparedStatement 준비
            preparedStatement = connection.prepareStatement(query);

            //? 자리에 원하는 값을 넣기
            //데이터타입에 맞춰 메소드를 호출한다.
            //1번 ? 자리에 3을 넣어라.
            preparedStatement.setInt(1, 3);

            //위의 값 resultSet에 저장
            resultSet = preparedStatement.executeQuery();

            //while문을 통해 쿼리 결과값 BoardDTO 객체에 저장
            BoardDTO temp = new BoardDTO();
            while (resultSet.next()) {
                temp.setId(resultSet.getInt("id"));
                temp.setTitle(resultSet.getString("title"));
                temp.setContent(resultSet.getString("content"));
                temp.setWriterId(resultSet.getInt("writer_id"));
            }

            //temp 출력
            System.out.println(temp);

            //3. INSERT(CREATE), ROW 만들기
            //BoardDTO 객체에 값을 넣고 데이터베이스에 데이터 넣기
            query = "INSERT INTO board(title, content, writer_id) VALUES(?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);

            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setTitle("지금은 11시 18분");
            boardDTO.setContent("예제를 내줘야지");
            boardDTO.setWriterId(1);

            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getWriterId());

            //insert, update, delete 쿼리는
            //excuteUpdate()를 한다.
            //preparedStatement.executeUpdate();

            //4. UPDATE
            //4번 글의 내용 수정
            BoardDTO boardDTO2 = new BoardDTO();
            boardDTO2.setId(4);
            boardDTO2.setTitle("수정된 제목");
            boardDTO2.setContent("id: 4 게시글이 수정됐습니다");

            query = "UPDATE board SET title = ?, content = ?, modify_date = NOW() WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, boardDTO2.getTitle());
            preparedStatement.setString(2, boardDTO2.getContent());
            preparedStatement.setInt(3, boardDTO2.getId());

            //preparedStatement.executeUpdate();

            //5. DELETE
            int id = 5;
            query = "DELETE FROM board WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            //preparedStatement.executeUpdate();

        } catch (Exception e) {
            //오류가 발생하면 그 오류의 내역 출력
            e.printStackTrace();
        }
    }
}
