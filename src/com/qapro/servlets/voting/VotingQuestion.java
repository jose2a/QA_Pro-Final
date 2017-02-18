package com.qapro.servlets.voting;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qapro.dtos.VotingQuestionDto;
import com.qapro.entity.MemberInfo;
import com.qapro.entity.Question;
import com.qapro.entity.Vote;
import com.qapro.enums.VotingEnum;
import com.qapro.enums.VotingResult;
import com.qapro.service.QuestionService;
import com.qapro.service.VoteService;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class VotingServlet
 */
public class VotingQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VotingQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {			
			Long questionId = Long.parseLong(request.getParameter("questionId"));
			VotingEnum type = VotingEnum.valueOf(request.getParameter("type"));
			
			MemberInfo memberInfo = SessionUtil.getUserFromSession(request.getSession());
			
			if (memberInfo == null) {
				response.sendRedirect("./Login");
			}			
			
			Long userId = memberInfo.getId();
			
			VoteService voteService = new VoteService();
			Vote vote = new Vote();
			vote.setUserId(userId);
			vote.setQuestionId(questionId);
			vote.setType(type);
			vote.setDate(new Date());
			vote.setIsCancelled(false);

			VotingResult result = null;
			
			result = voteService.saveVote(vote);
			
			QuestionService questionService = new QuestionService();
			Question question = questionService.getQuestionAndAnswers(questionId);
			
			VotingQuestionDto votingQuestionDto = new VotingQuestionDto();
			votingQuestionDto.setVoteUp(question.getUpVote());
			votingQuestionDto.setVoteDown(question.getDownVote());

			switch (result) {
			case VOTING_SUCCESFULL:
				votingQuestionDto.setStatus("Your voting has been saved, thanks.");
				break;
			case ALREADY_VOTE_DOWN:
				votingQuestionDto.setStatus("You already voted this question down.");
				break;
			case ALREADY_VOTE_UP:
				votingQuestionDto.setStatus("You already voted this question up.");
				break;
			default:
				votingQuestionDto.setStatus("Your vote could not be saved.");
				break;
			}
			
			String json = new Gson().toJson(votingQuestionDto);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
