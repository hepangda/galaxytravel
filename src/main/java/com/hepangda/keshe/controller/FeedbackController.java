package com.hepangda.keshe.controller;

import com.hepangda.keshe.model.Feedback;
import com.hepangda.keshe.service.FeedbackService;
import com.hepangda.keshe.util.Constants;
import com.hepangda.keshe.util.GenericController;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class FeedbackController extends GenericController {

  @Autowired
  FeedbackService srv;

  @GetMapping("/user/feedback/create/{orderId}")
  public String pathCreate(@PathVariable("orderId") long orderId, Model model) {
    model.addAttribute("active", "feedback");
    model.addAttribute(Constants.BIZF_MODIFIER, orderId);
    return "feedback_creat";
  }

  @GetMapping("/admin/feedback/list")
  public String pathList(@RequestParam(required = false) Integer page, Model model) {
    int ipage = dealPage(page);
    model.addAttribute("active", "feedback");
    model.addAttribute("page_max", srv.getPageMax());
    model.addAttribute(Constants.BIZF_LIST, srv.show(ipage));
    return "feedback_list";
  }


  @PostMapping("/api/feedback/create")
  public String doCreate(@RequestParam Map<String, Object> feedbackMap, Model model) {
    Feedback feedback = getBeanFromBody(Feedback.class, feedbackMap);
    model.addAttribute("active", "feedback");
    return resp(model, () -> srv.add(feedback), i -> "gotoorder",
        i -> pathCreate(feedback.getOrderId(), model));
  }

  @PostMapping("/api/feedback/delete")
  public String doDelete(@RequestParam long id, Model model) {
    model.addAttribute("active", "feedback");
    return resp(model, () -> srv.deleteById(id), () -> pathList(1, model));
  }
}
