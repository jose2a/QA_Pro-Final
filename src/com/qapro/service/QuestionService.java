package com.qapro.service;

import java.util.List;

import com.qapro.dao.AnswerDao;
import com.qapro.dao.MemberDao;
import com.qapro.dao.QuestionDao;
import com.qapro.entity.Answer;
import com.qapro.entity.Question;

public class QuestionService {

	QuestionDao questionDao = new QuestionDao();
	AnswerDao answerDao = new AnswerDao();
	MemberDao memberDao = new MemberDao();

	public Long InsertQuestion(Question question) throws Exception {
		return questionDao.insertQuestion(question);
	}

	public void UpdateQuestion(Question question) throws Exception {
		questionDao.updateQuestion(question);
	}

	public void deleteQuestion(long questionId) throws Exception {
		questionDao.deleteQuestion(questionId);
	}

	public Question getQuestionAndAnswers(Long questionId) throws Exception {

		Question question = questionDao.getQuestionById(questionId);

		if (question != null) {
			List<Answer> answerList = answerDao.getAnswersToQuestion(questionId);

			question.setAnswers(answerList);
		}

		return question;
	}

	public List<Question> getQuestionsByUserId(Long userId) throws Exception {

		List<Question> questionList = questionDao.getQuestionsByUserId(userId);

		return questionList;
	}

	public List<Question> getQuestionsWithAskerUsername() throws Exception {

		List<Question> questionList = questionDao.getQuestionsWithAskerUsername();
		return questionList;
	}

	public Question getQuestion(Long id) throws Exception {
		Question question = questionDao.getQuestionById(id);

		return question != null ? question : null;
	}

	public static void main(String args[]) {

		try {
			QuestionService service = new QuestionService();

			Question question;

			question = service.getQuestionAndAnswers(1l);

			System.out.println(question.getText());
			for (Answer a : question.getAnswers()) {
				System.out.println(a.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
