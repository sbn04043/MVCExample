package main;

//자바에서 시간/날짜 다루기
//자바에서의 시간은 그리치니 표준 시간대 1970년 1월 1일 0시 0분 0초 기준
//이후 시간대는 +, 이전 시간대는 -로 Long 데이터 타입을 기준으로 표시
//한국은 + 9. 그렇게 세부적으로 알 필요는 없다.
//java.util.Date를 사용해 현재 시간을 저장/변경 하고
//SimpleDateFormat을 사용해 출력하는 방법을 알아보자

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeMain {
    public static void main(String[] args) {
        //java.util.Date 객체 만들기
        Date date = new Date();
        //date 출력
        System.out.println(date);

        //현재 시간을 바꿀 때
        date.setHours(20);
        System.out.println(date);

        //표시되는 형식을 바꾸고 싶다면
        //SimpleDateFormat을 사용하는데
        //생성자의 파라미터로 우리가 원하는 형식을 넣는다.
        //y: 연도, M: 월, d: 일
        //h: 12시간 체제의 시각
        //H: 24시간 체제의 시각
        //m: 분, s: 초, S:밀리초
        //E: 요일

        //####-##-## ##:##:## 형식으로 연월일 시분초 출력
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E요일 HH:mm:ss");

        //simpleDateFormat의 객체를 통해 date 출력
        System.out.println(simpleDateFormat.format(date));

        //또한 SimpleDateFormat을 사용하면 String을 시간으로 변환하는 것도 가능하다.
        simpleDateFormat = new SimpleDateFormat("yyMMdd HHmmss");
        //lenient: 관대한 -> 데이터의 값이 벗어나면 오류 발생 ex) 12월 40일 등
        simpleDateFormat.setLenient(false);
        String time = "981230 233030";
        try {
            date = simpleDateFormat.parse(time);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
























