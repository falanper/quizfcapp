package com.falanper.jogodostimes

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.falanper.jogodostimes.databinding.ActivityQuizzQuestionsBinding

class QuizzQuestionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizzQuestionsBinding
    private lateinit var mQuestionsList: ArrayList<Question>
    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizzQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUsername = intent.getStringExtra(Constants.USERNAME)
        mQuestionsList = Constants.getQuestions()
        setQuestion()

        with(binding) {

            textViewOptionOne.setOnClickListener {
                selectedOptionView(
                    binding.textViewOptionOne,
                    1
                )
            }
            textViewOptionTwo.setOnClickListener {
                selectedOptionView(
                    binding.textViewOptionTwo,
                    2
                )
            }
            textViewOptionThree.setOnClickListener {
                selectedOptionView(
                    binding.textViewOptionThree,
                    3
                )
            }
            textViewOptionFour.setOnClickListener {
                selectedOptionView(
                    binding.textViewOptionFour,
                    4
                )
            }
            btnSubmit.setOnClickListener {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList.size -> {

                            setQuestion()
                        }
                        else -> {
                            val intent =
                                Intent(this@QuizzQuestionsActivity, ResultActivity::class.java)
                            intent.putExtra(Constants.USERNAME, mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {

                    val question = mQuestionsList[mCurrentPosition - 1]
                    binding.imgCrest.setImageResource(question.correctImage)
                    if (question.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        if (binding.btnSubmit.text == getString(R.string.btn_answer_check)) {
                            mCorrectAnswers++
                        }
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList.size) {
                        binding.btnSubmit.text = getString(R.string.btn_finish)
                    } else {
                        binding.btnSubmit.text = getString(R.string.btn_next_question)
                    }
                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun setQuestion() {
        val question = mQuestionsList[mCurrentPosition - 1]
        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList.size) {
            binding.btnSubmit.text = getString(R.string.btn_finish)
        } else {
            binding.btnSubmit.text = getString(R.string.btn_answer_check)
        }

        with(binding) {
            progressBar.progress = mCurrentPosition
            tvProgress.text = "${mCurrentPosition} / ${binding.progressBar.max}"
            tvQuestion.text = question.question
            imgCrest.setImageResource(question.image)
            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.textViewOptionOne)
        options.add(1, binding.textViewOptionTwo)
        options.add(2, binding.textViewOptionThree)
        options.add(3, binding.textViewOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.textViewOptionOne.background =
                ContextCompat.getDrawable(this, drawableView)
            2 -> binding.textViewOptionTwo.background =
                ContextCompat.getDrawable(this, drawableView)
            3 -> binding.textViewOptionThree.background =
                ContextCompat.getDrawable(this, drawableView)
            4 -> binding.textViewOptionFour.background =
                ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}