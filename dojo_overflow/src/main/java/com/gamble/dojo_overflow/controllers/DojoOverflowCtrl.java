package com.gamble.dojo_overflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gamble.dojo_overflow.models.Answer;
import com.gamble.dojo_overflow.models.Question;
import com.gamble.dojo_overflow.models.Tag;
import com.gamble.dojo_overflow.services.AnswerService;
import com.gamble.dojo_overflow.services.QuestionService;
import com.gamble.dojo_overflow.services.TagService;

@Controller
public class DojoOverflowCtrl {

	private final TagService tagService;
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	public DojoOverflowCtrl(TagService tagService,
							QuestionService questionService,
							AnswerService answerService) {
		super();
		this.questionService = questionService;
		this.tagService = tagService;
		this.answerService = answerService;
	}

	@RequestMapping("/")
	public String home() {
		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("question_new") Question question,
								@ModelAttribute("error") String error) {
		System.out.println("error? " + error);
        return "/WEB-INF/views/question.jsp";
	}
	
	@PostMapping("/questions/new")
	public String createQuestion(@RequestParam Map<String, String> params,
								RedirectAttributes redirectAttributes,
								@RequestParam(value="tags_input_str", required=true, defaultValue="") String tags_input_str, 
								@Valid @ModelAttribute("question_new") Question question,
								BindingResult result) {
        if (result.hasErrors()) {
        		System.out.println("All params: " + params);
        		System.out.println("tags: "  + tags_input_str);
        		System.out.println("question: " + question);
        		System.out.println(result.toString());
            return "/WEB-INF/views/question.jsp";
        } else {
        		System.out.println("All params: " + params);
        		System.out.println("parameter input for tags: " + tags_input_str);
        		List<String> tag_list = Arrays.asList(tags_input_str.trim().split("\\s*,\\s*"));
        		if (tag_list.size() > 3) {
        			redirectAttributes.addFlashAttribute("error", "Only three tags per question!");
        			return "redirect:/questions/new";
        		}
        		if (!tags_input_str.equals(tags_input_str.toLowerCase())) {
              	redirectAttributes.addFlashAttribute("error", "lowercase only for tags!");
            		return "redirect:/questions/new";
        		}
        		List<Tag> question_tag_list = new ArrayList<Tag>();
        		for (String tag: tag_list) {
        			Tag tag_old = tagService.findTagBySubject(tag);
        			if (tag_old == null) {
//        				List<Question> tag_new_questions_list = new ArrayList<Question>();
//        				tag_new_questions_list.add(question);
        				Tag tag_new = new Tag();
        				tag_new.setSubject(tag);
 //       				tag_new.setQuestions(tag_new_questions_list);
        				tagService.addTag(tag_new);
        				question_tag_list.add(tag_new);
        			} else {
//        				List<Question> tag_old_questions_list = tag_old.getQuestions();
//        				tag_old_questions_list.add(question);
//        				tag_old.setQuestions(tag_old_questions_list);
        				tagService.updateTag(tag_old);
        				question_tag_list.add(tag_old);
        			}        			
        		}
        		question.setTags(question_tag_list);
            System.out.println(question);
        		questionService.addQuestion(question);
            return "redirect:/questions";
        }
    }
	
	@RequestMapping("/questions")
	public String dashboard(Model model) {
		List<Question> question_list = questionService.findAll();
		model.addAttribute("question_list", question_list);
		return "/WEB-INF/views/dashboard.jsp";
	}
	
	@RequestMapping("/questions/{id_uri}")
	public String createAnswer(@PathVariable("id_uri") int id_uri, Model model,
								@ModelAttribute("answer_new") Answer answer) {
		Question question = questionService.findQuestionById(id_uri);
		if (question != null) {
			List<Answer> answer_list = question.getAnswers();
			model.addAttribute("question", question);
			model.addAttribute("answer_list", answer_list);
			System.out.println("answer id: " + answer.getId());
			return "/WEB-INF/views/answer.jsp";
		} else {
			return "redirect:/questions";
		}
	}
	
	@PostMapping("/questions/{id_uri}")
	public String saveAnswer(@PathVariable("id_uri") int id_uri, Model model,
							@Valid @ModelAttribute("answer_new") Answer answer,
							BindingResult result
							) {
        if (result.hasErrors()) {
        		Question question = questionService.findQuestionById(id_uri);
    			List<Answer> answer_list = question.getAnswers();
    			model.addAttribute("question", question);
    			model.addAttribute("answer_list", answer_list);
            return "/WEB-INF/views/answer.jsp";
        } else {
        		Question question = questionService.findQuestionById(id_uri);
        		answer.setQuestion(question);
            answerService.addAnswer(answer);
			answer = new Answer();
			model.addAttribute("answer_new", answer);
            return "redirect:/questions/" + id_uri;
        }

	}
}
