package com.falanper.jogodostimes

object Constants {

    const val USERNAME: String = "username"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val arrayOfNumbers = getSortedArray()
        val questionsList = ArrayList<Question>()
        for (number in arrayOfNumbers) {
            questionsList.add(que[number])
        }
        return questionsList
    }
}

private fun getSortedArray(): MutableList<Int> {
    val arrayInt = mutableListOf<Int>()
    while (arrayInt.size < 10) {
        val number = (1..100).random()
        if (number !in arrayInt) {
            arrayInt.add(number)
        }
    }
    return arrayInt
}
