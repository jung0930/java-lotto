package lotto.domain;

import lotto.domain.exception.NumberCountException;
import lotto.domain.exception.NumberDuplicateException;

import java.util.*;
import java.util.stream.Collectors;

public final class Lotto {

    private static final int LIMIT_SIZE = 6;
    private static final String SEPARATOR = ",";
    private static final String WHITE_SPACE = " ";
    private static final String BLANK = "";

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(final List<Integer> numbers) {
        addLottoNumber(numbers);
    }

    public Lotto(final String numbers) {
        String[] dividedNumbers = splitNumbers(numbers);
        addLottoNumber(Arrays
                .stream(dividedNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private void validateNumbers(final List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty() || numbers.size() != LIMIT_SIZE) {
            throw new NumberCountException();
        }
        if (validateDuplicate(numbers)) {
            throw new NumberDuplicateException();
        }
    }

    private boolean validateDuplicate(final List<Integer> numbers) {
        Set<Integer> numbersSet = new HashSet<>(numbers);
        return numbersSet.size() != LIMIT_SIZE;
    }

    private void addLottoNumber(final List<Integer> numbers) {
        validateNumbers(numbers);
        for (int number : numbers) {
            lottoNumbers.add(LottoNumberFactory.getLottoNumber(number));
        }
    }

    private String[] splitNumbers(final String numbers) {
        return numbers.replace(WHITE_SPACE, BLANK).split(SEPARATOR);
    }

    public MatchingCount getMatchingCount(final Lotto lotto) {
        MatchingCount matchingCount = new MatchingCount();

        for (LottoNumber lottoNumber : lottoNumbers) {
            matchingCount = lotto.addMatchingCount(lottoNumber, matchingCount);
        }

        return matchingCount;
    }

    private MatchingCount addMatchingCount(final LottoNumber lottoNumber, final MatchingCount matchingCount) {
        if (hasLottoNumber(lottoNumber)) {
            return matchingCount.increment();
        }
        return matchingCount;
    }

    public boolean hasLottoNumber(final LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

}
