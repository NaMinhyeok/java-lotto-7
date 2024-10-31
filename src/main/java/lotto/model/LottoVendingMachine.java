package lotto.model;

import lotto.exception.GameException;
import lotto.provider.NumbersProvider;

import java.util.List;
import java.util.stream.IntStream;

public class LottoVendingMachine {

    public static final int LOTTO_PRICE = 1000;

    private final int cost;

    public LottoVendingMachine(int cost) {
        validate(cost);
        this.cost = cost;
    }

    public List<Lotto> purchaseAll(NumbersProvider numbersProvider) {
        return IntStream.range(0, getAvailableQuantity())
            .mapToObj(i -> new Lotto(numbersProvider.getNumbers()))
            .toList();
    }

    public int getAvailableQuantity() {
        return cost / LOTTO_PRICE;
    }

    private void validate(int cost) {
        if (cost < LOTTO_PRICE) {
            throw new GameException("로또 구입 금액은 %s원 이상이어야 합니다.".formatted(LOTTO_PRICE));
        }
        if (hasRemain(cost)) {
            throw new GameException("로또 구입 금액은 %s원 단위로 입력해야 합니다.".formatted(LOTTO_PRICE));
        }
    }

    private boolean hasRemain(int cost) {
        return cost % LOTTO_PRICE != 0;
    }

}
