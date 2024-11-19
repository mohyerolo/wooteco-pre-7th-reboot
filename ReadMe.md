# 우테코 프리코스 7기 다시 해보기

## 첫 번째 미션: 계산기

[1주 차 미션 진행 깃허브](https://github.com/mohyerolo/java-calculator-7/tree/mohyerolo)

### 목표

1. MVC 패턴 적용
2. 클래스, 메서드 역할 분리
3. 객체 지향

### 구현 기능

- 문자열 분리
    - 쉼표(,) 또는 콜론(:) 을 기준으로 분리
    - 커스텀 구분자 지정 가능: "//"와 "\n" 사이에 위치시키는 문자가 커스텀 구분자가 됨
- 분리된 문자를 숫자로 변환 후 각 숫자의 합을 반환
- 잘못된 값이 들어오면 IllegalArgumentException을 발생시키고 애플리케이션 종료

### 구현 순서

1. 문자 받기
2. 문자 형태 검사
3. 문자 분리
4. 분리된 문자 검사
5. 분리된 문자 숫자 변환, 합 계산

## 두 번째 미션: 자동차 경주 게임

[2주 차 미션 진행 깃허브](https://github.com/mohyerolo/java-racingcar-7/tree/mohyerolo)

### 구현 기능

- 자동차 이름들 입력
- 자동차 이름 분리
    - 쉼표를 기준으로 분리
    - 5자 이하만 가능
- 몇 번 이동할건지 입력
- 0-9사이의 랜덤값에서 4 이상이면 전진
- 한 명 이상의 우승자 발표
    - 여러 명일 경우 쉼표로 구분

### 처음 구현했던 것과 달라진 점

1. 서비스에서 뷰에 접근하던 기존 코드와 달리 이번에는 서비스는 비즈니스 로직만 담당하도록 구현
2. 출력 코드를 컨트롤러에서 담당하도록 해서 컨트롤러의 역할에 집중
3. 기존에는 InputParser에서 자동차 객체 생성을 했는데 Parser는 Parse의 역할만 하고 객체 생성은 도메인이나 Factory에서 담당

- 기존

```java
public class InputParser {
    public List<Car> parseRacingCarList(final Input input) {
        List<Car> carList = splitCarsToList(input.getInput());
        carNameValidator.validateCarNamesUnique(carList);
        return carList;
    }

    private List<Car> splitCarsToList(final String cars) {
        String[] carNames = cars.split(DELIMITER);
        return createCarList(carNames);
    }

    private List<Car> createCarList(final String[] carNames) {
        List<Car> carList = new ArrayList<>();
        for (String carName : carNames) {
            carNameValidator.validateCarNameLength(carName);
            carList.add(Car.create(carName));
        }
        return carList;
    }

    public int parseTryCount(final Input input) {
        int count = parseInteger(input.getInput());
        tryCountValidator.validateTryCountGreaterZero(count);
        return count;
    }

    private int parseInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(GameErrorMessage.WRONG_TYPE_TRY_COUNT.getValue());
        }
    }
}
```

- 변경 후: 파서에서는 분리와 파싱만, Cars 도메인에서 정적 팩토리 메서드 패턴으로 CarsFactory를 이용해 생성

```java
public class InputParser {
    private static final String DELIMITER = ",";
    private static final String NUMBER_ERROR = "시도 횟수는 숫자여야합니다.";

    public static String[] splitCarNames(String carNames) {
        return carNames.split(DELIMITER);
    }

    public static int parseCount(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_ERROR);
        }
    }
}

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final String carNames) {
        return new Cars(CarsFactory.createCars(carNames));
    }
}

public class CarsFactory {
    public static List<Car> createCars(final String carNames) {
        String[] splitCarNames = InputParser.splitCarNames(carNames);
        validateCar(splitCarNames);
        return createEachCar(splitCarNames);
    }

    private static void validateCar(final String[] splitCarNames) {
        CarValidator.validateBlank(splitCarNames);
        CarValidator.validateDuplicate(splitCarNames);
    }

    private static List<Car> createEachCar(final String[] splitCarNames) {
        return Arrays.stream(splitCarNames)
                .map(Car::new)
                .toList();
    }
}
```

4. 인터페이스 사용으로 자동차 경주 번호를 불러오는 방법에 유연성과 확장성 증가, Randoms에 대한 의존성 감소

- 기존

```java
public void playOneRound() {
    for (Car car : carList) {
        int randomNum = Randoms.pickNumberInRange(0, 9);
        car.moveOrNothing(randomNum);
    }
}
```

- 변경
    - MoveGenerator -> RandomNumGenerator
    - Cars에서 MoveGenerator를 매개변수로 받아 의존성 감소

```java
 public void playGame(final MoveGenerator moveGenerator) {
    for (Car car : cars) {
        int randomNum = moveGenerator.generateNumber(MIN_NUM, MAX_NUM);
        car.moveOrNot(randomNum);
    }
}
```

5. Cars 객체에서 문자열 형식을 작업하는 게 아닌 객체를 반환하도록 변경해 MVC 패턴에서 도메인 계층의 역할에 맞도록 수정, 단일 책임 원칙도 지켜짐

- 기존

```java
public String getWinners() {
    List<String> winnerList = extractWinnerList();
    return String.join(", ", winnerList);
}

private List<String> extractWinnerList() {
    int highestScore = getHighestScore();
    return createWinnerList(highestScore);
}

private int getHighestScore() {
    return carList.stream()
            .mapToInt(Car::getMoveCnt)
            .max()
            .orElse(0);
}

private List<String> createWinnerList(final int highestScore) {
    return carList.stream()
            .filter(c -> c.getMoveCnt() == highestScore)
            .map(Car::getName)
            .toList();
}
```

- 변경

```java
public List<Car> findWinners() {
    int highestMoveCnt = getWinningMoveCnt();
    return cars.stream()
            .filter(car -> car.isWinner(highestMoveCnt))
            .toList();
}

private int getWinningMoveCnt() {
    return cars.stream()
            .mapToInt(Car::getMoveCnt)
            .max()
            .orElse(0);
}
```

## 세 번째 미션: 로또

## 네 번째 미션: 편의점
