package lotto.domain;

public class WinningLotto extends Lotto{

    private static final int INIT_COUNT = 0;

    public WinningLotto(String input) {
        super(input);
    }

    public int getMatchingLottoNumberCount(Rank rank, Lottos purchasedLottos) {
        int matchingCount = INIT_COUNT;
        int hitsCount = INIT_COUNT;

        for(Lotto lotto : purchasedLottos.getLottos()) {
            hitsCount = countHit(lotto);
            matchingCount += isEqualsHitsCounts(rank, hitsCount);
        }

        return matchingCount;
    }

    private int countHit(Lotto lotto) {
        int hitsCount = INIT_COUNT;

        for(LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            hitsCount += countContains(lottoNumber);
        }

        return hitsCount;
    }

    private int countContains(LottoNumber lottoNumber) {
        int hitsCount = INIT_COUNT;

        if(this.getLottoNumbers().contains(lottoNumber)) {
            hitsCount++;
        }

        return hitsCount;
    }

    private int isEqualsHitsCounts(Rank rank, int hitsCount) {
        int matchingCount = INIT_COUNT;

        if(rank.getHitsCount() == hitsCount) {
            matchingCount++;
        }

        return matchingCount;
    }

}
