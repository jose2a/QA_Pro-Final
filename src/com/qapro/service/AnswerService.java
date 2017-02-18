package com.qapro.service;

import com.qapro.dao.AnswerDao;
import com.qapro.entity.Answer;

public class AnswerService {

	private AnswerDao answerDao = new AnswerDao();

	public void addAnswer(Answer answer) throws Exception {

		answerDao.insertAnswer(answer);
	}
}
