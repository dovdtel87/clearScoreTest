package com.example.davidmartinezgarcia.clearscoreproject.presenter

import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.ScoreSummaryContract
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.presenter.ScoreSummaryPresenter
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository.ScoreSummaryRepositoryInterface
import com.example.davidmartinezgarcia.clearscoreproject.model.CreditReportInfo
import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by david.martinezgarcia on 14/04/2018.
 */
class ScoreSummaryPresenterTest {

    @Mock
    private lateinit var view: ScoreSummaryContract.View
    @Mock private lateinit var scoreSummaryRepository: ScoreSummaryRepositoryInterface

    private lateinit var presenter: ScoreSummaryContract.Presenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ScoreSummaryPresenter(view, scoreSummaryRepository)
    }

    @Test
    fun shouldRetrieveScoreSummary() {
        val creditReportInfo = CreditReportInfo(100, 700)
        val scoreSummary = ScoreSummary("PASS", creditReportInfo)

        `when`(scoreSummaryRepository.retrieveScores()).thenReturn(Observable.just(scoreSummary))

        presenter.retrieveScore()

        val inOrder = inOrder(view)
        inOrder.verify<ScoreSummaryContract.View>(view).showProgress()
        inOrder.verify<ScoreSummaryContract.View>(view).hideProgress()
        inOrder.verify<ScoreSummaryContract.View>(view).showScore(scoreSummary)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun shouldThrowApiException() {
        val exception = Throwable("Api error")

        `when`(scoreSummaryRepository.retrieveScores()).thenReturn(Observable.error(exception))

        presenter.retrieveScore()

        val inOrder = inOrder(view)
        inOrder.verify<ScoreSummaryContract.View>(view).showProgress()
        inOrder.verify<ScoreSummaryContract.View>(view).onApiError(exception)
        inOrder.verifyNoMoreInteractions()
    }
}