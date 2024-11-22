# 우테코 프리코스 7기 다시 해보기

## 목차

[1. 첫 번째 미션: 계산기](#첫-번째-미션-계산기)  
[2. 두 번째 미션: 자동차 경주 게임](#두-번째-미션-자동차-경주-게임)  
[2-1. 처음 구현했던 것과 달라진 점](#처음-구현했던-것과-달라진-점)  
[3. 세 번째 미션: 로또](#세-번째-미션-로또)
[3-1. 처음 구현했던 것과 달라진 점](#처음-구현했던-것과-달라진-점-1)

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

<div align="right">

[목차](#목차)

</div>

## 세 번째 미션: 로또

[3주 차 미션 진행 깃허브](https://github.com/mohyerolo/java-lotto-7/tree/mohyerolo)

### 구현 기능

- 1000원 단위로 로또 구매 (1000원 - 로또 번호 6개)
- 1-45 사이의 6개 랜덤 숫자를 구매한 로또 개수만큼 발행
- 사용자에게 로또 당첨 번호 1-45 사이의 6개와 보너스 번호 한 개 입력 받기
- 발행됐던 로또 번호들과 당첨 번호, 보너스 번호로 3개/ 4개/ 5개/ 5개 + 보너스/ 6개 일치 개수 계산
- 수익률 계산 (소수점 둘째 자리에서 반올림)
- 5개의 로또 당첨 결과와 총 수익률 출력

### 처음 구현했던 것과 달라진 점

- 도메인을 단순 데이터 보관 용도가 아닌 비즈니스 로직도 담당하는 도메인의 역할로 사용 + 객체 간 협력 사용
- 도메인의 생성자는 검증된 데이터를 받아들이고 정적 팩토리 메서드 사용으로 데이터 캡슐화
    - 기존에는 Lotto에서 두 개의 생성자를 통해 도메인을 생성했지만 팩토리 메서드 패턴을 이용해 하나의 인터페이스를 구현한 구현체들을 받아 생성할 수 있도록 해 유연성 및 확장성 증가
- 컨틀로러와 뷰, 서비스의 역할 분배
- 서비스가 컨틀로러와 도메인을 이어주는 역할로 존재하는 게 아닌 여러 도메인을 이용해 비즈니스 흐름을 돕도록 사용
- 데이터 생성 책임을 서비스가 아닌 Factory 클래스로 분리

1. 구입 금액 관리
    - 기존: 구입 금액과 개수를 도메인으로 받고 검증하는 기능으로만 사용함
    ```java
    public class PurchasePrice {
        // 생략
        private final int purchasePrice;
        private final int quantity;
    
        public PurchasePrice(final int purchasePrice) {
            validatePurchasePrice(purchasePrice);
            this.purchasePrice = purchasePrice;
            quantity = calculateQuantity();
        }
        // getter ...    
        private void validatePurchasePrice(final int purchasePrice) {
            validateAvailableNum(purchasePrice);
            validatePriceUnit(purchasePrice);
        }
    
        private void validateAvailableNum(final int purchasePrice) {
            if (purchasePrice <= ZERO) {
                throw new IllegalArgumentException(PRICE_MUST_BIGGER_THAN_ZERO);
            }
        }
    
        private void validatePriceUnit(final int purchasePrice) {
            if (purchasePrice % PurchasePrice.UNIT != ZERO) {
                throw new IllegalArgumentException(PRICE_MUST_1000_UNIT);
            }
        }
    
        private int calculateQuantity() {
            return purchasePrice / UNIT;
        }
    }
    ```
    - 변경: 구입 금액은 구입 단위와 구입된 로또 개수로 알 수 있으므로 따로 저장하지 않음
   ```java
   // LottoController
   private LottoTickets issueLottoTickets() {
       return executeWithRetry(() -> {
           int purchasedAmount = inputView.readPurchasedAmount();
           return LottoTickets.from(purchasedAmount, new RandomLottoGenerator());
       });
   }

   // LottoTickets
   public class LottoTickets {
        private static final int purchaseUnit = 1000;
    
        private final List<Lotto> purchasedLottos;
    
        private LottoTickets(final List<Lotto> purchasedLottos) {
            this.purchasedLottos = purchasedLottos;
        }
    
        public static LottoTickets from(final int purchasedAmount, final LottoGenerator lottoGenerator) {
            List<Lotto> lottos = LottoTicketsFactory.createLottos(purchasedAmount, purchaseUnit, lottoGenerator);
            return new LottoTickets(lottos);
        }
        // 다른 비스니스 로직...
   }
   ```

2. 잘못된 값 들어오면 재시도
    - 기존: InputService에서 InputView에 접근해서 재시도 하게 함
    - 변경: View에 접근해야되므로 컨트롤러에서 접근

3. 구입한 로또들 관리 도메인
    - 기존: 로또 리스트 보관 용도로만 사용
    ```java
   public class PurchasedLotto {
        private final List<Lotto> purchasedLottos;

        public PurchasedLotto(final List<Lotto> lottos) {
            this.purchasedLottos = lottos;
        }

        public List<Lotto> getPurchasedLottos() {
            return purchasedLottos;
        }
   }
    ```
    - 변경: 구입한 로또 리스트 보관 및 관련 비즈니스 로직 실행
    ```java
    package org.wooteco.pre.lotto.domain;
    // import 생략
    public class LottoTickets {
        private static final int purchaseUnit = 1000;
    
        private final List<Lotto> purchasedLottos;
    
        private LottoTickets(final List<Lotto> purchasedLottos) {
            this.purchasedLottos = purchasedLottos;
        }
    
        public static LottoTickets from(final int purchasedAmount, final LottoGenerator lottoGenerator) {
            List<Lotto> lottos = LottoTicketsFactory.createLottos(purchasedAmount, purchaseUnit, lottoGenerator);
            return new LottoTickets(lottos);
        }
    
        public void compareWinningLotto(final Map<Match, Integer> matchCountData, final WinningLottos winningLottos, final int minMatchCount) {
            // 당첨 로또와 구매한 로또 비교해서 결과 저장
        }
    
        public double calcReturnRate(final int totalPrize) {
            return (double) totalPrize / (purchasedLottos.size() * purchaseUnit) * 100;
        }
    
        public List<Lotto> getPurchasedLottos() {
            return purchasedLottos;
        }
    
        private boolean isContainBonus(final int count, final WinningLottos winningLottos, final Lotto lotto) {
            // 로또가 보너스 번호를 포함할 수 있고, 포함하고 있는지 확인
        }
    }
    ```

4. 로또 결과 확인 로직
    - 기존: LottoController -> LottoService -> LottoResultCalculator, 도메인들은 확인 용도로 쓰였음
   ```java
    public class DefaultLottoResultCalculator implements LottoResultCalculator {
    private static final int MIN_MATCH_COUNT = 3;
    
        @Override
        public LottoResult calculateResult(final List<Lotto> purchasedLottos, final WinningLotto winningLotto) {
            Map<WinningResult, Integer> results = calcPurchasedAndWinning(purchasedLottos, winningLotto);
            int totalAmount = PrizeCalculator.calcTotalPrizeAmount(results);
            return new LottoResult(results, totalAmount);
        }
    
        private Map<WinningResult, Integer> calcPurchasedAndWinning(final List<Lotto> purchased, final WinningLotto winning) {
            Map<WinningResult, Integer> results = initializeResults();
            for (Lotto lotto : purchased) {
                updateLottoResult(results, lotto, winning);
            }
            return results;
        }
    
        private Map<WinningResult, Integer> initializeResults() {
            Map<WinningResult, Integer> results = new EnumMap<>(WinningResult.class);
            Arrays.stream(WinningResult.values()).forEach(result -> results.put(result, 0));
            return results;
        }
    
        private void updateLottoResult(final Map<WinningResult, Integer> results, final Lotto lotto, final WinningLotto winningLotto) {
            int matchCount = winningLotto.countMatches(lotto);
            if (matchCount < MIN_MATCH_COUNT) {
                return;
            }
    
            WinningResult result = findResultAsMatchCount(matchCount, winningLotto.containsBonusNum(lotto));
            results.put(result, results.get(result) + 1);
        }
    
        private WinningResult findResultAsMatchCount(final int matchCount, final boolean isBonusMatch) {
            if (matchCount == 5 && isBonusMatch) {
                return WinningResult.BONUS;
            }
            return WinningResult.fromMatchCount(String.valueOf(matchCount));
        }
    }
    ```
    - 변경: 도메인이 도메인의 역할을 할 수 있게 관련 비즈니스 로직을 담당하게 함 + 객체 간 협력 사용
        - LottoController -> LottoService -> LottoTickets 도메인 사용 (WinningLotto라는 당첨 번호,보너스 번호가 있는 도메인을 매개변수로)
    ```java
    // service/LottoService.class
    public class LottoService {
    
        public LottoResult checkLottoResult(final LottoTickets lottoTickets, final WinningLottos winningLottos) {
            Map<Match, Integer> matchCountData = Match.initilizeMatchMap();
            lottoTickets.compareWinningLotto(matchCountData, winningLottos, Match.getMinMatchCount());
            double returnRate = calcReturnRate(matchCountData, lottoTickets);
            return new LottoResult(matchCountData, returnRate);
        }
    
        private double calcReturnRate(final Map<Match, Integer> matchCountData, final LottoTickets lottoTickets) {
            int totalPrize = sumTotalPrize(matchCountData);
            return lottoTickets.calcReturnRate(totalPrize);
        }
    
        private int sumTotalPrize(final Map<Match, Integer> matchCountData) {
            return matchCountData.entrySet().stream()
                    .mapToInt(entry ->
                            entry.getKey().getPrize() * entry.getValue())
                    .sum();
        }
    }
    
    // domain/LottoTickets.class
    public void compareWinningLotto(final Map<Match, Integer> matchCountData, final WinningLottos winningLottos, final int minMatchCount) {
        for (Lotto lotto : purchasedLottos) {
            int count = winningLottos.checkLottosMatchCount(lotto);
            boolean containBonus = isContainBonus(count, winningLottos, lotto);
            if (count >= minMatchCount) {
                Match match = Match.from(count, containBonus);
                matchCountData.put(match, matchCountData.get(match) + 1);
            }
        }
    }
    
    public double calcReturnRate(final int totalPrize) {
        return (double) totalPrize / (purchasedLottos.size() * purchaseUnit) * 100;
    }
    
    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }
    
    private boolean isContainBonus(final int count, final WinningLottos winningLottos, final Lotto lotto) {
        if (count != Match.FIVE_BONUS.getMatchCount()) {
            return false;
        }
        return winningLottos.isBonusNumMatch(lotto);
    }
    ```

<div align="right">

[목차](#목차)

</div>

## 네 번째 미션: 편의점

<div align="right">

[목차](#목차)

</div>
