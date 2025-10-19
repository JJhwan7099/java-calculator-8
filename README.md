# java-calculator-precourse

---
## **입출력 요구사항**
- 입력
  - 구분자와 양수로 구성된 문자
- 출력
  - 덧셈 결과
  - 실행 결과 예시
  ```
  덧셈할 문자열을 입력해 주세요.
  1,2:3
  결과 : 6
  ```

## 동작 흐름
```
사용자 입력 
-> 커스텀 구분자 존재 유무 파악 
-> 커스텀 구분자가 있다면 추출 
-> 커스텀 구분자 지정 문자열을 제외한 문자열 추출 
-> 커스텀 구분자, 쉼표(,) 또는 콜론(:)을 기준으로 분리 
-> 합 계산 
```
## 고려 사항
- 구분자로 '-'가 오게 되었을때 음수 처리를 해야하는지?
    - 입출력 요구사항에 구분자와 양수로 구성된 문자로 되어있으므로 양수만 있다고 가정합니다.
      - 커스텀 구분자로 '-'가 등록되지 않아도 음수로 판별할 수 있으므로 검증 필요!

## 기능 목록
- [x] 쉼표(,) 혹은 콜론(:)을 구분자로 문자열을 분리하는 함수 구현.
- [x] 커스텀 구분자 존재 여부를 판단하는 함수 구현. 
- [x] 커스텀 구분자를 추출하는 함수 구현.
- [x] 커스텀 구분자 지정 문자열을 제외한 문자열 반환 함수 구현.
- [x] 구분자들을 기준으로 분리하고 배열에 저장하는 함수 구현.
- [x] 사용자 입력값 검증 함수 구현.

## 검증 사항
- 커스텀 구분자 지정시 //+"커스텀구분자"+\n 형태인지 확인.
- 커스텀 구분자 및 쉼표, 콜론으로 분리 후 숫자만 존재하는지 확인.
- 분리된 숫자들이 양수인지 확인. (분리된 숫자가 양수라고 하더라도 '+' 기호가 있으면 예외로 처리)

## 고민 내용
- Application.java에 모든 로직이 몰려있어서 책임 혹은 역할 분배를 하는것이 좋을까?
  - MVC패턴에 맞춰서 구조를 변경.
  - 
    |클래스|역할|
    | - |--|
    | **Application** | 프로그램 시작, Controller 실행 |
    | **CalculatorController** | 흐름 제어 (View <=> Model 연결) |
    | **Calculator** | 실제 계산 로직 (Model) |
    | **InputParser** | 입력값 파싱 로직 (Model) |
    | **InputView** | 사용자 입력 담당 |
    | **OutputView** | 출력 담당 |
    | **Validator** | 유효성 검사 (음수, 잘못된 입력 등) |

- Calculator에서 String 형태의 숫자를 int로 변경하면서 숫자가 아닐때 발생하는 NumberForMatException을 예외처리 하는데, 이 역할을 validator에서 수행하는게 맞을지?
  ```java
  // String 형태의 숫자를 int형으로 변환하는 부분
  public int calculate(String[] numbers) {
        int result = 0;
        for (String number : numbers) {
            try{
                result += Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력값이 잘못되었습니다.");
            }
        }
        return result;
    }
  ```
  - 만약 Validator에서 이 과정을 수행한다면 Integer.parseInt()롤 실행해야하는데, Validator와 Calculator에서 Integer.parseInt()를 각각 수행하게 되면, 같은 과정을 2번 실행해야하는 상황이 발생함.
  - Validator 클래스에서 Integer.parseInt()를 수행하고 변경된 정수를 반환한다면 Validator의 역할을 벗어나는것이 아닐까?
  - 모든 역할을 확실하게 나누는 것은 상황에 따라 불가능할 수도 있다는 결론. 따라서 위 방식을 유지하자!

## 트러블 슈팅
- split() 함수를 실행결과 문자열 마지막에 구분자가 있을때 배열의 마지막 항목에 빈값이 추가되지 않아서 검증이 어려운 상황이 발생함.
    <img width="388" height="142" alt="image" src="https://github.com/user-attachments/assets/ed811c62-d4cd-4c82-985c-d5489fd521b1" />
    - split() 함수 두번째 인자에 -1을 입력하자!
      - String[] result = str.split(regex, limit);
      - limit값이 0보다 작다면, 최대 limit - 1번까지 분리, 남은 문자열은 마지막 요소에 포함!
    - 결과 <br>
      <img width="761" height="65" alt="image" src="https://github.com/user-attachments/assets/16d30357-80fd-44d6-97a1-a7ad26b4b136" />

