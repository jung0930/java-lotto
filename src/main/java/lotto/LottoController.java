package lotto;

import lotto.domain.*;
import lotto.domain.generationStrategy.AutoNumberGenerationStrategy;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;
import java.util.Scanner;

public final class LottoController {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputView inputView = new InputView(scanner);
            ResultView resultView = new ResultView();
            LottoMachine lottoMachine = new LottoMachine();

            Money money = new Money(inputView.inputAmount());

            int manualLottoCount = inputView.inputLottoCountToPurchaseManually();
            List<String> manuallyLottos = inputView.inputNumberOfLottoToPurchaseManually(manualLottoCount);

            Lottos lottos = lottoMachine.buyLotto(money, manuallyLottos, new AutoNumberGenerationStrategy());

            resultView.printLottoCount(lottos.count(), manualLottoCount);
            resultView.printLottos(lottos);

            Lotto winningLotto = new Lotto(inputView.inputWinningNumbers());
            LottoNumber bonusNumber = new LottoNumber(inputView.inputBonusNumber());

            WinningsStatistics winningsStatistics = new WinningsStatistics(winningLotto, bonusNumber);
            Result result = winningsStatistics.makeStatisticsWinnings(lottos);

            resultView.printStatistics(result);
            resultView.printEarningsRate(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
