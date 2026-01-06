# Ticket System (Console)

`Core Java` 학습 중 **클래스/객체(OOP)** 개념을 연습하기 위해 만든 콘솔 기반의 간단한 좌석(티켓) 예약 시스템입니다.

## Features
- 좌석 상태 조회(예약/빈 좌석)
- 좌석 예약 (이름 + 행/열 입력)
- 예약 취소 (이름/좌석 기반)
- 입력값 검증(범위 체크, 이미 예약된 좌석 처리 등)


## How to Run
### IntelliJ
1. 이 저장소를 IntelliJ로 엽니다.
2. `project/src/ticket/system` 아래의 `main()`이 있는 클래스를 찾습니다.
3. 해당 파일에서 **Run** 실행

### Terminal (선택)
- `javac` / `java`로 실행하려면, `main()`이 있는 클래스 기준으로 컴파일/실행하세요.

## Example Input
프로그램 안내에 따라 아래처럼 입력합니다.

- 공백 구분 입력 예시: `jav 1 2`
- 또는 콤마 구분 입력 예시: `jav, 1, 2`

(어떤 형식을 받는지는 코드의 `Scanner` 처리 방식에 따라 달라집니다.)