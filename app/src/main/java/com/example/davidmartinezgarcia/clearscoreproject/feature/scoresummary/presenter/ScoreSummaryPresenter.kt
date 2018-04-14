package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.presenter

import com.example.davidmartinezgarcia.clearscoreproject.feature.common.presenter.BasePresenter
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.ScoreSummaryContract
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository.ScoreSummaryRepositoryInterface
import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

/**
 * Created by david.martinezgarcia on 12/04/2018.
 */
class ScoreSummaryPresenter(private var view : ScoreSummaryContract.View ,
                            private var scoreSummaryRepository: ScoreSummaryRepositoryInterface) : BasePresenter(), ScoreSummaryContract.Presenter {

    private var disposable : CompositeDisposable = CompositeDisposable()

    override fun retrieveScore() {
        disposable.add(scoreSummaryRepository.retrieveScores()
                .subscribe(Consumer<ScoreSummary> { scoreSummary ->
                    if (scoreSummary != null) {
                        view.showScore(scoreSummary)
                    }
                }, ApiErrorAction(view)))
    }

    override fun onViewCreated() {
    }

    override fun onViewDestroyed() {
        disposable.clear()
    }
}